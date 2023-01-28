create table reservas (
	ID bigint not null auto_increment,
	ID_CLIENTE bigint not null,
	ID_APARTAMENTO int not null,
	DATA_INICIAL date default null,
	DATA_FINAL date default null,
	primary key(ID),
	key (ID_CLIENTE),
	key (ID_APARTAMENTO),
	constraint foreign key (ID_CLIENTE) references cliente (ID), 
	constraint foreign key (ID_APARTAMENTO) references apartamento (ID) 
) engine=InnoDB default charset=utf8mb4 collate=utf8mb4_0900_ai_ci;