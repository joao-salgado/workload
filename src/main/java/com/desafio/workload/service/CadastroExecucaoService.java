package com.desafio.workload.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.desafio.workload.model.Execucao;
import com.desafio.workload.model.Flag;
import com.desafio.workload.repository.Execucoes;
import com.desafio.workload.service.exception.DataFimAnteriorAInicioException;
import com.desafio.workload.service.exception.RestricaoEncontradaException;

@Service
public class CadastroExecucaoService {

	@Autowired
	private Execucoes execucoes;
	private List<Long> lista;
	
	@Transactional
	public void salvar(Execucao execucao) {
		
		if(execucao.getTermino() != null) {
			
			if(execucao.getInicio().after(execucao.getTermino())) {
				throw new DataFimAnteriorAInicioException("A data de término deve ser igual ou posterior à data de início");
			}
			
		}
		
		String data = trasAnoDaData(execucao);	
		lista = execucoes.flags(execucao.getAtividade().getCodigo(), Integer.parseInt(data));		
		if(execucao.getAtividade().getRestricao().getFlag().equals(Flag.A)) {
			flagA(execucao);			
		} else if(execucao.getAtividade().getRestricao().getFlag().equals(Flag.B)) {
			flagB(execucao);
		} else if(execucao.getAtividade().getRestricao().getFlag().equals(Flag.C)) {
			flagC(execucao);
		} else {
			execucoes.save(execucao);
		}
		
	}
	
	private void flagC(Execucao execucao) {
		if(lista.size() != 5) {
			execucoes.save(execucao);
		} else {
			throw new RestricaoEncontradaException(execucao.getAtividade().getRestricao().getDescricao());
		}
	}

	private void flagB(Execucao execucao) {
		if(lista.size() != 2) {
			execucoes.save(execucao);
		} else {
			throw new RestricaoEncontradaException(execucao.getAtividade().getRestricao().getDescricao());
		}
	}

	private void flagA(Execucao execucao) {		
		if(lista.isEmpty()) {
			execucoes.save(execucao);
		} else {
			throw new RestricaoEncontradaException(execucao.getAtividade().getRestricao().getDescricao());
		}
	}

	private String trasAnoDaData(Execucao execucao) {
		String data = "";
		String dataEmString = execucao.getInicio().toString();
		
		for(int i = dataEmString.lastIndexOf("T") + 2; i < dataEmString.length(); i++) {
			data += dataEmString.charAt(i);
		}
		System.out.println(data);
		
		
		return data;
	}


	@Transactional
	public void alterarTermino(Long[] codigos, TerminoAtividade terminoAtividade) {
		terminoAtividade.executar(codigos, execucoes);		
		
	}
	
}
