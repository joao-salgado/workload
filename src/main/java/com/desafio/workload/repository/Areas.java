package com.desafio.workload.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desafio.workload.model.Area;

@Repository
public interface Areas extends JpaRepository<Area, Long> {

}
