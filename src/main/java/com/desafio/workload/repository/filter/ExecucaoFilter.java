package com.desafio.workload.repository.filter;

import com.desafio.workload.model.Area;
import com.desafio.workload.model.Restricao;

public class ExecucaoFilter {

	/*private LocalDate desde;
	private LocalDate ate;*/
	
	private String descricao;
	private Integer pontuacao;
	private Area area;
	private Restricao restricao;
	
	/*public LocalDate getDesde() {
		return desde;
	}
	public void setDesde(LocalDate desde) {
		this.desde = desde;
	}
	public LocalDate getAte() {
		return ate;
	}
	public void setAte(LocalDate ate) {
		this.ate = ate;
	}*/
	
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public Integer getPontuacao() {
		return pontuacao;
	}
	public void setPontuacao(Integer pontuacao) {
		this.pontuacao = pontuacao;
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
	
	
	
	
}
