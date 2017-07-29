package com.desafio.workload.session;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.desafio.workload.model.Aluno;

@SessionScope
@Component
public class TabelasAlunosSession {

	private Set<TabelaAlunosTurma> tabelas = new HashSet<>();

	public void adicionarItem(String uuid, Aluno aluno) {
		TabelaAlunosTurma tabela = buscarTabelaPorUuid(uuid);
		tabela.adicionarItem(aluno);
		tabelas.add(tabela);
	}


	public void excluirItem(String uuid, Aluno aluno) {
		TabelaAlunosTurma tabela = buscarTabelaPorUuid(uuid);
		tabela.excluirItem(aluno);
	}

	@SuppressWarnings("unchecked")
	public List<Aluno> getItens(String uuid) {
		return (List<Aluno>) buscarTabelaPorUuid(uuid).getItens();
	}
		
	private TabelaAlunosTurma buscarTabelaPorUuid(String uuid) {
		TabelaAlunosTurma tabela = tabelas.stream()
				.filter(t -> t.getUuid().equals(uuid))
				.findAny()
				.orElse(new TabelaAlunosTurma(uuid));
		return tabela;
	}
	
}
