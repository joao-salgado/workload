package com.desafio.workload.controller;

import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.desafio.workload.controller.page.PageWrapper;
import com.desafio.workload.controller.validator.TurmaValidator;
import com.desafio.workload.model.Aluno;
import com.desafio.workload.model.Professor;
import com.desafio.workload.model.Turma;
import com.desafio.workload.repository.Alunos;
import com.desafio.workload.repository.Disciplinas;
import com.desafio.workload.repository.Professores;
import com.desafio.workload.repository.Turmas;
import com.desafio.workload.repository.filter.TurmaFilter;
import com.desafio.workload.service.CadastroTurmaService;
import com.desafio.workload.service.exception.ImpossivelExcluirEntidadeException;
import com.desafio.workload.session.TabelasAlunosSession;

@Controller
@RequestMapping("/turmas")
public class TurmasController {
	
	@Autowired
	private CadastroTurmaService cadastroTurmaService; 
	
	@Autowired
	private Alunos alunos;
	
	@Autowired
	private Turmas turmas;
	
	@Autowired
	private Disciplinas disciplinas;
	
	@Autowired
	private Professores professores;
	
	@Autowired
	private TabelasAlunosSession tabelaAlunos;
	
	@Autowired
	private TurmaValidator turmaValidator;
	
	@InitBinder("turma")
	public void inicializarValidador(WebDataBinder binder) {
		binder.setValidator(turmaValidator);
	}

	@GetMapping("/nova")
	public ModelAndView nova(Turma turma) {
		ModelAndView mv = new ModelAndView("turma/CadastroTurma");
		
		setUuid(turma);
		
		mv.addObject("itens", turma.getAlunos());
		
		return mv;
	}
	
	@PostMapping("/nova")
	public ModelAndView cadastrar(Turma turma, BindingResult result, Model model, RedirectAttributes	attributes) {
		turma.setAlunos(tabelaAlunos.getItens(turma.getUuid()));
		turma.setQuantidadeAluno(turma.getAlunos().size());
		
		
		if(turma.isNova()) {
			Long temp = turmas.trasCodigoProfessor(turma.getProfessorTemp());
			Professor professor = professores.findOne(temp);
			turma.setProfessor(professor);
		} else {
			Professor professor = professores.findOne(turma.getProfessorTemp());
			turma.setProfessor(professor);
		}
		
		turmaValidator.validate(turma, result);
		if(result.hasErrors()) {
			return nova(turma);
		}
		
		cadastroTurmaService.salvar(turma);
		
		attributes.addFlashAttribute("mensagem", "Turma salva com sucesso!");
		return new ModelAndView("redirect:/turmas/nova");
	}
	
	@GetMapping
	public ModelAndView pesquisar(TurmaFilter turmaFilter, BindingResult result
			, @PageableDefault(size = 10) Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("turma/PesquisaTurmas");
		mv.addObject("disciplinas", disciplinas.findAll());
		
		PageWrapper<Turma> paginaWrapper = new PageWrapper<>(turmas.filtrar(turmaFilter, pageable)
				, httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable Long codigo) {
		Turma turma = turmas.buscarComAlunos(codigo);
		
		setUuid(turma);
		for(Aluno aluno : turma.getAlunos()) {
			tabelaAlunos.adicionarItem(turma.getUuid(), aluno);
		}
		
		turma.setProfessorTemp(turma.getProfessor().getCodigo());
		
		ModelAndView mv = nova(turma);
		mv.addObject(turma);
		return mv;
		
	}
	

	@PostMapping("/item")
	public ModelAndView adicionarItem(Long codigoAluno, String uuid) {
		Aluno aluno = alunos.findOne(codigoAluno);
		tabelaAlunos.adicionarItem(uuid, aluno);
		
		return mvTabelaAlunosTurma(uuid);
	}
	
	@DeleteMapping("/item/{uuid}/{codigoAluno}")
	public ModelAndView excluirAluno(@PathVariable("codigoAluno") Aluno aluno, @PathVariable String uuid) {
		tabelaAlunos.excluirItem(uuid, aluno);
		
		return mvTabelaAlunosTurma(uuid);
	}
	
	@DeleteMapping("/{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") Turma turma) {
		try {
			cadastroTurmaService.excluir(turma);
		} catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
		return ResponseEntity.ok().build();
	}

	private ModelAndView mvTabelaAlunosTurma(String uuid) {
		ModelAndView mv = new ModelAndView("turma/TabelaAlunosTurma");
		mv.addObject("itens", tabelaAlunos.getItens(uuid));
		return mv;
	}
	
	private void setUuid(Turma turma) {
		
		if(StringUtils.isEmpty(turma.getUuid())) {
			turma.setUuid(UUID.randomUUID().toString());
		}
		
	}
	
}
