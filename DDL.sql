create schema avaliacao;

use avaliacao;

create user if not exists user password 'pass123' admin;

grant select, insert, delete, update on schema avaliacao to user;

create table usu_usuario (
  usu_id bigint unsigned PRIMARY KEY auto_increment,
  usu_nome_usuario varchar(50) not null,
  usu_senha varchar(50) not null,
  
  constraint usu_nome_usuario_uk unique (usu_nome_usuario)
);

create table pro_professor (
  pro_id bigint unsigned PRIMARY KEY auto_increment,
  pro_titulo varchar(10),
  
  constraint pro_usu_fk foreign key(pro_id)
    references usu_usuario (usu_id)
);

create table alu_aluno (
  alu_id bigint unsigned PRIMARY KEY auto_increment,
  alu_ra bigint unsigned not null,
  
  constraint alu_usu_fk foreign key(alu_id)
    references usu_usuario (usu_id),
  constraint alu_ra_uk unique (alu_ra)
);

create table tra_trabalho (
  tra_id bigint unsigned PRIMARY KEY auto_increment,
  tra_titulo varchar(50) not null,
  tra_data_hora_entrega datetime not null,
  tra_local_arquivo varchar(200) not null,
  pro_avaliador_id bigint unsigned,
  
  constraint tra_pro_fk foreign key (pro_avaliador_id)
    references pro_professor (pro_id)
);

create table ent_entrega (
  alu_id bigint,
  tra_id bigint,
  
  PRIMARY KEY (alu_id, tra_id),
  constraint ent_alu_fk foreign key (alu_id)
    references alu_aluno (alu_id),
  constraint ent_tra_fk foreign key (tra_id)
    references tra_trabalho (tra_id)
);

create table eve_evento (
	eve_id bigint unsigned PRIMARY KEY auto_increment,
	eve_titulo varchar(50) not null,
  	eve_local varchar(100) not null,
  	eve_data_hora_agendamento datetime not null,
  	pro_organizador_id bigint,
  	
  	constraint eve_pro_organizador_fk foreign key (pro_organizador_id)
    	references pro_professor (pro_id)
);

create table aca_academico (
	aca_id bigint unsigned PRIMARY KEY auto_increment,
	aca_cursos varchar(252) not null,
	
	constraint aca_eve_fk foreign key(aca_id)
    	references eve_evento (eve_id)
);

create table des_desportivo (
	des_id bigint unsigned PRIMARY KEY auto_increment,
	des_equipes varchar(252) not null,
	
	constraint des_eve_fk foreign key(des_id)
    	references eve_evento (eve_id)
);

create table amb_ambiente (
	amb_id bigint unsigned PRIMARY KEY auto_increment,
	amb_tamanho double not null,
	amb_distanciamento_min int not null,
	amb_lotacao int not null,
	amb_tipo_ambiente int not null,
	pro_responsavel_id bigint,
	
	constraint amb_pro_responsavel_fk foreign key (pro_responsavel_id)
    	references pro_professor (pro_id)
);