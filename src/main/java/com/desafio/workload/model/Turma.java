package com.desafio.workload.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.Valid;

import com.desafio.workload.validation.SIGLA_TURMA;

@Entity
@Table(name = "turma")
public class Turma implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Faz auto-increment
	private Long codigo;
	
	@SIGLA_TURMA
	private String sigla;
	
	@Column(name="quantidade_aluno")
	private Integer quantidadeAluno;
	
	@ManyToOne
	@JoinColumn(name="codigo_disciplina")
	private Disciplina disciplina;
	
	@ManyToOne
	@JoinColumn(name="codigo_professor")
	private Professor professor;
	
	@ManyToMany
	@JoinTable(name = "matricula", joinColumns = @JoinColumn(name = "codigo_turma")
				, inverseJoinColumns = @JoinColumn(name = "codigo_aluno"))
	private List<Aluno> alunos = new ArrayList<>();

	@Embedded
	@Valid
	private Periodo periodo;
	
	@Transient
	private String uuid;

	@Transient
	private Long professorTemp;
	
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

	public String getSigla() {
		return sigla;
	}

	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	public Integer getQuantidadeAluno() {
		return quantidadeAluno;
	}

	public void setQuantidadeAluno(Integer quantidadeAluno) {
		this.quantidadeAluno = quantidadeAluno;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public Periodo getPeriodo() {
		return periodo;
	}

	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
	}

	public List<Aluno> getAlunos() {
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public Long getProfessorTemp() {
		return professorTemp;
	}

	public void setProfessorTemp(Long professorTemp) {
		this.professorTemp = professorTemp;
	}

	public boolean isNova() {
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
		Turma other = (Turma) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

}
