package com.desafio.workload.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;


@Entity
@Table(name="atividade")
public class Atividade implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //Faz auto-increment
	private Long codigo;
	
	@NotBlank(message = "A descricao é obrigatória")
	private String descricao;
	
	@NotNull(message="Adicione a pontuação")
	@Max(value=100, message="A pontuação máxima é 100")
	private Integer pontuacao;
	
	@NotNull(message="Adicione uma restrição")
	@ManyToOne
	@JoinColumn(name="codigo_restricao")
	private Restricao restricao;
	
	@NotNull(message="A área é obrigatória")
	@ManyToOne
	@JoinColumn(name="codigo_area")
	private Area area;
	
	@OneToMany(mappedBy="atividade")
	private List<Execucao> execucoes;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}
	
	
	public Restricao getRestricao() {
		return restricao;
	}

	public void setRestricao(Restricao restricao) {
		this.restricao = restricao;
	}
	
	public Integer getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(Integer pontuacao) {
		this.pontuacao = pontuacao;
	}
	

	public List<Execucao> getExecucoes() {
		return execucoes;
	}

	public void setExecucoes(List<Execucao> execucoes) {
		this.execucoes = execucoes;
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
		Atividade other = (Atividade) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	
}
