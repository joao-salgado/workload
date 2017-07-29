package com.desafio.workload.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.desafio.workload.validation.SIGLA_DISCIPLINA;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name="disciplina")
public class Disciplina implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Faz auto-increment
	private Long codigo;
	
	@NotBlank(message = "O nome é obrigatório")
	private String nome;
	
	@SIGLA_DISCIPLINA
	@NotBlank(message = "A sigla é obrigatória")
	private String sigla;
	
	@Max(value=100, message = "A quantidade de créditos deve ser menor que 100")
	@NotNull(message = "O crédito é obrigatório")
	private Long creditos;
	
	@NotNull(message="A carga horária é obrigatória")
	@Max(value = 85, message="Carga horária deve ser menor ou igual a 85")
	@Min(value = 34, message="Carga horária deve ser maior ou igual a 34")
	@Column(name="carga_horaria")
	private Integer cargaHoraria;
	
	@JsonIgnore
	@NotNull(message="O curso é obrigatório")
	@ManyToOne
	@JoinColumn(name="codigo_curso")
	private Curso curso;
	
	@JsonIgnore
	@NotNull(message="A dificuldade é obrigatória")
	@ManyToOne
	@JoinColumn(name="codigo_dificuldade")
	private Dificuldade dificuldade;
	
	public Dificuldade getDificuldade() {
		return dificuldade;
	}
	public void setDificuldade(Dificuldade dificuldade) {
		this.dificuldade = dificuldade;
	}

	@PrePersist @PreUpdate
	private void prePersisteUpdate() {
		sigla = sigla.toUpperCase();
	}

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
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
	

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public Integer getCargaHoraria() {
		return cargaHoraria;
	}
	
	public void setCargaHoraria(Integer cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
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
		Disciplina other = (Disciplina) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	} 
		
}
