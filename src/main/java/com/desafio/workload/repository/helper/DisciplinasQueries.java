package com.desafio.workload.repository.helper;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.desafio.workload.model.Disciplina;
import com.desafio.workload.repository.filter.DisciplinaFilter;

public interface DisciplinasQueries {
	
	public Page<Disciplina> filtrar(DisciplinaFilter filtro, Pageable pageable);
	
}
