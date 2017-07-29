package com.desafio.workload.service;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.desafio.workload.model.Turma;
import com.desafio.workload.repository.Turmas;
import com.desafio.workload.service.exception.ImpossivelExcluirEntidadeException;

@Service
public class CadastroTurmaService {

	@Autowired
	private Turmas turmas;
	
	
	@Transactional
	public void salvar(Turma turma) {
		turmas.save(turma);
	}
	
	@Transactional
	public void excluir(Turma turma) {
		try {
			turmas.delete(turma);
			turmas.flush();
		} catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossível apagar essa turma.");
		}
	}
	
}
