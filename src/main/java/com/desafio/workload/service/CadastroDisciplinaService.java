package com.desafio.workload.service;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.desafio.workload.model.Disciplina;
import com.desafio.workload.repository.Disciplinas;
import com.desafio.workload.service.exception.ImpossivelExcluirEntidadeException;
import com.desafio.workload.service.exception.SiglaDisciplinaJaCadastradaException;

@Service
public class CadastroDisciplinaService {
	
	@Autowired
	private Disciplinas disciplinas;
	
	@Transactional
	public void salvar(Disciplina disciplina) {

		Optional<Disciplina> disciplinaOptional = disciplinas.findBySiglaIgnoreCase(disciplina.getSigla());
		if(disciplinaOptional.isPresent() && !disciplinaOptional.get().equals(disciplina)) {
			throw new SiglaDisciplinaJaCadastradaException("Esta disciplina provavelmente já está cadastrada, bom ao menos a sigla já está");
		}
		
		disciplinas.save(disciplina);
	}

	@Transactional
	public void excluir(Disciplina disciplina) {
		try {
			disciplinas.delete(disciplina);
			disciplinas.flush();
		} catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossível apagar disciplina. Ela já foi ou está sendo usada.");
		}
	}
	
}
