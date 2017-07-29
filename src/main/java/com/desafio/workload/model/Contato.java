package com.desafio.workload.model;

import java.io.Serializable;

import javax.persistence.Embeddable;


@Embeddable
public class Contato implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer ramal;
	
	private String setor;
	
	private String telefone;

	public Integer getRamal() {
		return ramal;
	}

	public void setRamal(Integer ramal) {
		this.ramal = ramal;
	}

	public String getSetor() {
		return setor;
	}

	public void setSetor(String setor) {
		this.setor = setor;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	

}
