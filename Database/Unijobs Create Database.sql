CREATE DATABASE unijobs;
USE unijobs;

CREATE TABLE IF NOT EXISTS unijobs.universidades (
    id_universidade INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    cnpj VARCHAR(45) NOT NULL,
    PRIMARY KEY (id_universidade)
);
  
CREATE TABLE IF NOT EXISTS unijobs.cursos (
    id_curso INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    PRIMARY KEY (id_curso)
);

CREATE TABLE IF NOT EXISTS unijobs.tipos_usuarios (
    id_tipoUsuario INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    PRIMARY KEY (id_tipoUsuario)
);

CREATE TABLE IF NOT EXISTS unijobs.usuarios (
    id_usuario INT NOT NULL AUTO_INCREMENT,
    email VARCHAR(255) NOT NULL,
    senha VARCHAR(255) NOT NULL,
    nome VARCHAR(255) NOT NULL,
    celular VARCHAR(15) NOT NULL,
    ra VARCHAR(255) NOT NULL,
    id_universidade INT NOT NULL,
    id_cursos INT NOT NULL,
    id_tipoUsuario INT NOT NULL,
    PRIMARY KEY (id_usuario),
    FOREIGN KEY (id_universidade)
        REFERENCES unijobs.universidades (id_universidade)
        ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (id_cursos)
        REFERENCES unijobs.cursos (id_curso)
        ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (id_tipousuario)
        REFERENCES unijobs.tipos_usuarios (id_tipoUsuario)
        ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS unijobs.tipos_produto (
    id_tipoProduto INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    descricao VARCHAR(500) NOT NULL,
    PRIMARY KEY (id_tipoProduto)
);

CREATE TABLE IF NOT EXISTS unijobs.tipos_servico (
    id_tipoServico INT NOT NULL AUTO_INCREMENT,
    nome VARCHAR(255) NOT NULL,
    descricao VARCHAR(500) NOT NULL,
    PRIMARY KEY (id_tipoServico)
);

CREATE TABLE IF NOT EXISTS unijobs.anuncios (
    id_anuncio INT NOT NULL AUTO_INCREMENT,
    titulo VARCHAR(255) NOT NULL,
    descricao VARCHAR(500) NOT NULL,
    preco DECIMAL(10 , 2 ) NOT NULL,
    miniatura VARCHAR(500) NOT NULL,
    ativo TINYINT NOT NULL,
    prazo INT NOT NULL,
    id_usuario INT NOT NULL,
    id_tipoProduto int,
    id_tipoServico int,
    PRIMARY KEY (id_anuncio),
    FOREIGN KEY (id_usuario)
        REFERENCES unijobs.usuarios (id_usuario)
        ON DELETE RESTRICT ON UPDATE CASCADE,
	FOREIGN KEY (id_tipoProduto)
        REFERENCES unijobs.tipos_produto (id_tipoProduto)
        ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (id_tipoServico)
        REFERENCES unijobs.tipos_servico (id_tipoServico)
        ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS unijobs.avaliacoes (
    id_avaliacao INT NOT NULL AUTO_INCREMENT,
    nota INT NOT NULL,
    id_anuncio INT NOT NULL,
    PRIMARY KEY (id_avaliacao),
    FOREIGN KEY (id_anuncio)
        REFERENCES unijobs.anuncios (id_anuncio)
        ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS unijobs.cursos_universidade (
    id_universidade INT NOT NULL,
    id_curso INT NOT NULL,
    PRIMARY KEY (id_universidade , id_curso),
    FOREIGN KEY (id_universidade)
        REFERENCES unijobs.universidades (id_universidade)
        ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (id_curso)
        REFERENCES unijobs.cursos (id_curso)
        ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS unijobs.imagens (
    id_imagem INT NOT NULL AUTO_INCREMENT,
    url VARCHAR(255) NULL,
    id_anuncio INT NOT NULL,
    PRIMARY KEY (id_imagem),
    FOREIGN KEY (id_anuncio)
        REFERENCES unijobs.anuncios (id_anuncio)
        ON DELETE RESTRICT ON UPDATE CASCADE
);
