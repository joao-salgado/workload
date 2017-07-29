package com.desafio.workload.service;

import java.util.Date;

import com.desafio.workload.repository.Execucoes;

public enum TerminoAtividade {

	ATIVAR {
		@Override
		public void executar(Long[] codigos, Execucoes execucoes) {
			execucoes.findByCodigoIn(codigos).forEach(u -> u.setTermino(new Date()));
			
		}
	},
	DESATIVAR {
		@Override
		public void executar(Long[] codigos, Execucoes execucoes) {
			execucoes.findByCodigoIn(codigos).forEach(u -> u.setTermino(null));
			
		}
	};
	
	public abstract void executar(Long[] codigos, Execucoes execucoes);
	
}
