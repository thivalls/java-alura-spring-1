INSERT INTO USUARIO(nome, email, senha) VALUES('Aluno', 'aluno@email.com', '$2a$10$zsBm.zEwf2TZ82YLQrlCV.QGApNWnqXk8rggp2w13D3JJCwtRGlD6');
INSERT INTO USUARIO(nome, email, senha) VALUES('Administrador', 'admin@email.com', '$2a$10$zsBm.zEwf2TZ82YLQrlCV.QGApNWnqXk8rggp2w13D3JJCwtRGlD6');

INSERT INTO perfil(id, nome) values(1, 'ROLE_ALUNO');
INSERT INTO perfil(id, nome) values(2, 'ROLE_ADMINISTRATOR');

INSERT INTO usuario_perfis(usuario_id, perfis_id) VALUES(1, 1);
INSERT INTO usuario_perfis(usuario_id, perfis_id) VALUES(2, 2);

INSERT INTO CURSO(nome, categoria) VALUES('Spring Boot', 'Programação');
INSERT INTO CURSO(nome, categoria) VALUES('HTML 5', 'Front-end');

INSERT INTO TOPICO(titulo, mensagem, data_criacao, status, autor_id, curso_id) VALUES('Dúvida', 'aa Erro ao criar projeto', '2019-05-05 18:00:00', 'NAO_RESPONDIDO', 1, 1);
INSERT INTO TOPICO(titulo, mensagem, data_criacao, status, autor_id, curso_id) VALUES('Dúvida 2', 'beira Tag HTML', '2019-05-05 21:00:00', 'NAO_RESPONDIDO', 1, 1);
INSERT INTO TOPICO(titulo, mensagem, data_criacao, status, autor_id, curso_id) VALUES('Dúvida 3', 'beira Tag HTML', '2019-05-05 20:00:00', 'NAO_RESPONDIDO', 1, 2);