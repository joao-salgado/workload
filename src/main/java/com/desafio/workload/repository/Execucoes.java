package com.desafio.workload.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.desafio.workload.model.Execucao;
import com.desafio.workload.repository.helper.ExecucoesQueries;

@Repository
public interface Execucoes extends JpaRepository<Execucao, Long>, ExecucoesQueries {

	@Query(
	        value = "SELECT e.codigo "
	        		+ "FROM execucao e "
	        		+ "WHERE e.codigo_atividade = :atividade AND YEAR(e.inicio) = :anoInicio",
	        nativeQuery=true
	    )
	List<Long> flags(@Param("atividade")Long codigo, 
					 @Param("anoInicio")int dataInicio) throws NullPointerException;

	public List<Execucao> findByCodigoIn(Long[] codigos);

}
