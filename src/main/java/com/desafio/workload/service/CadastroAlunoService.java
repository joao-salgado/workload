package com.desafio.workload.service;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.desafio.workload.model.Aluno;
import com.desafio.workload.repository.Alunos;
import com.desafio.workload.service.exception.ImpossivelExcluirEntidadeException;
import com.desafio.workload.service.exception.RaAlunoJaCadastradoException;

@Service
public class CadastroAlunoService {

	@Autowired
	private Alunos alunos;
	
	@Transactional
	public void salvar(Aluno aluno) {
		
		
		Optional<Aluno> alunoOptional = alunos.findByRa(aluno.getRa());
		if(alunoOptional.isPresent() && alunoOptional.get().equals(aluno)) {
			throw new RaAlunoJaCadastradoException("Este RA já está cadastrado");
		}
		
		alunos.save(aluno);
	}
	
	@Transactional
	public void excluir(Aluno aluno) {
		try {
			alunos.delete(aluno);
			alunos.flush();
		} catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossível apagar aluno. Está alocado algum curso e/ou turma.");
		}
	}
	
	
}
