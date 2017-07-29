INSERT INTO area (codigo, descricao) VALUES (1, 'Ensino, Pesquisa e Extensão');
INSERT INTO area (codigo, descricao) VALUES (2, 'Produção Docente');
INSERT INTO area (codigo, descricao) VALUES (3, 'Orientações Acadêmicas');
INSERT INTO area (codigo, descricao) VALUES (4, 'Gestão Acadêmica');
INSERT INTO area (codigo, descricao) VALUES (5, 'Gestão Institucional');
INSERT INTO area (codigo, descricao) VALUES (6, 'Gestão Interinstitucional');

INSERT INTO restricao (codigo, descricao, flag) VALUES (1, 'Limitado a um programa por ano.', 'A');
INSERT INTO restricao (codigo, descricao, flag) VALUES (2, 'Limitado a um projeto por ano.', 'A');
INSERT INTO restricao (codigo, descricao, flag) VALUES (3, 'Benefício nos dois anos posteriores ao depósito - Limitado a um por ano', 'A');
INSERT INTO restricao (codigo, descricao, flag) VALUES (4, 'Benefício no ano posterior à publicação - Limitado a um por ano', 'A');
INSERT INTO restricao (codigo, descricao, flag) VALUES (5, 'Benefício no ano posterior à publicação e Limitado a dois artigos por ano.', 'B');
INSERT INTO restricao (codigo, descricao, flag) VALUES (6, 'Limitado a dois artigos por ano.', 'B');
INSERT INTO restricao (codigo, descricao, flag) VALUES (7, 'Limitado a um livro por ano.', 'A');
INSERT INTO restricao (codigo, descricao, flag) VALUES (8, 'Limitado a um capítulo por ano.', 'A');
INSERT INTO restricao (codigo, descricao, flag) VALUES (9, 'Limitado a um periódico por ano.', 'A');
INSERT INTO restricao (codigo, descricao, flag) VALUES (10, 'Limitada a duas produções por ano.', 'B');
INSERT INTO restricao (codigo, descricao, flag) VALUES (11, 'Limitado a cinco consultorias por ano.', 'C');
INSERT INTO restricao (codigo, descricao, flag) VALUES (12, 'Limitados a duas participações.', 'B');
INSERT INTO restricao (codigo, descricao, flag) VALUES (13, 'Limitado a duas atividades por semestre.', 'C');
INSERT INTO restricao (codigo, descricao, flag) VALUES (14, 'Sem restrição.', 'D');


INSERT INTO atividade (codigo, descricao, pontuacao, codigo_restricao, codigo_area) 
	VALUES (0, 'Docente Colaborador de Programa de Pós-Graduação (PPG) Stricto Sensu - Inclui participação formalizada e autorizada em PPG de outra instituição.', 40, 1, 1);
	
INSERT INTO atividade (codigo, descricao, pontuacao, codigo_restricao, codigo_area) 
	VALUES (0, 'Coordenador de Programa ou Projeto institucional de Ensino, Pesquisa ou Extensão, com fomento de órgão externo à UTFPR e registrado em uma Pró-Reitoria.', 40, 2, 1);
	
INSERT INTO atividade (codigo, descricao, pontuacao, codigo_restricao, codigo_area) 
	VALUES (0, 'Participante de Programa e/ou Projeto de Extensão Social/Inclusivo, registrado na Diretoria de Relações Empresariais e Comunitárias (Direc) e homologado pela Pró-Reitoria de Relações Empresariais e Comunitárias (Prorec) (Ex.: CIMCO, Empreendedorismo, Cultura e Esportes).', 15, 2, 1);
	
INSERT INTO atividade (codigo, descricao, pontuacao, codigo_restricao, codigo_area) 
	VALUES (0, 'Coordenador de Projeto com Entidades Internacionais, financiado por Agência Internacional ou Nacional de Fomento.', 40, 14, 1);
	
INSERT INTO atividade (codigo, descricao, pontuacao, codigo_restricao, codigo_area) 
	VALUES (0, 'Coordenador de Projeto Institucional com aprovação de fomento (PADCT, FNDCT, RHAE, CNPq, FINEP (Ex.: CT-INFRA, MDIC, Pró-Equipamentos, bolsa, ou auxílio a custeio) em edital de Pró-Reitoria (Ex.: PIBIC/PIBITI, PROEXT, PIBID, PIBEX, PET).', 15, 2, 1);
	
