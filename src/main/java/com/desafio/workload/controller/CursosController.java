package com.desafio.workload.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
import com.desafio.workload.model.Curso;
import com.desafio.workload.repository.Cursos;
import com.desafio.workload.repository.Departamentos;
import com.desafio.workload.repository.Tipos;
import com.desafio.workload.repository.filter.CursoFilter;
import com.desafio.workload.service.CadastroCursoService;
import com.desafio.workload.service.exception.ImpossivelExcluirEntidadeException;
import com.desafio.workload.service.exception.NomeCursoJaCadastradoException;

@Controller
@RequestMapping("/cursos")
public class CursosController {
	
	@Autowired
	private Tipos tipos;
	
	@Autowired
	private Departamentos departamentos;
	
	@Autowired
	private Cursos cursos;
	
	@Autowired
	private CadastroCursoService cadastroCursoService;
	
	@RequestMapping("/novo")
	public ModelAndView novo(Curso curso) {
		ModelAndView mv = new ModelAndView("curso/CadastroCurso");
		mv.addObject("tipos", tipos.findAll());
		mv.addObject("departamentos", departamentos.findAll());
 		return mv;
	}
	
	
	@PostMapping(value = { "/novo", "{\\d+}" })
	public ModelAndView salvar(@Valid Curso curso, BindingResult result, Model model, RedirectAttributes	attributes) {
		if(result.hasErrors()) {
			return novo(curso);
		}
		
		try {
			cadastroCursoService.salvar(curso);
		} catch(NomeCursoJaCadastradoException e) {
			result.rejectValue("nome", e.getMessage(), e.getMessage());
			return novo(curso);
		}
		
		attributes.addFlashAttribute("mensagem", "Curso salvo com sucesso!");
		return new ModelAndView("redirect:/cursos/novo");
	}
	
	@GetMapping
	public ModelAndView pesquisar(CursoFilter cursoFilter, BindingResult result
			, @PageableDefault(size = 5) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("curso/PesquisaCursos");
		mv.addObject("departamentos", departamentos.findAll());
		mv.addObject("tipos", tipos.findAll());
		
		PageWrapper<Curso> paginaWrapper = new PageWrapper<>(cursos.filtrar(cursoFilter, pageable)
				, httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}
	
	@DeleteMapping("/{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") Curso curso) {
		try {
			cadastroCursoService.excluir(curso);
		} catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		return ResponseEntity.ok().build();
	}

	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable("codigo") Curso curso) {
		ModelAndView mv = novo(curso);
		mv.addObject(curso);
		return mv;
	}
	
}
