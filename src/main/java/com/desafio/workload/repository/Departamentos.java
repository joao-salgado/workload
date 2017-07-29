package com.desafio.workload.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.desafio.workload.model.Departamento;
import com.desafio.workload.repository.helper.DepartamentosQueries;

@Repository
public interface Departamentos extends JpaRepository<Departamento, Long>, DepartamentosQueries {
	
	public Optional<Departamento> findBySiglaIgnoreCase(String sigla);
	
}
