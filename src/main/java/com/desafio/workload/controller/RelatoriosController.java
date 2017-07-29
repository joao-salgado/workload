package com.desafio.workload.controller;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.desafio.workload.dto.PeriodoRelatorio;
import com.desafio.workload.dto.Ranking;
import com.desafio.workload.dto.TurmaRelatorio;
import com.desafio.workload.model.Professor;
import com.desafio.workload.repository.Departamentos;
import com.desafio.workload.repository.Professores;
import com.desafio.workload.repository.Turmas;

@Controller
@RequestMapping("/relatorios")
public class RelatoriosController {
	
	@Autowired
	private Turmas turmas;
	
	@Autowired
	private Professores professores;
	
	@Autowired
	private Departamentos departamentos;

	@GetMapping("/atividadesProfessor")
	public ModelAndView relatorioAtividadesDoProfessor() {
		ModelAndView mv = new ModelAndView("relatorio/RelatorioAtividadesDoProfessor");
		mv.addObject(new PeriodoRelatorio());
		return mv;
	}
	
	@GetMapping("/turmasProfessor")
	public ModelAndView relatorioTurmasProfessor() {
		ModelAndView mv = new ModelAndView("relatorio/RelatorioTurmasDoProfessor");
		mv.addObject(new TurmaRelatorio());
		return mv;
	}
	
	@GetMapping("/ranking")
	public ModelAndView relatorioRanking() {
		ModelAndView mv = new ModelAndView("relatorio/RelatorioRanking");
		mv.addObject(new Ranking());
		mv.addObject("departamentos", departamentos.findAll());
		return mv;
	}
	
	@PostMapping("/ranking")
	public ModelAndView gerarRelatorioRanking(Ranking ranking) {
		
		Map<String, Object> parametros = new HashMap<>();
		
		Date dataInicio = Date.from(LocalDateTime.of(ranking.getDataInicio(), LocalTime.of(0, 0, 0))
				.atZone(ZoneId.systemDefault()).toInstant());
		Date dataFim = Date.from(LocalDateTime.of(ranking.getDataFim(), LocalTime.of(23, 59, 59))
				.atZone(ZoneId.systemDefault()).toInstant());
		
		parametros.put("format", "pdf");
		parametros.put("data_inicio", dataInicio);
		parametros.put("data_fim", dataFim);
		if(ranking.getDepartamento() != null) {
			parametros.put("departamento", ranking.getDepartamento().getCodigo());
			return new ModelAndView("relatorio_ranking_com_departamento", parametros);			 
		}
		return new ModelAndView("relatorio_ranking_sem_departamento", parametros);			
		
	}
	
	@PostMapping("/turmasProfessor")
	public ModelAndView gerarRelatorioTurmasProfessor(TurmaRelatorio turmaRelatorio) {
		
		Long temp = turmas.trasCodigoProfessor(turmaRelatorio.getProfessor().getCodigo());
		Professor professor = professores.findOne(temp);

		Map<String, Object> parametros = new HashMap<>();
		
		parametros.put("format", "pdf");
		parametros.put("ano", turmaRelatorio.getAno());
		parametros.put("siape", professor.getSiape());
		
		if(turmaRelatorio.getSemestre() != null) {			
			parametros.put("semestre", turmaRelatorio.getSemestre());
			return new ModelAndView("relatorio_turmas_do_professor", parametros);
		}
		
		return new ModelAndView("relatorio_turmas_sem_semestre_do_professor", parametros);
	}
	
	@PostMapping("/atividadesProfessor")
	public ModelAndView gerarRelatorioAtividadesProfessor(PeriodoRelatorio periodoRelatorio) {
		
		Long temp = turmas.trasCodigoProfessor(periodoRelatorio.getProfessor().getCodigo());
		Professor professor = professores.findOne(temp);

		Map<String, Object> parametros = new HashMap<>();
		
		Date dataInicio = Date.from(LocalDateTime.of(periodoRelatorio.getDataInicio(), LocalTime.of(0, 0, 0))
				.atZone(ZoneId.systemDefault()).toInstant());
		Date dataFim = Date.from(LocalDateTime.of(periodoRelatorio.getDataFim(), LocalTime.of(23, 59, 59))
				.atZone(ZoneId.systemDefault()).toInstant());
		
		System.out.println(dataInicio);
		System.out.println(dataFim);

		parametros.put("format", "pdf");
		parametros.put("data_inicio", dataInicio);
		parametros.put("data_fim", dataFim);
		parametros.put("siape", professor.getSiape());
		
		return new ModelAndView("relatorio_atividades_do_professor_reformulado", parametros);
	}
	
}
