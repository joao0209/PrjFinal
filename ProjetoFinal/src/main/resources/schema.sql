
drop table if exists usuarios;
create table usuarios (
	id identity primary key,
	username varchar(10) not null,
	password varchar(100) not null,
	nome varchar(40) not null,
	email varchar(40) not null,
	habilitado boolean not null default(false),
	creation_date timestamp not null default(now())
);
alter table usuarios add constraint username_unique unique(username);

drop table if exists papeis;
create table papeis (
	id identity primary key,
	usuario_id bigint,
	papel varchar(20) not null
);
alter table papeis add constraint usuario_papel_unique unique(usuario_id, papel);
alter table papeis add foreign key (usuario_id) references usuarios(id);

drop table if exists endereco;
create table endereco (
	id identity primary key,
	logradouro varchar(50) not null,
	numero bigint not null,
	cep varchar(10) not null,
	bairro varchar(50) not null
);

drop table if exists ordem_servico;
create table ordem_servico (
	id identity primary key,
	endereco_id bigint,
	usuario_id bigint,
	data_contratacao date not null,
	data_inicio_instalacao date,
	data_final_instalacao date
);

drop table if exists plano_instalacao;
create table plano_instalacao(
	id identity primary key,
	descricao varchar(20) not null --// (Monofasico - bifasico - trifasico)
);

drop table if exists horario_preferencial;
create table horario_preferencial(
	id identity primary key,
	descricao varchar(20) not null --// melhor horario para a instalacao ( manha - tarde - noite - madrugada)
);

drop table if exists instalador_ordem_servico;
create table instalador_ordem_servico(
	id identity primary key,
	dataAtribuicao timestamp not null default(now()),
	iniciado boolean not null default(false),
	finalizado boolean not null default(false),
	instalador_id bigint
);

alter table instalador_ordem_servico add foreign key (instalador_id) references usuarios(id);
