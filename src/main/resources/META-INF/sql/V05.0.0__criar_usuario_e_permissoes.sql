create table usuario (
	ID bigint primary key,
	EMAIL varchar(50) not null,
	SENHA varchar(180) not null
) engine=innodb default charset=utf8mb4 collate=utf8mb4_0900_ai_ci;

create table permissao (
	ID bigint primary key,
	DESCRICAO varchar(60) not null
) engine=innodb default charset=utf8mb4 collate=utf8mb4_0900_ai_ci;

create table permissao_usuario (
	ID_USUARIO bigint not null,
	ID_PERMISSAO bigint not null,
	primary key (ID_USUARIO, ID_PERMISSAO),
	foreign key (ID_USUARIO) references USUARIO(ID),
	foreign key (ID_PERMISSAO) references PERMISSAO(ID)
) engine=innodb default charset=utf8mb4 collate=utf8mb4_0900_ai_ci;

insert into usuario(id, email, senha) values (1, "admin", "$2a$10$9hU.lU31OAmhDfLSzQDsJusCQcSvzzOPiYw0EckyYhO.5ZvVUSNR2"); /*senha admin*/

insert into permissao (id, descricao) values (1, "ROLE_CADASTRAR_CLIENTE");
insert into permissao (id, descricao) values (2, "ROLE_REMOVER_CLIENTE");
insert into permissao (id, descricao) values (3, "ROLE_PESQUISAR_CLIENTE");

insert into permissao (id, descricao) values (4, "ROLE_CADASTRAR_FUNCIONARIO");
insert into permissao (id, descricao) values (5, "ROLE_REMOVER_FUNCIONARIO");
insert into permissao (id, descricao) values (6, "ROLE_PESQUISAR_FUNCIONARIO");

insert into permissao (id, descricao) values (7, "ROLE_CADASTRAR_APARTAMENTO");
insert into permissao (id, descricao) values (8, "ROLE_REMOVER_APARTAMENTO");
insert into permissao (id, descricao) values (9, "ROLE_PESQUISAR_APARTAMENTO");

insert into permissao (id, descricao) values (10, "ROLE_REGISTRAR_RESERVA");
insert into permissao (id, descricao) values (11, "ROLE_CANCELAR_RESERVA");
insert into permissao (id, descricao) values (12, "ROLE_PESQUISAR_RESERVA");


insert into permissao_usuario (ID_USUARIO, ID_PERMISSAO) values (1, 1);
insert into permissao_usuario (ID_USUARIO, ID_PERMISSAO) values (1, 2);
insert into permissao_usuario (ID_USUARIO, ID_PERMISSAO) values (1, 3);
insert into permissao_usuario (ID_USUARIO, ID_PERMISSAO) values (1, 4);
insert into permissao_usuario (ID_USUARIO, ID_PERMISSAO) values (1, 5);
insert into permissao_usuario (ID_USUARIO, ID_PERMISSAO) values (1, 6);
insert into permissao_usuario (ID_USUARIO, ID_PERMISSAO) values (1, 7);
insert into permissao_usuario (ID_USUARIO, ID_PERMISSAO) values (1, 8);
insert into permissao_usuario (ID_USUARIO, ID_PERMISSAO) values (1, 9);
insert into permissao_usuario (ID_USUARIO, ID_PERMISSAO) values (1, 10);
insert into permissao_usuario (ID_USUARIO, ID_PERMISSAO) values (1, 11);
insert into permissao_usuario (ID_USUARIO, ID_PERMISSAO) values (1, 12);
