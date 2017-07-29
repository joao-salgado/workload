CREATE TABLE execucao (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	inicio DATE NOT NULL,
	termino DATE,
	codigo_professor BIGINT(20) NOT NULL,
	codigo_atividade BIGINT(20) NOT NULL,
	FOREIGN KEY (codigo_professor) REFERENCES professor(codigo),
    FOREIGN KEY (codigo_atividade) REFERENCES atividade(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;