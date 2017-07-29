package com.desafio.workload.repository.filter;

import java.util.List;

import com.desafio.workload.model.Grupo;
import com.desafio.workload.model.Professor;

public class UsuarioFilter {

	private Professor professor;
	private String nome;
	private String email;
	private List<Grupo> grupos;
	
	private String siape;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public String getSiape() {
		return siape;
	}

	public void setSiape(String siape) {
		this.siape = siape;
	}
	
}