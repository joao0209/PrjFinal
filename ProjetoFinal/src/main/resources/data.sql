delete from usuarios;
insert into usuarios (id, username, password, nome, email,habilitado) values (1, 'doug', 'ytap', 'Doug Funnie', 'doug@jumbo.com',1);
insert into usuarios (id, username, password, nome, email,habilitado) values (2, 'tintin', 'ulim', 'Tintin', 'tintin@example.com',1);
insert into usuarios (id, username, password, nome, email,habilitado) values (3, 'tia', 'akol', 'Tia Loca', 'locaTia@nofaa.com',1);

delete from papeis;
insert into papeis (usuario_id, papel) values (1, 'ROLE_INSTALLER');
insert into papeis (usuario_id, papel) values (2, 'ROLE_DIRECTOR');
insert into papeis (usuario_id, papel) values (3, 'ROLE_CLIENT');

delete from endereco;
insert into endereco (
	logradouro,
	numero,
	cep,
	bairro)
values(
'logTeste',
123,
'123412',
'teste'
);

delete from ordem_servico;
insert into ordem_servico (
endereco_id,
cliente_id,
plano,
instalador_id,
data_contratacao)
values(
1,
3,
1,
0,
now()
);