INSERT INTO atividade (codigo, descricao, pontuacao, codigo_restricao, codigo_area) 
	VALUES (0, 'Coordenador de Projeto com instituição de excelência internacional para dupla diplomação em nível de graduação e pós-graduação e para transferência de tecnologia.', 20, 2, 1);
	
INSERT INTO atividade (codigo, descricao, pontuacao, codigo_restricao, codigo_area) 
	VALUES (0, 'Coordenação de Projeto de Pesquisa, Desenvolvimento e Inovação (PD&I), em parceria com instituições.', 20, 2, 1);
	
INSERT INTO atividade (codigo, descricao, pontuacao, codigo_restricao, codigo_area) 
	VALUES (0, 'Participação em Projeto de PD&I de interesse institucional, financiado por instituições conveniadas.', 15, 2, 1);
	
INSERT INTO atividade (codigo, descricao, pontuacao, codigo_restricao, codigo_area) 
	VALUES (0, 'Coordenador de Projeto de Desenvolvimento de Produtos ou Processos (produtos, protótipos e processos implantados e não patenteados, software não registrados). Resultado deve ultrapassar o conceitual, demonstrando o aspecto de utilidade prática.', 15, 2, 1);
	
INSERT INTO atividade (codigo, descricao, pontuacao, codigo_restricao, codigo_area) 
	VALUES (0, 'Coordenação de Projeto de Pesquisa, financiado por CNPq, CAPES, FINEP, Fundação Araucária ou outros órgãos de fomento oficiais, executado na UTFPR.', 25, 2, 1);
	
INSERT INTO atividade (codigo, descricao, pontuacao, codigo_restricao, codigo_area) 
	VALUES (0, 'Participação em Projeto de Pesquisa financiado por CNPq, CAPES, FINEP, Fundação Araucária ou outros órgãos de fomento oficiais, executado na UTFPR.', 10, 2, 1);
	
INSERT INTO atividade (codigo, descricao, pontuacao, codigo_restricao, codigo_area) 
	VALUES (0, 'Coordenador de Projeto para Eventos Esportivos, Artísticos ou Culturais (Ex.: Jogos Universitários, Olimpíadas de Física, Matemática ou Química, apresentação de dança, coral, teatro e de música instrumental ou similares).', 15, 2, 1);
	
INSERT INTO atividade (codigo, descricao, pontuacao, codigo_restricao, codigo_area) 
	VALUES (0, 'Participante de Projeto para Eventos Esportivos, Artísticos ou Culturais (Ex.: Jogos Universitários, Olimpíadas de Física, Matemática ou Química, apresentação de dança, coral, teatro e de música instrumental ou similares).', 10, 2, 1);
	
INSERT INTO atividade (codigo, descricao, pontuacao, codigo_restricao, codigo_area) 
	VALUES (0, 'Coordenador de Programa de Especialização Lato Sensu não remunerado desenvolvido na UTFPR', 30, 14, 1);
	
	
	
INSERT INTO atividade (codigo, descricao, pontuacao, codigo_restricao, codigo_area) 
	VALUES (0, 'Inventos e demais produtos de pesquisa, com patente ou registro de software, cultivares, ou outro, depositado.', 25, 3, 2);
	
INSERT INTO atividade (codigo, descricao, pontuacao, codigo_restricao, codigo_area) 
	VALUES (0, 'Publicação de livro técnico/científico em língua estrangeira com ISBN. Livro(s) relevante(s) na área de avaliação do solicitante.', 60, 4, 2);
	
INSERT INTO atividade (codigo, descricao, pontuacao, codigo_restricao, codigo_area) 
	VALUES (0, 'Artigo em periódico classificado na área Qualis A1 ou artigo não classificado no Qualis selecionado, mas com JCR > 1.6.', 60, 4, 2);
	
INSERT INTO atividade (codigo, descricao, pontuacao, codigo_restricao, codigo_area) 
	VALUES (0, 'Artigo em periódico classificado na área Qualis A2 ou artigo não classificado no Qualis selecionado, mas com JCR > 1.2.', 50, 4, 2);
	
