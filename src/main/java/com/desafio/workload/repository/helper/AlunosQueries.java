package com.desafio.workload.repository.helper;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.desafio.workload.dto.AlunoDTO;
import com.desafio.workload.model.Aluno;
import com.desafio.workload.repository.filter.AlunoFilter;

public interface AlunosQueries {
	
	public Page<Aluno> filtrar(AlunoFilter filtro, Pageable pageable);
	
	public List<AlunoDTO> porRaOuNome(String raOuNome); 
}
