package com.desafio.workload.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.desafio.workload.controller.page.PageWrapper;
import com.desafio.workload.model.Titulo;
import com.desafio.workload.model.Usuario;
import com.desafio.workload.repository.Departamentos;
import com.desafio.workload.repository.Grupos;
import com.desafio.workload.repository.RegimesTrabalho;
import com.desafio.workload.repository.Usuarios;
import com.desafio.workload.repository.filter.UsuarioFilter;
import com.desafio.workload.service.CadastroProfessorService;
import com.desafio.workload.service.CadastroUsuarioService;
import com.desafio.workload.service.StatusUsuario;
import com.desafio.workload.service.exception.EmailUsuarioJaCadastradoException;
import com.desafio.workload.service.exception.SenhaObrigatoriaUsuarioException;
import com.desafio.workload.service.exception.SiapeProfessorJaCadastradoException;

@Controller
@RequestMapping("/professores")
public class ProfessoresController {

	@Autowired
	private Departamentos departamentos;
	
	@Autowired
	private Usuarios usuarios;

	
	@Autowired
	private RegimesTrabalho regimesTrabalho;
	
	@Autowired
	private Grupos grupos;
	
	@Autowired
	private CadastroProfessorService cadastroProfessorService;
	
	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;
	

	@RequestMapping("/novo")
	public ModelAndView novo(Usuario usuario) {
		ModelAndView mv = new ModelAndView("professor/CadastroProfessor");
		mv.addObject("regimesTrabalho", regimesTrabalho.findAll()); //RETORNA UMA LISTA
		mv.addObject("departamentos", departamentos.findAll());
		mv.addObject("grupos", grupos.findAll());
		mv.addObject("titulos", Titulo.values()); //RETORNA UM ARRAY
		return mv;
	}
	
	@PostMapping(value = { "/novo", "{\\d+}" })
	public ModelAndView salvar(@Valid Usuario usuario, BindingResult result, Model model, RedirectAttributes	attributes) {
		if(result.hasErrors()) {
			return novo(usuario);
		}
		try {
			cadastroProfessorService.salvar(usuario.getProfessor(), usuario);
		} catch(SiapeProfessorJaCadastradoException e) {
			result.rejectValue("professor.siape", e.getMessage(), e.getMessage());
			return novo(usuario);
		} catch(EmailUsuarioJaCadastradoException e) {
			result.rejectValue("email", e.getMessage(), e.getMessage());
			return novo(usuario);
		} catch(SenhaObrigatoriaUsuarioException e) {
			result.rejectValue("senha", e.getMessage(), e.getMessage());
			return novo(usuario);
		}
		
		attributes.addFlashAttribute("mensagem", "Professor salvo com sucesso!");
		return new ModelAndView("redirect:/professores/novo");
	}
	
	@GetMapping
	public ModelAndView pesquisar(UsuarioFilter usuarioFilter
			, @PageableDefault(size = 10) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("/professor/PesquisaProfessores");
		mv.addObject("grupos", grupos.findAll());
		
		PageWrapper<Usuario> paginaWrapper = new PageWrapper<>(usuarios.filtrar(usuarioFilter, pageable, 1)
				, httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}
	
	@PutMapping("/status")
	@ResponseStatus(HttpStatus.OK)
	public void atualizarStatus(@RequestParam("codigos[]") Long[] codigos, @RequestParam("status") StatusUsuario statusUsuario) {
		cadastroUsuarioService.alterarStatus(codigos, statusUsuario);
	}
	
	@RequestMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<Usuario> pesquisar(String nome) {
		validarTamanhoNome(nome);
		return usuarios.findByNomeIgnoreCaseStartingWithAndAtivoTrueAndProfessorIsNotNull(nome);
	}
	
	private void validarTamanhoNome(String nome) {
		if (StringUtils.isEmpty(nome) || nome.length() < 3) {
			throw new IllegalArgumentException();
		}	
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Void> tratarIllegalArgumentException(IllegalArgumentException e) {
		return ResponseEntity.badRequest().build();
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable Long codigo) {
		Usuario usuario = usuarios.buscarComGrupos(codigo);
		ModelAndView mv = novo(usuario);
		mv.addObject(usuario);
		return mv;
		
	}
	
}
