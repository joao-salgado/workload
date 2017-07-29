package com.desafio.workload.repository.filter;

import com.desafio.workload.model.Bloco;

public class DepartamentoFilter {

	
	private Bloco bloco;
	private String nome;
	private String sigla;
	
	public Bloco getBloco() {
		return bloco;
	}
	public void setBloco(Bloco bloco) {
		this.bloco = bloco;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getSigla() {
		return sigla;
	}
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}
	
	
	
}
