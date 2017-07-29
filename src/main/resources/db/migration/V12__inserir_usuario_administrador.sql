INSERT INTO professor(siape, titulo, codigo_regime, codigo_departamento) VALUES ( 
	'123.456.789', 
	'DOUTOR', 
	1,
	(SELECT codigo FROM departamento WHERE sigla LIKE 'DAASA')
);

INSERT INTO usuario (nome, email, senha, ativo, codigo_professor) VALUES ('Admin', 'admin@workload.com', 
	'$2a$10$g.wT4R0Wnfel1jc/k84OXuwZE02BlACSLfWy6TycGPvvEKvIm86SG', TRUE,(SELECT codigo FROM professor WHERE siape LIKE '123.456.789'));