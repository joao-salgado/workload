CREATE TABLE area (
	codigo BIGINT(20) PRIMARY KEY,
	descricao VARCHAR(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE restricao (
	codigo BIGINT(20) PRIMARY KEY,
	descricao VARCHAR(200) NOT NULL,
	flag CHAR(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE atividade (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	descricao VARCHAR(1000) NOT NULL,
	pontuacao INTEGER NOT NULL,
	codigo_restricao BIGINT(20) NOT NULL,
	codigo_area BIGINT(20) NOT NULL,
	FOREIGN KEY (codigo_area) REFERENCES area(codigo),
	FOREIGN KEY (codigo_restricao) REFERENCES restricao(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO permissao VALUES(9, 'ROLE_DOCENTE');

INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) VALUES (1, 9);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) VALUES (2, 9);
