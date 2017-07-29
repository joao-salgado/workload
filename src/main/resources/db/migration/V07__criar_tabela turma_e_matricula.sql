CREATE TABLE turma (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	sigla VARCHAR(6) NOT NULL,
	quantidade_aluno INTEGER NOT NULL,
	ano INTEGER NOT NULL,
	semestre INTEGER NOT NULL,
	codigo_disciplina BIGINT(20) NOT NULL,
	codigo_professor BIGINT(20) NOT NULL,
	FOREIGN KEY (codigo_disciplina) REFERENCES disciplina(codigo),
	FOREIGN KEY (codigo_professor) REFERENCES professor(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE matricula (
	codigo_aluno BIGINT(20) NOT NULL,
	codigo_turma BIGINT(20) NOT NULL,
	PRIMARY KEY (codigo_aluno, codigo_turma),
	FOREIGN KEY (codigo_aluno) REFERENCES aluno(codigo),
	FOREIGN KEY (codigo_turma) REFERENCES turma(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

