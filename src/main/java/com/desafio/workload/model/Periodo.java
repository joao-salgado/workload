package com.desafio.workload.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Periodo implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	
	private Integer ano;
	
	private Integer semestre;
	
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
	
}
