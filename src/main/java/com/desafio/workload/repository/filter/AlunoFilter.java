package com.desafio.workload.repository.filter;

import com.desafio.workload.model.Curso;

public class AlunoFilter {

	private String nome;
	private String ra;
	private Curso curso;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getRa() {
		return ra;
	}
	public void setRa(String ra) {
		this.ra = ra;
	}
	public Curso getCurso() {
		return curso;
	}
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	
	
	
}
