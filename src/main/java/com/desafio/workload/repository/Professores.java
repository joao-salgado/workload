package com.desafio.workload.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.desafio.workload.model.Professor;
import com.desafio.workload.repository.helper.ProfessoresQueries;

@Repository
public interface Professores extends JpaRepository<Professor, Long>, ProfessoresQueries {

	public Optional<Professor> findBySiape(String siape);

	
	@Query(
	        value = "UPDATE professor p SET p.codigo_departamento = :departamento, "
	        		+ "p.codigo_regime = :regime, p.siape = :siape, p.titulo = :titulo, "
	        		+ "WHERE p.siape = :siape", 
	        nativeQuery=true
	    )
	public void alterar(@Param("departamento")Long departamento, 
						@Param("regime")Long regime, 
						@Param("siape")String siape, 
						@Param("titulo")String titulo);
	
}
