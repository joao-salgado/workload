package com.desafio.workload.repository.helper;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.desafio.workload.model.Tipo;
import com.desafio.workload.repository.filter.TipoFilter;

public interface TiposQueries {
	
	public Page<Tipo> filtrar(TipoFilter filtrar, Pageable pageable);
	
}
