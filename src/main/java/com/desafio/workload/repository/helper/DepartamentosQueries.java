package com.desafio.workload.repository.helper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.desafio.workload.model.Departamento;
import com.desafio.workload.repository.filter.DepartamentoFilter;

public interface DepartamentosQueries {

	public Page<Departamento> filtrar(DepartamentoFilter filtro, Pageable pageable);
		
}