INSERT INTO atividade (codigo, descricao, pontuacao, codigo_restricao, codigo_area) 
	VALUES (0, 'Artigo em periódico classificado na área Qualis B1 ou artigo não classificado no Qualis selecionado, mas com JCR > 0.8.', 25, 5, 2);
	
INSERT INTO atividade (codigo, descricao, pontuacao, codigo_restricao, codigo_area) 
	VALUES (0, 'Artigo em periódico classificado na área Qualis B2 ou artigo não classificado no Qualis selecionado, mas com JCR > 0.4', 15, 5, 2);
	
INSERT INTO atividade (codigo, descricao, pontuacao, codigo_restricao, codigo_area) 
	VALUES (0, 'Publicação integral de artigo científico em anais de congressos, simpósios, seminários ou similares, em eventos de abrangência nacional ou internacional.', 5, 6, 2);
	
INSERT INTO atividade (codigo, descricao, pontuacao, codigo_restricao, codigo_area) 
	VALUES (0, 'Editoração ou organização de livro técnico/científico com ISBN ou anais de congressos de sociedades cientificas.', 15, 14, 2);
	
INSERT INTO atividade (codigo, descricao, pontuacao, codigo_restricao, codigo_area) 
	VALUES (0, 'Tradução de livro técnico/científico publicado por Editora.', 25, 14, 2);
	
INSERT INTO atividade (codigo, descricao, pontuacao, codigo_restricao, codigo_area) 
	VALUES (0, 'Publicação de livro técnico/científico em língua portuguesa com ISBN. Livro relevante na área de avaliação do postulante.', 30, 7, 2);
	
INSERT INTO atividade (codigo, descricao, pontuacao, codigo_restricao, codigo_area) 
	VALUES (0, 'Publicação de capítulo de livro técnico/científico em língua estrangeira, publicado após ser submetido ao conselho editorial em editora.', 15, 8, 2);
	
INSERT INTO atividade (codigo, descricao, pontuacao, codigo_restricao, codigo_area) 
	VALUES (0, 'Publicação de capítulo de livro técnico/científico em língua portuguesa, publicado após ser submetido ao conselho editorial em editora.', 10, 8, 2);
	
INSERT INTO atividade (codigo, descricao, pontuacao, codigo_restricao, codigo_area) 
	VALUES (0, 'Editor-chefe de periódico com ISSN e indexado (JCR) com abrangência internacional por, pelo menos, um ano.', 40, 9, 2);
	
INSERT INTO atividade (codigo, descricao, pontuacao, codigo_restricao, codigo_area) 
	VALUES (0, 'Editor-chefe de periódico indexado com abrangência nacional por pelo menos um ano.', 15, 9, 2);
	
INSERT INTO atividade (codigo, descricao, pontuacao, codigo_restricao, codigo_area) 
	VALUES (0, 'Editor-associado ou de área de periódico indexado com abrangência internacional por pelo menos um ano.', 10, 14, 2);
	
INSERT INTO atividade (codigo, descricao, pontuacao, codigo_restricao, codigo_area) 
	VALUES (0, 'Produção artística com homologação institucional e apresentação pública.', 15, 10, 2);
	
INSERT INTO atividade (codigo, descricao, pontuacao, codigo_restricao, codigo_area) 
	VALUES (0, 'Consultoria ad hoc para órgãos de fomento à pesquisa, ao ensino ou à extensão.', 5, 11, 2);
	
INSERT INTO atividade (codigo, descricao, pontuacao, codigo_restricao, codigo_area) 
	VALUES (0, 'Implementação ou execução de projeto didático-pedagógico nos cursos regulares: desenvolvimento de bancada didática, experimentos ou protótipos; projeto aprovado de implementação de ambientes de ensino/aprendizagem, laboratórios, oficinas, estúdios ou áreas para práticas culturais e esportivas; responsável por visitas técnicas, controle de patrimônio dos departamentos, vice-coordenação de curso.', 10, 14, 2);
	
