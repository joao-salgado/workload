package com.desafio.workload.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desafio.workload.model.Restricao;

@Repository
public interface Restricoes extends JpaRepository<Restricao, Long> {

}
