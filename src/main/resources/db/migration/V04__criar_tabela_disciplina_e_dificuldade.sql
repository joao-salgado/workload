CREATE TABLE dificuldade (
	codigo INTEGER PRIMARY KEY AUTO_INCREMENT,
	nivel VARCHAR(99) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE disciplina (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(99) NOT NULL,
	creditos INTEGER NOT NULL,
	codigo_curso BIGINT(20) NOT NULL,
	codigo_dificuldade INTEGER NOT NULL,
	FOREIGN KEY (codigo_curso) REFERENCES curso(codigo),
	FOREIGN KEY (codigo_dificuldade) REFERENCES dificuldade(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO dificuldade VALUES(0, 'Fácil');
INSERT INTO dificuldade VALUES(0, 'Médio');
INSERT INTO dificuldade VALUES(0, 'Difícil');
