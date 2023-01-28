
create table cliente (
 ID bigint not null auto_increment,
 NOME varchar(80) not null,
 CPF varchar(15) not null, 
 TELEFONE varchar(16) default null,
 CELULAR varchar(16) default null,
 EMAIL varchar(60) default null,
 SEXO varchar(10) default null,
 DATA_NASCIMENTO date default null,
 CEP varchar(10) default null,
 LOGRADOURO varchar(80) default null,
 BAIRRO varchar(80) default null,
 COMPLEMENTO varchar(80) default null,
 MUNICIPIO varchar(80) default null,
 NUMERO varchar(6) default null,
 UF varchar(2) default null,
 primary key (ID)
) engine=innodb default charset=utf8mb4 collate=utf8mb4_0900_ai_ci;