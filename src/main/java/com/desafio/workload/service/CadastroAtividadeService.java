package com.desafio.workload.service;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.desafio.workload.model.Atividade;
import com.desafio.workload.repository.Atividades;
import com.desafio.workload.service.exception.ImpossivelExcluirEntidadeException;

@Service
public class CadastroAtividadeService {

	@Autowired
	private Atividades atividades;
	
	@Transactional
	public void salvar(Atividade atividade) {
		
		atividades.save(atividade);
	}
	
	@Transactional
	public void excluir(Atividade atividade) {
		try {
			atividades.delete(atividade);
			atividades.flush();
		} catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossível apagar a atividade. Já foi utilizada.");
		}
	}
	
}
