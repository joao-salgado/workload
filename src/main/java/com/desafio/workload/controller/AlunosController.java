package com.desafio.workload.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.desafio.workload.controller.page.PageWrapper;
import com.desafio.workload.dto.AlunoDTO;
import com.desafio.workload.model.Aluno;
import com.desafio.workload.model.Genero;
import com.desafio.workload.repository.Alunos;
import com.desafio.workload.repository.Cursos;
import com.desafio.workload.repository.filter.AlunoFilter;
import com.desafio.workload.service.CadastroAlunoService;
import com.desafio.workload.service.exception.ImpossivelExcluirEntidadeException;
import com.desafio.workload.service.exception.RaAlunoJaCadastradoException;

@Controller
@RequestMapping("/alunos")
public class AlunosController {
	
	@Autowired
	private Cursos cursos;
	
	@Autowired
	private CadastroAlunoService cadastroAlunoService; 
	
	@Autowired
	private Alunos alunos;
	
	@RequestMapping("/novo")
	public ModelAndView novo(Aluno aluno) {
		ModelAndView mv = new ModelAndView("aluno/CadastroAluno");
		mv.addObject("cursos", cursos.findAll()); //RETORNA UMA LISTA
		mv.addObject("generos", Genero.values()); //RETORNA UM ARRAY
		return mv;
	}
	
	@PostMapping(value = { "/novo", "{\\d+}" })
	public ModelAndView salvar(@Valid Aluno aluno, BindingResult result, Model model, RedirectAttributes	attributes) {
		if(result.hasErrors()) {
			return novo(aluno);
		}
		
		try {
			cadastroAlunoService.salvar(aluno);
		} catch(RaAlunoJaCadastradoException e) {
			result.rejectValue("ra", e.getMessage(), e.getMessage());
			return novo(aluno);
		}
		
		attributes.addFlashAttribute("mensagem", "Aluno salvo com sucesso!");
		return new ModelAndView("redirect:/alunos/novo");
	}
	
	@GetMapping
	public ModelAndView pesquisar(AlunoFilter alunoFilter, BindingResult result
			, @PageableDefault(size = 10) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("aluno/PesquisaAlunos");
		mv.addObject("cursos", cursos.findAll());
		mv.addObject("generos", Genero.values());
		PageWrapper<Aluno> paginaWrapper = new PageWrapper<>(alunos.filtrar(alunoFilter, pageable)
				, httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}
	
	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<AlunoDTO> pesquisar(String raOuNome) {
		
		return alunos.porRaOuNome(raOuNome);
		
	}
	
	@DeleteMapping("/{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") Aluno aluno) {
		try {
			cadastroAlunoService.excluir(aluno);
		} catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable("codigo") Aluno aluno) {
		ModelAndView mv = novo(aluno);
		mv.addObject(aluno);
		return mv;
	}
	
}
