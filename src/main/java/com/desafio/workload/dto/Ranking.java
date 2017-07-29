package com.desafio.workload.dto;

import java.time.LocalDate;

import com.desafio.workload.model.Departamento;

public class Ranking {

	private LocalDate dataInicio;
	private LocalDate dataFim;
	
	private Departamento departamento;

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

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}
	
}
