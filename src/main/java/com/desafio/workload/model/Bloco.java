package com.desafio.workload.model;

public enum Bloco {
	A("A"),
	I("I"),
	K("K"),
	P("P");
	
	private String descricao;
	
	Bloco(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
}
