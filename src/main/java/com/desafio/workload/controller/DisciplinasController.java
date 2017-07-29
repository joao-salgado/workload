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
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.desafio.workload.controller.page.PageWrapper;
import com.desafio.workload.model.Disciplina;
import com.desafio.workload.repository.Cursos;
import com.desafio.workload.repository.Dificuldades;
import com.desafio.workload.repository.Disciplinas;
import com.desafio.workload.repository.filter.DisciplinaFilter;
import com.desafio.workload.service.CadastroDisciplinaService;
import com.desafio.workload.service.exception.ImpossivelExcluirEntidadeException;
import com.desafio.workload.service.exception.SiglaDisciplinaJaCadastradaException;

@Controller
@RequestMapping("/disciplinas")
public class DisciplinasController {
	
	@Autowired
	private Cursos cursos;
	
	@Autowired
	private Disciplinas disciplinas;
	
	@Autowired
	private Dificuldades dificuldades;
	
	@Autowired
	private CadastroDisciplinaService cadastroDisciplinaService;
	
	@RequestMapping("/nova")
	public ModelAndView nova(Disciplina disciplina) {
		ModelAndView mv = new ModelAndView("disciplina/CadastroDisciplina");
		mv.addObject("cursos", cursos.findAll());
		mv.addObject("dificuldades", dificuldades.findAll());
		return mv;
	}
	
	@PostMapping(value = { "/nova", "{\\d+}" })
	public ModelAndView salvar(@Valid Disciplina disciplina, BindingResult result, Model model, RedirectAttributes	attributes) {
		if(result.hasErrors()) {
			return nova(disciplina);
		}
		
		try {
			cadastroDisciplinaService.salvar(disciplina);
		} catch(SiglaDisciplinaJaCadastradaException e) {
			result.rejectValue("sigla", e.getMessage(), e.getMessage());
			return nova(disciplina);
		}
		attributes.addFlashAttribute("mensagem", "Disciplina salva com sucesso!");
		return new ModelAndView("redirect:/disciplinas/nova");
	}
	
	@GetMapping
	public ModelAndView pesquisar(DisciplinaFilter disciplinaFilter, BindingResult result
			, @PageableDefault(size = 10) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("disciplina/PesquisaDisciplinas");
		mv.addObject("cursos", cursos.findAll());
		mv.addObject("dificuldades", dificuldades.findAll());
		
		PageWrapper<Disciplina> paginaWrapper = new PageWrapper<>(disciplinas.filtrar(disciplinaFilter, pageable)
				, httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}
	
	@RequestMapping(consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody List<Disciplina> pesquisar(String nome) {
		validarTamanhoSigla(nome);
		return disciplinas.findByNomeStartingWithIgnoreCase(nome);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Void> tratarIllegalArgumentException(IllegalArgumentException e) {
		return ResponseEntity.badRequest().build();
	}
	
	
	private void validarTamanhoSigla(String nome) {
		if (StringUtils.isEmpty(nome) || nome.length() < 3) {
			throw new IllegalArgumentException();
		}
		
	}
	
	@DeleteMapping("/{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") Disciplina disciplina) {
		try {
			cadastroDisciplinaService.excluir(disciplina);
		} catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable("codigo") Disciplina disciplina) {
		ModelAndView mv = nova(disciplina);
		mv.addObject(disciplina);
		return mv;
	}
}
