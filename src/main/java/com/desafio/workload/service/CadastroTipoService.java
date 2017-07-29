package com.desafio.workload.service;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.desafio.workload.model.Tipo;
import com.desafio.workload.repository.Tipos;
import com.desafio.workload.service.exception.DescricaoTipoJaCadastradoException;
import com.desafio.workload.service.exception.ImpossivelExcluirEntidadeException;

@Service
public class CadastroTipoService {

	@Autowired
	private Tipos tipos;
	
	@Transactional
	public Tipo salvar(Tipo tipo) {
		
		Optional<Tipo> tipoOptional = tipos.findByDescricaoIgnoreCase(tipo.getDescricao());
		if(tipoOptional.isPresent() && !tipoOptional.get().equals(tipo)) {
			throw new DescricaoTipoJaCadastradoException("Tipo já está cadastrado");
		}
		
		return tipos.saveAndFlush(tipo);
	}
	
	@Transactional
	public void excluir(Tipo tipo) {
		try {
			tipos.delete(tipo);
			tipos.flush();
		} catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossível apagar este tipo de curso. Ele está sendo usado.");
		}
	}
	
}
