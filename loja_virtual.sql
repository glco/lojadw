create table if not exists loja_virtual.categoria(
	id  serial not null primary key,
	descricao varchar(255) not null
);

create table if not exists loja_virtual.administrador(
	id  serial not null primary key,
	login varchar(55) unique not null,
	senha varchar(55) not null
);
create table if not exists loja_virtual.produto(
	id  serial not null primary key,
	idcategoria int not null,
	nome varchar(255) not null,
	descricao varchar(255) not null,
	valor decimal(10,2) not null
);
create table if not exists loja_virtual.cliente(
	id  serial not null primary key,
	nome varchar(255) not null,
	cpf varchar(20) not null,
	email varchar(55) not null
);

create table if not exists loja_virtual.compra(
	id  serial not null primary key,
	idcliente int not null,
	idproduto int not null
);