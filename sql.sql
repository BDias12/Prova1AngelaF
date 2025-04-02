CREATE DATABASE a;
use a;

CREATE TABLE Pessoa(
	id int PRIMARY KEY auto_increment,
	nome VARCHAR(100),
  endereco VARCHAR(100),
  telefone CHAR(11),
  cpf CHAR(11),
  tipo_sanguineo Enum("A", "B", "AB", "O"),
  fator_rh Enum("+", "-"),
  curso Enum("Direito", "Ciência da Computação", "Sistemas de Informação", "Medicina", "Psicologia", "Nutrição"),
  contato_emergencia VARCHAR(100),
  telefone_emergencia CHAR(11),
  altura double,
  peso double,
  imc double
);

SELECT * FROM Pessoa