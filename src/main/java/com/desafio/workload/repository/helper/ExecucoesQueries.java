package com.desafio.workload.repository.helper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.desafio.workload.model.Execucao;
import com.desafio.workload.model.Professor;
import com.desafio.workload.repository.filter.ExecucaoFilter;

public interface ExecucoesQueries {

	public Page<Execucao> filtrar(ExecucaoFilter filtro, Pageable pageable, Professor professor);
	
}
