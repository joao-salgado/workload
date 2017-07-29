package com.desafio.workload.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;


@Entity
@Table(name="departamento")
public class Departamento implements Serializable {
	
	private static final long serialVersionUID = 1L;

	//@Column(name="exemplo") //Mapeia tal atributo que está abaixo para o nome acima no banco
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Faz auto-increment
	private Long codigo;
	
	
	@NotBlank(message = "A sigla é obrigatória")
	private String sigla;
	
	@NotBlank(message = "O nome é obrigatório")
	private String nome;
	
	@OneToMany(mappedBy="departamento")
	private List<Curso> cursos;	
	
	@NotNull(message = "O bloco é obrigatório")
	@Enumerated(EnumType.STRING)
	private Bloco bloco;
	
	@OneToMany(mappedBy="departamento")
	private List<Professor> professores;
	
	@PrePersist @PreUpdate
	private void prePersisteUpdate() {
		sigla = sigla.toUpperCase();
	}
	
	public List<Professor> getProfessores() {
		return professores;
	}
	
	public void setProfessores(List<Professor> professores) {
		this.professores = professores;
	}
	
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
	
	public Long getCodigo() {
		return codigo;
	}
	
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	public List<Curso> getCursos() {
		return cursos;
	}
	
	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}
	
	public Bloco getBloco() {
		return bloco;
	}
	
	public void setBloco(Bloco bloco) {
		this.bloco = bloco;
	}
	
	public boolean isNovo() {
		return codigo == null;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Departamento other = (Departamento) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
}
