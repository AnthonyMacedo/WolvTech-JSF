create table apartamento (
	ID int not null auto_increment,
	NUM_QUARTO varchar(8) not null,
	QTD_OCUPANTES int not null,
	STATUS_APARTAMENTO varchar(11) default null,
	primary key(ID),
	unique key(NUM_QUARTO)
) engine=InnoDB default charset=utf8mb4 collate=utf8mb4_0900_ai_ci;