INSERT INTO atividade (codigo, descricao, pontuacao, codigo_restricao, codigo_area) 
	VALUES (0, 'Elaboração de projeto de curso de capacitação para servidores, homologado pela Pró-Reitoria envolvida.', 10, 14, 2);
	
	
 
INSERT INTO atividade (codigo, descricao, pontuacao, codigo_restricao, codigo_area) 
	VALUES (0, 'Orientação de Empresa Hospedada no Hotel Tecnológico.', 5, 14, 3);

INSERT INTO atividade (codigo, descricao, pontuacao, codigo_restricao, codigo_area) 
	VALUES (0, 'Orientação de Empresa Júnior.', 5, 14, 3);
	
INSERT INTO atividade (codigo, descricao, pontuacao, codigo_restricao, codigo_area) 
	VALUES (0, 'Dissertação de mestrado concluída, sob orientação do docente.', 10, 14, 3);
	
INSERT INTO atividade (codigo, descricao, pontuacao, codigo_restricao, codigo_area) 
	VALUES (0, 'Dissertação de mestrado concluída, sob coorientação do docente.', 5, 14, 3);
	
INSERT INTO atividade (codigo, descricao, pontuacao, codigo_restricao, codigo_area) 
	VALUES (0, 'Tese de doutorado concluída, sob orientação do docente.', 15, 14, 3);
	
INSERT INTO atividade (codigo, descricao, pontuacao, codigo_restricao, codigo_area) 
	VALUES (0, 'Tese de doutorado concluída, sob coorientação do docente.', 10, 14, 3);
	
INSERT INTO atividade (codigo, descricao, pontuacao, codigo_restricao, codigo_area) 
	VALUES (0, 'Orientação de Trabalho de Conclusão de Curso (TCC) concluída em Curso Técnico e/ou de Graduação.', 5, 14, 3);
	
INSERT INTO atividade (codigo, descricao, pontuacao, codigo_restricao, codigo_area) 
	VALUES (0, 'Coorientação de TCC concluída em Curso Técnico e/ou de Graduação.', 5, 14, 3);
	
INSERT INTO atividade (codigo, descricao, pontuacao, codigo_restricao, codigo_area) 
	VALUES (0, 'Orientação de monitoria concluída.', 5, 14, 3);
	
INSERT INTO atividade (codigo, descricao, pontuacao, codigo_restricao, codigo_area) 
	VALUES (0, 'Orientação/supervisão de estágio concluída em Curso Técnico e/ou Graduação.', 5, 14, 3);
	
INSERT INTO atividade (codigo, descricao, pontuacao, codigo_restricao, codigo_area) 
	VALUES (0, 'Orientação de TCC concluída de Especialização.', 5, 14, 3);
	
INSERT INTO atividade (codigo, descricao, pontuacao, codigo_restricao, codigo_area) 
	VALUES (0, 'Supervisão de bolsista de programa de desenvolvimento científico regional, de recém-doutor e de pós-doutoramento.', 10, 14, 3);
	
INSERT INTO atividade (codigo, descricao, pontuacao, codigo_restricao, codigo_area) 
	VALUES (0, 'Orientação de bolsistas com titulação de mestre ou doutor, participante de projetos de P&D&I (Ex.: PADCT, CNPq, FINEP, Fundação Araucária ou empresas).', 5, 14, 3);
	
INSERT INTO atividade (codigo, descricao, pontuacao, codigo_restricao, codigo_area) 
	VALUES (0, 'Orientação de trabalho no Programa de Desenvolvimento Educacional (PDE) ou equivalente.', 5, 14, 3);

	
	
INSERT INTO atividade (codigo, descricao, pontuacao, codigo_restricao, codigo_area) 
	VALUES (0, 'Gestor de programas e projetos de captação de empresas para ações de desenvolvimento de tecnologia e/ou recursos humanos no Câmpus.', 60, 14, 4);
	
INSERT INTO atividade (codigo, descricao, pontuacao, codigo_restricao, codigo_area) 
	VALUES (0, 'Gestor de homologação de empresas concedentes de estágio aos alunos da UTFPR (gestor por curso).', 10, 14, 4);
	
INSERT INTO atividade (codigo, descricao, pontuacao, codigo_restricao, codigo_area) 
	VALUES (0, 'Gestor do Acompanhamento de Egresso no Câmpus.', 15, 14, 4);
	
