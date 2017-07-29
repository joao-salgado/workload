package com.desafio.workload.repository.helper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.desafio.workload.model.Curso;
import com.desafio.workload.repository.filter.CursoFilter;

public interface CursosQueries {
	
	public Page<Curso> filtrar(CursoFilter filtro, Pageable pageable);

}
