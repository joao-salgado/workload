package com.desafio.workload.repository.helper;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.desafio.workload.dto.AtividadePorMes;
import com.desafio.workload.model.Atividade;
import com.desafio.workload.repository.filter.AtividadeFilter;

public interface AtividadesQueries {
	
	public Page<Atividade> filtrar(AtividadeFilter filtro, Pageable pageable);

	public List<AtividadePorMes> totalPorMesInicio();
	
	public List<AtividadePorMes> totalPorMesTermino();
	
}
