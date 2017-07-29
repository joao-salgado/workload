package com.desafio.workload.service;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.desafio.workload.model.Curso;
import com.desafio.workload.repository.Cursos;
import com.desafio.workload.service.exception.ImpossivelExcluirEntidadeException;
import com.desafio.workload.service.exception.NomeCursoJaCadastradoException;

@Service
public class CadastroCursoService {

	@Autowired
	private Cursos cursos;
	
	@Transactional
	public void salvar(Curso curso) {
		
		Optional<Curso> cursoOptional = cursos.findByNomeIgnoreCase(curso.getNome());
		if(cursoOptional.isPresent() && !cursoOptional.get().equals(curso)) {
			throw new NomeCursoJaCadastradoException("Este curso já está cadastrado");
		}
		
		cursos.save(curso);
	}

	@Transactional
	public void excluir(Curso curso) {
		try {
			cursos.delete(curso);
			cursos.flush();
		} catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossível apagar o curso. Está em alguma disciplina.");
		}
	}
	
}
