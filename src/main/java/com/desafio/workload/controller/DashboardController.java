package com.desafio.workload.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.desafio.workload.repository.Alunos;
import com.desafio.workload.repository.Cursos;
import com.desafio.workload.repository.Departamentos;
import com.desafio.workload.repository.Disciplinas;
import com.desafio.workload.repository.Professores;
import com.desafio.workload.repository.Turmas;

@Controller
public class DashboardController {

	@Autowired
	private Departamentos departamentos;
	
	@Autowired
	private Cursos cursos;
	
	@Autowired
	private Disciplinas disciplinas;
	
	@Autowired
	private Professores professores;
	
	@Autowired
	private Turmas turmas;
	
	@Autowired
	private Alunos alunos;
	
	@GetMapping("/")
	public ModelAndView dashboard() {
		ModelAndView mv = new ModelAndView("Dashboard");
		
		mv.addObject("totalDepartamentos", departamentos.count());
		mv.addObject("totalCursos", cursos.count());
		mv.addObject("totalDisciplinas", disciplinas.count());
		mv.addObject("totalProfessores", professores.count() - 1);
		mv.addObject("totalTurmas", turmas.count());
		mv.addObject("totalAlunos", alunos.count());
		
		return mv;
		
	}
	
}
