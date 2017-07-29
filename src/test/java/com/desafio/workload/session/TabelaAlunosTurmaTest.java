package com.desafio.workload.session;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.desafio.workload.model.Aluno;

public class TabelaAlunosTurmaTest {

	private TabelaAlunosTurma tabelaAlunosTurma;

	@Before
	public void setUp() {
		this.tabelaAlunosTurma = new TabelaAlunosTurma("1");
	}
	
	@Test
	public void deveManterTamanhoDaListaParaMesmosAlunos() throws Exception {
		Aluno c1 = new Aluno();
		c1.setCodigo(1L);
		
		tabelaAlunosTurma.adicionarItem(c1);
		tabelaAlunosTurma.adicionarItem(c1);
		
		assertEquals(1, tabelaAlunosTurma.total());
	}
	
	@Test
	public void deveExcluirAluno() throws Exception{
		
		Aluno a1 = new Aluno();
		a1.setCodigo(1L);
		
		Aluno a2 = new Aluno();
		a2.setCodigo(2L);
		
		Aluno a3 = new Aluno();
		a2.setCodigo(3L);

		tabelaAlunosTurma.adicionarItem(a1);
		tabelaAlunosTurma.adicionarItem(a2);
		tabelaAlunosTurma.adicionarItem(a3);
		
		assertEquals(3, tabelaAlunosTurma.total());
		tabelaAlunosTurma.excluirItem(a1);
		assertEquals(2, tabelaAlunosTurma.total());
		
	}
	
}
