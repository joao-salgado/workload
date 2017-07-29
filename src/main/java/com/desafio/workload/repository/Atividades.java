package com.desafio.workload.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desafio.workload.model.Atividade;
import com.desafio.workload.repository.helper.AtividadesQueries;

@Repository
public interface Atividades extends JpaRepository<Atividade, Long>, AtividadesQueries {

}