INSERT INTO atividade (codigo, descricao, pontuacao, codigo_restricao, codigo_area) 
	VALUES (0, 'Organização e coordenação de Dia de Campo, exposição ou visita/reunião técnica, registrados na DIREC.', 10, 13, 4);
	
INSERT INTO atividade (codigo, descricao, pontuacao, codigo_restricao, codigo_area) 
	VALUES (0, 'Gestão de laboratórios multiusuários de pesquisa, homologados pela Pró-Reitoria de Pesquisa e Pós-Graduação (PROPPG).', 40, 14, 4);
	
INSERT INTO atividade (codigo, descricao, pontuacao, codigo_restricao, codigo_area) 
	VALUES (0, 'Gestão das atividades de estágios das licenciaturas.', 40, 14, 4);
	
INSERT INTO atividade (codigo, descricao, pontuacao, codigo_restricao, codigo_area) 
	VALUES (0, 'Gestão de laboratórios de ensino para cursos regulares.', 10, 14, 4);
	
INSERT INTO atividade (codigo, descricao, pontuacao, codigo_restricao, codigo_area) 
	VALUES (0, 'Tutor de orientação de aluno de mobilidade em projeto de colaboração internacional.', 10, 14, 4);
	
INSERT INTO atividade (codigo, descricao, pontuacao, codigo_restricao, codigo_area) 
	VALUES (0, 'Gestão das atividades de TCC (gestor por curso).', 20, 14, 4);
	
INSERT INTO atividade (codigo, descricao, pontuacao, codigo_restricao, codigo_area) 
	VALUES (0, 'Gestão das atividades complementares (gestor por curso).', 20, 14, 4);
	
INSERT INTO atividade (codigo, descricao, pontuacao, codigo_restricao, codigo_area) 
	VALUES (0, 'Gestão de acompanhamento de estágios (PRAE) bacharelado.', 20, 14, 4);
	
INSERT INTO atividade (codigo, descricao, pontuacao, codigo_restricao, codigo_area) 
	VALUES (0, 'Participante de Comitê de Ética em Pesquisa (CEP) e Comitê de Ética no uso de Animais.', 20, 14, 4);
	
INSERT INTO atividade (codigo, descricao, pontuacao, codigo_restricao, codigo_area) 
	VALUES (0, 'Assessoria de cursos regulares (formalizada junto à Coordenação/Departamento Acadêmico)', 15, 14, 4);
	
INSERT INTO atividade (codigo, descricao, pontuacao, codigo_restricao, codigo_area) 
	VALUES (0, 'Elaboração e aplicação de avaliação de nível de proficiência em língua estrangeira.', 15, 14, 4);
	
INSERT INTO atividade (codigo, descricao, pontuacao, codigo_restricao, codigo_area) 
	VALUES (0, 'Gestão de grupos de disciplinas.', 5, 14, 4);
	
INSERT INTO atividade (codigo, descricao, pontuacao, codigo_restricao, codigo_area) 
	VALUES (0, 'Participação em Colegiado de Curso e/ou Conselho Departamental.', 5, 14, 4);
	
INSERT INTO atividade (codigo, descricao, pontuacao, codigo_restricao, codigo_area) 
	VALUES (0, 'Participação em Núcleo Docente Estruturante (NDE).', 15, 14, 4);
	
INSERT INTO atividade (codigo, descricao, pontuacao, codigo_restricao, codigo_area) 
	VALUES (0, 'Coordenação da elaboração/revisão dos projetos pedagógicos de novos cursos de Graduação e Pós-Graduação (modalidades presenciais e EAD).', 15, 14, 4);
	
	

INSERT INTO atividade (codigo, descricao, pontuacao, codigo_restricao, codigo_area) 
	VALUES (0, 'Participante de conselhos, comitês e afins em órgãos de fomento à pesquisa, ao ensino ou à extensão, com comprovação de participação efetiva.', 10, 12, 5);
	
