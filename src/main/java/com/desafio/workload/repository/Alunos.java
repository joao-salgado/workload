package com.desafio.workload.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desafio.workload.model.Aluno;
import com.desafio.workload.repository.helper.AlunosQueries;

@Repository
public interface Alunos extends JpaRepository<Aluno, Long>, AlunosQueries {

	public Optional<Aluno> findByRa(String ra);
	
}
