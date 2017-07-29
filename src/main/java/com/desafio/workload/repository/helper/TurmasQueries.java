package com.desafio.workload.repository.helper;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.desafio.workload.model.Turma;
import com.desafio.workload.repository.filter.TurmaFilter;

public interface TurmasQueries {

	public Page<Turma> filtrar(TurmaFilter filtro, Pageable pageable);

	public Turma buscarComAlunos(Long codigo);
	
}
