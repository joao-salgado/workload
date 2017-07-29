package com.desafio.workload.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="execucao")
@DynamicUpdate
public class Execucao implements Serializable {

		private static final long serialVersionUID = 1L;
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY) //Faz auto-increment
		private Long codigo;
		
		@NotNull(message="A data de início da atividade é obrigatória")
		@DateTimeFormat(pattern = "dd/MM/yyyy")
		@Temporal(TemporalType.DATE)
		private Date inicio;
		
		@DateTimeFormat(pattern = "dd/MM/yyyy")
		@Temporal(TemporalType.DATE)
		private Date termino;
		
		@ManyToOne
		@JoinColumn(name="codigo_atividade")
		private Atividade atividade;
		
		@ManyToOne
		@JoinColumn(name="codigo_professor")
		private Professor professor;
		
		private String observacao;
		
		public Long getCodigo() {
			return codigo;
		}

		public void setCodigo(Long codigo) {
			this.codigo = codigo;
		}

		public Date getInicio() {
			return inicio;
		}

		public void setInicio(Date inicio) {
			this.inicio = inicio;
		}

		public Date getTermino() {
			return termino;
		}

		public void setTermino(Date termino) {
			this.termino = termino;
		}

		public Atividade getAtividade() {
			return atividade;
		}

		public void setAtividade(Atividade atividade) {
			this.atividade = atividade;
		}

		public Professor getProfessor() {
			return professor;
		}

		public void setProfessor(Professor professor) {
			this.professor = professor;
		}

		public String getObservacao() {
			return observacao;
		}

		public void setObservacao(String observacao) {
			this.observacao = observacao;
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
			Execucao other = (Execucao) obj;
			if (codigo == null) {
				if (other.codigo != null)
					return false;
			} else if (!codigo.equals(other.codigo))
				return false;
			return true;
		}
	
		
}
