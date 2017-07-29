CREATE TABLE regime_trabalho (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	descricao VARCHAR(99) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO regime_trabalho VALUES(0, 'Tempo parcial de 20 horas semanais de trabalho');
INSERT INTO regime_trabalho VALUES(0, 'Tempo integral de 40 horas semanais de trabalho');
INSERT INTO regime_trabalho VALUES(0, 'Dedicação exclusiva, com 40 horas semanais de trabalho');

CREATE TABLE professor (
	codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	siape VARCHAR(99) NOT NULL,
	nome VARCHAR(99) NOT NULL,
	titulo VARCHAR(99),
	codigo_departamento BIGINT(20) NOT NULL,
	codigo_regime BIGINT(20) NOT NULL, 
	FOREIGN KEY (codigo_departamento) REFERENCES departamento(codigo),
	FOREIGN KEY (codigo_regime) REFERENCES regime_trabalho(codigo)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
