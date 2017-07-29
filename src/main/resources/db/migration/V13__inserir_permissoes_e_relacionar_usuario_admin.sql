INSERT INTO permissao VALUES(1, 'ROLE_CADASTRAR_USUARIO');
INSERT INTO permissao VALUES(2, 'ROLE_CADASTRAR_PROFESSOR');
INSERT INTO permissao VALUES(3, 'ROLE_CADASTRAR_DEPARTAMENTO');
INSERT INTO permissao VALUES(4, 'ROLE_CADASTRAR_CURSO');
INSERT INTO permissao VALUES(5, 'ROLE_CADASTRAR_TIPO_CURSO');
INSERT INTO permissao VALUES(6, 'ROLE_CADASTRAR_DISCIPLINA');
INSERT INTO permissao VALUES(7, 'ROLE_CADASTRAR_TURMA');
INSERT INTO permissao VALUES(8, 'ROLE_CADASTRAR_ALUNO');

INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) VALUES (2, 1);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) VALUES (2, 2);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) VALUES (2, 3);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) VALUES (2, 4);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) VALUES (2, 5);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) VALUES (2, 6);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) VALUES (2, 7);
INSERT INTO grupo_permissao (codigo_grupo, codigo_permissao) VALUES (2, 8);


INSERT INTO usuario_grupo (codigo_usuario, codigo_grupo) VALUES ( 
	(SELECT codigo FROM usuario WHERE email = 'admin@workload.com'), 2);