package com.desafio.workload.service;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.desafio.workload.model.Departamento;
import com.desafio.workload.repository.Departamentos;
import com.desafio.workload.service.exception.ImpossivelExcluirEntidadeException;
import com.desafio.workload.service.exception.SiglaDepartamentoJaCadastradaException;

@Service
public class CadastroDepartamentoService {
	
	@Autowired
	private Departamentos departamentos;
	
	@Transactional
	public void salvar(Departamento departamento) {
		
		Optional<Departamento> departamentoOptional = departamentos.findBySiglaIgnoreCase(departamento.getSigla());
		if(departamentoOptional.isPresent() && !departamentoOptional.get().equals(departamento)) {
			throw new SiglaDepartamentoJaCadastradaException("Este departamento já está cadastrado");
		}
		
		departamentos.save(departamento);
	}
	
	@Transactional
	public void excluir(Departamento departamento) {
		try {
			departamentos.delete(departamento);
			departamentos.flush();
		} catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossível apagar departamento. Está alocando algum curso e/ou professor.");
		}
	}
	
}
