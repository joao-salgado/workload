package com.desafio.workload.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desafio.workload.model.Tipo;
import com.desafio.workload.repository.helper.TiposQueries;

@Repository
public interface Tipos extends JpaRepository<Tipo, Long>, TiposQueries {

	public Optional<Tipo> findByDescricaoIgnoreCase(String descricao);
	
}
