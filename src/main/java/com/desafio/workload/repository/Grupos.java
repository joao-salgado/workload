package com.desafio.workload.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desafio.workload.model.Grupo;

public interface Grupos extends JpaRepository<Grupo, Long> {

}
