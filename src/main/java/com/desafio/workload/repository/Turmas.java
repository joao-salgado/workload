package com.desafio.workload.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.desafio.workload.model.Turma;
import com.desafio.workload.repository.helper.TurmasQueries;

@Repository
public interface Turmas extends JpaRepository<Turma, Long>, TurmasQueries {

	@Query(
	        value = "SELECT u.codigo_professor FROM usuario u WHERE u.codigo = :professor", 
	        nativeQuery=true
	    )
	public Long trasCodigoProfessor(@Param("professor")Long professorTemp);
	
}
