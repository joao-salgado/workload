package com.desafio.workload.dto;

import com.desafio.workload.model.Professor;

public class TurmaRelatorio {

	private Integer ano;
	private Integer semestre;
	
	private Professor professor;

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Integer getSemestre() {
		return semestre;
	}

	public void setSemestre(Integer semestre) {
		this.semestre = semestre;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
		
}
