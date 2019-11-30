delete from usuarios;
insert into usuarios (id, username, password, nome, email) values (1, 'doug', 'ytap', 'Doug Funnie', 'doug@jumbo.com');
insert into usuarios (id, username, password, nome, email) values (2, 'tintin', 'ulim', 'Tintin', 'tintin@example.com');
insert into usuarios (id, username, password, nome, email) values (3, 'tia', 'akol', 'Tia Loca', 'locaTia@nofaa.com');

delete from papeis;
insert into papeis (usuario_id, papel) values (1, 'ROLE_USER');
insert into papeis (usuario_id, papel) values (2, 'ROLE_ADMIN');
insert into papeis (usuario_id, papel) values (3, 'ROLE_OPERATION');

delete from plano_instalacao;
insert into plano_instalacao(descricao) values 
('Monofasico'),('bifasico'), ('trifasico');

delete from horario_preferencial;
insert into horario_preferencial(descricao) values 
('manha'),('tarde'), ('noite'), ('madrugada');