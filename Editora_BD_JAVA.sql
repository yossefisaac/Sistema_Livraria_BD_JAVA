CREATE DATABASE Editora_BD_JAVA;

USE Editora_BD_JAVA;

CREATE TABLE Editora (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100),
    endereco TEXT,
    telefone VARCHAR(20)
);

CREATE TABLE Autor (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100),
    biografia TEXT
);

CREATE TABLE Livro (
    ISBN VARCHAR(13) PRIMARY KEY,
    titulo VARCHAR(200),
    preco DECIMAL(10, 2),
    ano INT,
    categoria VARCHAR(50)
);

CREATE TABLE Cliente (
    id SERIAL PRIMARY KEY,
    nome VARCHAR(100),
    endereco TEXT,
    telefone VARCHAR(20),
    email VARCHAR(100)
);

CREATE TABLE Pedido (
    id SERIAL PRIMARY KEY,
    cliente_id INT REFERENCES Cliente(id),
    data DATE,
    total DECIMAL(10, 2),
    status VARCHAR(20)
);

CREATE TABLE Pagamento (
    id SERIAL PRIMARY KEY,
    pedido_id INT REFERENCES Pedido(id),
    tipo VARCHAR(20)
);

CREATE TABLE Envio (
    id SERIAL PRIMARY KEY,
    pedido_id INT REFERENCES Pedido(id),
    tipo VARCHAR(20)
);

CREATE TABLE Pedido_Livro (
    pedido_id INT REFERENCES Pedido(id),
    livro_ISBN VARCHAR(13) REFERENCES Livro(ISBN),
    quantidade INT,
    PRIMARY KEY (pedido_id, livro_ISBN)
);

CREATE TABLE Livro_Autor (
    livro_ISBN VARCHAR(13) REFERENCES Livro(ISBN),
    autor_id INT REFERENCES Autor(id),
    PRIMARY KEY (livro_ISBN, autor_id)
);

CREATE TABLE Livro_Editora (
    livro_ISBN VARCHAR(13) REFERENCES Livro(ISBN),
    editora_id INT REFERENCES Editora(id),
    PRIMARY KEY (livro_ISBN, editora_id)
);