INSERT INTO atividade (codigo, descricao, pontuacao, codigo_restricao, codigo_area) 
	VALUES (0, 'Representação institucional com portaria para finalidade específica de representação (representante da instituição em nível técnico ou treinador em eventos esportivos, artísticos e culturais - ex.: Jogos Universitários, Olimpíadas de Física, Matemática e Química, apresentação de dança, coral, teatro e de música instrumental ou similares.', 15, 14, 5);
	
INSERT INTO atividade (codigo, descricao, pontuacao, codigo_restricao, codigo_area) 
	VALUES (0, 'Participação em comissão técnica ou de programas de eventos técnicos ou científicos, devidamente comprovados pela diretoria competente ou associação de classe.', 5, 12, 5);

INSERT INTO atividade (codigo, descricao, pontuacao, codigo_restricao, codigo_area) 
	VALUES (0, 'Membro da comissão organizadora de congressos, workshops, seminários, mostras, exposições, colóquios, jornadas, encontros, semanas, ou outros, em âmbito regional ou local.', 5, 12, 5);
	
INSERT INTO atividade (codigo, descricao, pontuacao, codigo_restricao, codigo_area) 
	VALUES (0, 'Membros eleitos dos órgãos deliberativos da UTFPR (Couni, Cogep, COPPG, Coemp, Coplad), bem como em comissões instituídas pelo MEC, Andifes ou similares.', 15, 14, 5);
	
INSERT INTO atividade (codigo, descricao, pontuacao, codigo_restricao, codigo_area) 
	VALUES (0, 'Cargo de Ouvidor nos Câmpus.', 40, 14, 5);
	
INSERT INTO atividade (codigo, descricao, pontuacao, codigo_restricao, codigo_area) 
	VALUES (0, 'Presidente de órgãos ou comissões permanentes de apoio. (Ex.: CPPD, CPA, NPPD, Comissão Professor Associado, Comissão Professor Titular ou similares e Comissão de Ética).', 40, 14, 5);
	
INSERT INTO atividade (codigo, descricao, pontuacao, codigo_restricao, codigo_area) 
	VALUES (0, 'Membro titular de órgãos ou comissões permanentes de apoio. (Ex.: CPPD, CPA, NPPD, Comissão Professor Associado, Comissão Professor Titular ou similares.', 10, 14, 5);
	
INSERT INTO atividade (codigo, descricao, pontuacao, codigo_restricao, codigo_area) 
	VALUES (0, 'Presidência de comissão designada por ato da administração da UTFPR. Por evento comprovado com portaria ou certificado.', 10, 14, 5);
	
INSERT INTO atividade (codigo, descricao, pontuacao, codigo_restricao, codigo_area) 
	VALUES (0, 'Membros de comissão designada por ato da administração, ou por atividade realizada e comprovada por portaria ou declaração emitida por Coordenação de Curso ou Chefia de Departamento Acadêmico.', 5, 14, 5);
	
INSERT INTO atividade (codigo, descricao, pontuacao, codigo_restricao, codigo_area) 
	VALUES (0, 'Atribuição de função designada por ato da administração da UTFPR (Ex.: Ascom, Asavi, entre outras).', 40, 14, 5);
	
	
	
INSERT INTO atividade (codigo, descricao, pontuacao, codigo_restricao, codigo_area) 
	VALUES (0, 'Membro de comissões ou organismos internacionais reconhecidos (Unesco, ONU, FAO, IEEE, IEE ou similares), como coordenador de comissão ou de área.', 15, 2, 6);	
	
INSERT INTO atividade (codigo, descricao, pontuacao, codigo_restricao, codigo_area) 
	VALUES (0, 'Participante ou representante titular da UTFPR, designado por portaria, em comitês permanentes em órgãos como MEC, CAPES, FINEP, CNPq, CREA ou outros conselhos profissionais.', 15, 14, 6);
	
INSERT INTO atividade (codigo, descricao, pontuacao, codigo_restricao, codigo_area) 
	VALUES (0, 'Membro do Conselho Deliberativo ou Fiscal da FUNTEF-PR.', 20, 14, 6);
	
INSERT INTO atividade (codigo, descricao, pontuacao, codigo_restricao, codigo_area) 
	VALUES (0, 'Presidente da Associação dos Servidores ou Presidente do Sindicato dos Docentes da UTFPR.', 20, 14, 6);
	
	
	
	
	
