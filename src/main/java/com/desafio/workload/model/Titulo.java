package com.desafio.workload.model;

public enum Titulo {
	
	BACHAREL("Bacharel"),
	ESPECIALISTA("Especialista"),
	MESTRE("Mestre"),
	DOUTOR("Doutor");
	
	private String descricao;
	
	Titulo(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
}
