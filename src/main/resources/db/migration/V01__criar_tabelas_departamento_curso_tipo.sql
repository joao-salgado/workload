CREATE TABLE departamento (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	sigla VARCHAR(10) NOT NULL,
	nome VARCHAR(99) NOT NULL,
	bloco VARCHAR(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE tipo (
	codigo INTEGER PRIMARY KEY AUTO_INCREMENT,
	descricao VARCHAR(99) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE curso (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	nome VARCHAR(99) NOT NULL,
	codigo_departamento BIGINT(20) NOT NULL,
	codigo_tipo INTEGER NOT NULL,
	FOREIGN KEY (codigo_departamento) REFERENCES departamento(codigo),
	FOREIGN KEY (codigo_tipo) REFERENCES tipo(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO tipo VALUES(0, 'Graduação');
INSERT INTO tipo VALUES(0, 'Pós-Graduação');
INSERT INTO tipo VALUES(0, 'Mestrado');
INSERT INTO tipo VALUES(0, 'Tecnológico');

INSERT INTO departamento(sigla, nome, bloco) VALUES ('DAASA', 'Departamento de assuntos administrativos', 'A');
