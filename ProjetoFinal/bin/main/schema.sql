
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

drop table if exists plano;
create table plano (
	id identity
	nome varchar(25) not null
);

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
	cliente_id bigint not null,
	plano_id bigint not null,
	instalador_id bigint not null,
	data_contratacao date not null,
	data_inicio_instalacao date,
	data_final_instalacao date
);



