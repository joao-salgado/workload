package com.desafio.workload.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.desafio.workload.model.Disciplina;
import com.desafio.workload.repository.helper.DisciplinasQueries;

@Repository
public interface Disciplinas extends JpaRepository<Disciplina, Long>, DisciplinasQueries {
	
	public Optional<Disciplina> findBySiglaIgnoreCase(String sigla);

	//public List<Disciplina> findBySiglaStartingWithIgnoreCase(String sigla);

	public List<Disciplina> findByNomeStartingWithIgnoreCase(String nome); 
	
}
