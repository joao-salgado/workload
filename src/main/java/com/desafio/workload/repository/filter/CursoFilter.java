package com.desafio.workload.repository.filter;

import com.desafio.workload.model.Departamento;
import com.desafio.workload.model.Tipo;

public class CursoFilter {

	private String nome;
	private Departamento departamento;
	private Tipo tipo;
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Departamento getDepartamento() {
		return departamento;
	}
	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	
	
	
}
