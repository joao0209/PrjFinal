
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

