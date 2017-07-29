package com.desafio.workload.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desafio.workload.model.Dificuldade;

@Repository
public interface Dificuldades extends JpaRepository<Dificuldade, Long>{

}
