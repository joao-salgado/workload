package com.desafio.workload.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="professor")
public class Professor implements Serializable {

private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Faz auto-increment
	private Long codigo;
	
	@NotBlank(message="O SIAPE é obrigatório")
	private String siape;
	
	@NotNull(message="Coloque o professor em um departamento")
	@ManyToOne
	@JoinColumn(name="codigo_departamento")
	private Departamento departamento;
	
	@NotNull(message="O regime de trabalho é obrigatório")
	@ManyToOne
	@JoinColumn(name="codigo_regime")
	private RegimeTrabalho regimeTrabalho;
	
	@NotNull(message="O título do professor é obrigatório")
	@Enumerated(EnumType.STRING)
	private Titulo titulo;
	
	@Valid
	@OneToOne(cascade = {CascadeType.ALL}, mappedBy = "professor")
	private Usuario usuario;
	
	@OneToMany(mappedBy="professor")
	private List<Execucao> execucoes;
	
	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	
	public RegimeTrabalho getRegimeTrabalho() {
		return regimeTrabalho;
	}

	public void setRegimeTrabalho(RegimeTrabalho regimeTrabalho) {
		this.regimeTrabalho = regimeTrabalho;
	}

	public String getSiape() {
		return siape;
	}

	public void setSiape(String siape) {
		this.siape = siape;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}


	public Titulo getTitulo() {
		return titulo;
	}

	public void setTitulo(Titulo titulo) {
		this.titulo = titulo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public List<Execucao> getExecucoes() {
		return execucoes;
	}

	public void setExecucoes(List<Execucao> execucoes) {
		this.execucoes = execucoes;
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
		Professor other = (Professor) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
}
