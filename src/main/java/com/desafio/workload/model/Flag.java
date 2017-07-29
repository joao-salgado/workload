package com.desafio.workload.model;

public enum Flag {
	A("A"), //LIMITE DE UM POR ANO
	B("B"), //LIMITE DE DOIS POR ANO
	C("C"), //LIMITE DE CINCO POR ANO
	D("D"); //SEM RESTRICAO
	
	private String descricao;
	
	Flag(String descricao) {
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return descricao;
	}
}
