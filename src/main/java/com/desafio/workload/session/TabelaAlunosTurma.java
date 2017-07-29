package com.desafio.workload.session;

import java.util.ArrayList;
import java.util.List;

import com.desafio.workload.model.Aluno;

class TabelaAlunosTurma {

	private String uuid;	
	private List<Aluno> itens = new ArrayList<Aluno>();
	
	
	public TabelaAlunosTurma(String uuid) {
		this.uuid = uuid;
	}

	public void adicionarItem(Aluno aluno) {
		if(!itens.contains(aluno)) {
			itens.add(0, aluno);
		}		
	}
	
	public void excluirItem(Aluno aluno) {
		itens.remove(aluno);
	}
	
	public int total() {
		return itens.size();
	}

	public Object getItens() {
		return itens;
	}

	public String getUuid() {
		return uuid;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((uuid == null) ? 0 : uuid.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TabelaAlunosTurma other = (TabelaAlunosTurma) obj;
		if (uuid == null) {
			if (other.uuid != null)
				return false;
		} else if (!uuid.equals(other.uuid))
			return false;
		return true;
	}
	
	
	
}