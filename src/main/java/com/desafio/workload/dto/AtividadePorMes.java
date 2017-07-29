package com.desafio.workload.dto;

public class AtividadePorMes {
	
	private String mes;
	private Integer total;
	
	public AtividadePorMes() {
		
	}
	
	public AtividadePorMes(String mes, Integer total) {
		this.mes = mes;
		this.total = total;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}
	

}
