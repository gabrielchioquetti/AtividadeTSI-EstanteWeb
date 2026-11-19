CREATE TABLE usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL, 
    email VARCHAR(255) NOT NULL unique,
    senha VARCHAR(25)
);

INSERT INTO usuario (nome, email, senha)
VALUE ('Marta', 'marta@estanteweb.com', "estante123");