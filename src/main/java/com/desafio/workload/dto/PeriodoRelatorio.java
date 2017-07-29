package com.desafio.workload.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.desafio.workload.model.Professor;

public class PeriodoRelatorio {

	@NotNull(message="A data de início é obrigatória")
	private LocalDate dataInicio;
	
	@NotNull(message="A data final é obrigatória")
	private LocalDate dataFim;
	
	@NotBlank(message="O siape é obrigatório")
	private String siape;
	
	private Professor professor;
	
	public Professor getProfessor() {
		return professor;
	}
	public void setProfessor(Professor professor) {
		this.professor = professor;
	}
	public LocalDate getDataInicio() {
		return dataInicio;
	}
	public void setDataInicio(LocalDate dataInicio) {
		this.dataInicio = dataInicio;
	}
	public LocalDate getDataFim() {
		return dataFim;
	}
	public void setDataFim(LocalDate dataFim) {
		this.dataFim = dataFim;
	}
	public String getSiape() {
		return siape;
	}
	public void setSiape(String siape) {
		this.siape = siape;
	}
	
}
