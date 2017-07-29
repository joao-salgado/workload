package com.desafio.workload.controller;

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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.desafio.workload.controller.page.PageWrapper;
import com.desafio.workload.model.Tipo;
import com.desafio.workload.repository.Tipos;
import com.desafio.workload.repository.filter.TipoFilter;
import com.desafio.workload.service.CadastroTipoService;
import com.desafio.workload.service.exception.DescricaoTipoJaCadastradoException;
import com.desafio.workload.service.exception.ImpossivelExcluirEntidadeException;

@Controller
@RequestMapping("/tipos")
public class TiposController {
	
	@Autowired
	private CadastroTipoService cadastroTipoService;
	
	@Autowired
	private Tipos tipos;
	
	@RequestMapping("/novo")
	public ModelAndView novo(Tipo tipo) {
		ModelAndView mv = new ModelAndView("tipo/CadastroTipo");

		return mv;
	}
	
	
	@PostMapping(value = { "/novo", "{\\d+}" })
	public ModelAndView salvar(@Valid Tipo tipo, BindingResult result, Model model, RedirectAttributes	attributes) {
		if(result.hasErrors()) {
			return novo(tipo);
		}
		
		try {
			cadastroTipoService.salvar(tipo);
		} catch (DescricaoTipoJaCadastradoException e) {
			result.rejectValue("descricao", e.getMessage(), e.getMessage());
			return novo(tipo);
		}
		
		attributes.addFlashAttribute("mensagem", "Tipo salvo com sucesso!");
		return new ModelAndView("redirect:/tipos/novo");
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
	public @ResponseBody ResponseEntity<?> salvar(@RequestBody @Valid Tipo tipo, BindingResult result) {
		if(result.hasErrors()) {
			return ResponseEntity.badRequest().body(result.getFieldError("descricao").getDefaultMessage());
		}
	
//		try {
			tipo = cadastroTipoService.salvar(tipo);
//		} catch (DescricaoTipoJaCadastradoException e) {
//			return ResponseEntity.badRequest().body(e.getMessage());
//		}
		
		return ResponseEntity.ok(tipo);
	}
	
	@GetMapping
	public ModelAndView pesquisar(TipoFilter tipoFilter, BindingResult result
			, @PageableDefault(size = 5) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("tipo/PesquisaTipos");		
		
		PageWrapper<Tipo> paginaWrapper = new PageWrapper<>(tipos.filtrar(tipoFilter, pageable)
				, httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}
	
	@DeleteMapping("/{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") Tipo tipo) {
		try {
			cadastroTipoService.excluir(tipo);
		} catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		return ResponseEntity.ok().build();
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable("codigo") Tipo tipo) {
		ModelAndView mv = novo(tipo);
		mv.addObject(tipo);
		return mv;
	}
	
}
