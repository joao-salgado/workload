package com.desafio.workload.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
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

import com.desafio.workload.security.UsuarioSistema;
import com.desafio.workload.controller.page.PageWrapper;
import com.desafio.workload.dto.AtividadePorMes;
import com.desafio.workload.model.Atividade;
import com.desafio.workload.model.Execucao;
import com.desafio.workload.repository.Areas;
import com.desafio.workload.repository.Atividades;
import com.desafio.workload.repository.Execucoes;
import com.desafio.workload.repository.Restricoes;
import com.desafio.workload.repository.filter.AtividadeFilter;
import com.desafio.workload.repository.filter.ExecucaoFilter;
import com.desafio.workload.service.CadastroAtividadeService;
import com.desafio.workload.service.CadastroExecucaoService;
import com.desafio.workload.service.TerminoAtividade;
import com.desafio.workload.service.exception.DataFimAnteriorAInicioException;
import com.desafio.workload.service.exception.ImpossivelExcluirEntidadeException;
import com.desafio.workload.service.exception.RestricaoEncontradaException;

@Controller
@RequestMapping("/atividades")
public class AtividadesController {

	@Autowired
	private Areas areas;
	
	@Autowired
	private Restricoes restricoes;
	
	@Autowired
	private Execucoes execucoes;
	
	@Autowired
	private Atividades atividades;
	
	@Autowired
	private CadastroAtividadeService cadastroAtividadeService;
	
	@Autowired
	private CadastroExecucaoService cadastroExecucaoService;
	
	@RequestMapping("/nova")
	public ModelAndView nova(Atividade atividade) {
		ModelAndView mv = new ModelAndView("atividade/CadastroAtividade");
		mv.addObject("areas", areas.findAll());
		mv.addObject("restricoes", restricoes.findAll());
 		return mv;
	}
	
	@RequestMapping("/criar")
	public ModelAndView criar(Atividade atividade) {
		ModelAndView mv = new ModelAndView("atividade/CriarAtividade");
		mv.addObject("areas", areas.findAll());
		mv.addObject("restricoes", restricoes.findAll());
 		return mv;
	}
	
	@RequestMapping("/adicionar")
	public ModelAndView novaExecucao(Execucao execucao) {
		ModelAndView mv = new ModelAndView("atividade/AdicionarAtividade");
 		return mv;
	}
	
	@PostMapping(value = { "/nova", "{\\d+}" })
	public ModelAndView salvar(@Valid Atividade atividade, BindingResult result, Model model, RedirectAttributes	attributes) {
		if(result.hasErrors()) {
			return nova(atividade);
		}
		
		cadastroAtividadeService.salvar(atividade);
		
		attributes.addFlashAttribute("mensagem", "Atividade salva com sucesso!");
		return new ModelAndView("redirect:/atividades/nova");
	}
	
	@PostMapping("/criar")
	public ModelAndView criar(@Valid Atividade atividade, BindingResult result, Model model, RedirectAttributes	attributes) {
		if(result.hasErrors()) {
			return nova(atividade);
		}
		
		cadastroAtividadeService.salvar(atividade);
		
		attributes.addFlashAttribute("mensagem", "Atividade salva com sucesso!");
		return new ModelAndView("redirect:/atividades/criar");
	}
	
	@PostMapping("/adicionar")
	public ModelAndView salvarExecucao(@Valid Execucao execucao, BindingResult result, Model model, RedirectAttributes	attributes
			, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {
		if(result.hasErrors()) {
			return novaExecucao(execucao);
		}
		
		execucao.setProfessor(usuarioSistema.getUsuario().getProfessor());
		
		try {
			cadastroExecucaoService.salvar(execucao);
		} catch(DataFimAnteriorAInicioException e) {
			result.rejectValue("termino", e.getMessage(), e.getMessage());
			return novaExecucao(execucao);
		} catch (RestricaoEncontradaException e) {
			result.rejectValue("inicio", e.getMessage(), e.getMessage());
			return novaExecucao(execucao);
		}
		
		attributes.addFlashAttribute("mensagem", "Atividade adicionada com sucesso!");
		return new ModelAndView("redirect:/atividades");
	}
	
	@GetMapping
	public ModelAndView pesquisar(AtividadeFilter atividadeFilter, BindingResult result
			, @PageableDefault(size = 10) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("atividade/PesquisaAtividades");
		mv.addObject("areas", areas.findAll());
		mv.addObject("restricoes", restricoes.findAll());
		
		PageWrapper<Atividade> paginaWrapper = new PageWrapper<>(atividades.filtrar(atividadeFilter, pageable)
				, httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}
	
	@DeleteMapping("/{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") Atividade atividade) {
		try {
			cadastroAtividadeService.excluir(atividade);
		} catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		return ResponseEntity.ok().build();
	}

	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable("codigo") Atividade atividade) {
		ModelAndView mv = nova(atividade);
		mv.addObject(atividade);
		return mv;
	}
	
	@GetMapping("/adicionar/{codigo}")
	public ModelAndView adicionar(@PathVariable("codigo") Atividade atividade) {
		Execucao execucao = new Execucao();
		execucao.setAtividade(atividade);
		ModelAndView mv = novaExecucao(execucao);
		mv.addObject(execucao);
		return mv;
	}
	
	@GetMapping("/totalPorMesInicio")
	public @ResponseBody List<AtividadePorMes> listarTotalAtividadePorMesInicio() {
		return atividades.totalPorMesInicio();
	}
	
	@GetMapping("/totalPorMesTermino")
	public @ResponseBody List<AtividadePorMes> listarTotalAtividadePorMesTermino() {
		return atividades.totalPorMesTermino();
	}
	
	
	@RequestMapping("/especificas")
	public ModelAndView pesquisarEspecifica(ExecucaoFilter execucaoFilter, BindingResult result
			, @PageableDefault(size = 10) Pageable pageable, HttpServletRequest httpServletRequest, @AuthenticationPrincipal UsuarioSistema usuarioSistema) {
		ModelAndView mv = new ModelAndView("atividade/AtividadesEspecificas");
		mv.addObject("areas", areas.findAll());
		mv.addObject("restricoes", restricoes.findAll());
		
		PageWrapper<Execucao> paginaWrapper = new PageWrapper<>(execucoes.filtrar(execucaoFilter, pageable, usuarioSistema.getUsuario().getProfessor())
				, httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}
	
	@PutMapping("/termino")
	@ResponseStatus(HttpStatus.OK)
	public void atualizarTerminoAtividade(@RequestParam("codigos[]") Long[] codigos, @RequestParam("status") TerminoAtividade terminoAtividade) {
		cadastroExecucaoService.alterarTermino(codigos, terminoAtividade);
	}
	
}
