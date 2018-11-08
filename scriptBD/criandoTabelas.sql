/* Criando tabela usuário*/
CREATE TABLE usuario(
	id SERIAL PRIMARY KEY,
	login VARCHAR(50) NOT NULL UNIQUE,
	senha VARCHAR(30) NOT NULL,
	nome VARCHAR(100)
)