package com.desafio.workload.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desafio.workload.model.Curso;
import com.desafio.workload.repository.helper.CursosQueries;

@Repository
public interface Cursos extends JpaRepository<Curso, Long>, CursosQueries {

	public Optional<Curso> findByNomeIgnoreCase(String nome);
	
}
