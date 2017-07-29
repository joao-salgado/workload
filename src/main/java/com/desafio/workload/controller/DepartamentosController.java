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
import com.desafio.workload.model.Bloco;
import com.desafio.workload.model.Departamento;
import com.desafio.workload.repository.Departamentos;
import com.desafio.workload.repository.filter.DepartamentoFilter;
import com.desafio.workload.service.CadastroDepartamentoService;
import com.desafio.workload.service.exception.ImpossivelExcluirEntidadeException;
import com.desafio.workload.service.exception.SiglaDepartamentoJaCadastradaException;

@Controller
@RequestMapping("/departamentos")
public class DepartamentosController {
	
	@Autowired
	private CadastroDepartamentoService cadastroDepartamentoService; 
	
	@Autowired
	private Departamentos departamentos;
	
	@RequestMapping("/novo")
	public ModelAndView novo(Departamento departamento) {		
		ModelAndView mv = new ModelAndView("departamento/CadastroDepartamento");
		mv.addObject("blocos", Bloco.values());
 		return mv;
	}
	
	@PostMapping(value = { "/novo", "{\\d+}" })
	public ModelAndView salvar(@Valid Departamento departamento, BindingResult result, Model model, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			return novo(departamento);
		}
		
		try {
			cadastroDepartamentoService.salvar(departamento);
		} catch(SiglaDepartamentoJaCadastradaException e) {
			result.rejectValue("sigla", e.getMessage(), e.getMessage());
			return novo(departamento);
		}
		
		attributes.addFlashAttribute("mensagem", "Departamento salvo com sucesso!");
		return new ModelAndView("redirect:/departamentos/novo");
	}
	
	@GetMapping
	public ModelAndView pesquisar(DepartamentoFilter departamentoFilter, BindingResult result
			, @PageableDefault(size = 5) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("departamento/PesquisaDepartamentos");
		mv.addObject("blocos", Bloco.values());		
		
		PageWrapper<Departamento> paginaWrapper = new PageWrapper<>(departamentos.filtrar(departamentoFilter, pageable)
				, httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}
	
	@DeleteMapping("/{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") Departamento departamento) {
		try {
			cadastroDepartamentoService.excluir(departamento);
		} catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable("codigo") Departamento departamento) {
		ModelAndView mv = novo(departamento);
		mv.addObject(departamento);
		return mv;
	}
	
}
