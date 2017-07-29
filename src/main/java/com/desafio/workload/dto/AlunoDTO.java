package com.desafio.workload.dto;

public class AlunoDTO {

	private Long codigo;
	private String ra;
	private String nome;
	private String email;
	
	public AlunoDTO(Long codigo, String ra, String nome, String email) {
		this.codigo = codigo;
		this.ra = ra;
		this.nome = nome;
		this.email = email;
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getRa() {
		return ra;
	}

	public void setRa(String ra) {
		this.ra = ra;
	}

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
	
	

}
