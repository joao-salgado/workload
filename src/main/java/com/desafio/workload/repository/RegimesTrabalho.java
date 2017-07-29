package com.desafio.workload.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.desafio.workload.model.RegimeTrabalho;

@Repository
public interface RegimesTrabalho extends JpaRepository<RegimeTrabalho, Long>{

}
