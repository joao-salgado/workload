package com.desafio.workload.repository.filter;

import com.desafio.workload.model.Curso;
import com.desafio.workload.model.Dificuldade;

public class DisciplinaFilter {

	private String sigla;
	private String nome;
	private Long creditos;
	private Curso curso;
	private Dificuldade dificuldade;
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Long getCreditos() {
		return creditos;
	}
	public void setCreditos(Long creditos) {
		this.creditos = creditos;
	}
	public Curso getCurso() {
		return curso;
	}
	public void setCurso(Curso curso) {
		this.curso = curso;
	}
	public Dificuldade getDificuldade() {
		return dificuldade;
	}
	public void setDificuldade(Dificuldade dificuldade) {
		this.dificuldade = dificuldade;
	}
	
	
	
}
