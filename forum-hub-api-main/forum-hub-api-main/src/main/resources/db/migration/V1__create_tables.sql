CREATE TABLE curso (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(100) NOT NULL,
  categoria VARCHAR(100) NOT NULL
);
CREATE TABLE perfil (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  nome VARCHAR(50) NOT NULL,
  UNIQUE KEY uk_perfil_nome (nome)
);
CREATE TABLE usuario (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  nome  VARCHAR(100) NOT NULL,
  email VARCHAR(150) NOT NULL,
  senha VARCHAR(255) NOT NULL,
  UNIQUE KEY uk_usuario_email (email)
);
CREATE TABLE usuario_perfil (
  usuario_id BIGINT NOT NULL,
  perfil_id  BIGINT NOT NULL,

  PRIMARY KEY (usuario_id, perfil_id),

  CONSTRAINT fk_usuario_perfil_usuario
    FOREIGN KEY (usuario_id) REFERENCES usuario(id),

  CONSTRAINT fk_usuario_perfil_perfil
    FOREIGN KEY (perfil_id) REFERENCES perfil(id)
);
CREATE TABLE topico (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  titulo   VARCHAR(200) NOT NULL,
  mensagem TEXT NOT NULL,
  data_criacao DATETIME NOT NULL,
  status VARCHAR(30) NOT NULL,

  autor_id BIGINT NOT NULL,
  curso_id BIGINT NOT NULL,

  CONSTRAINT fk_topico_autor
    FOREIGN KEY (autor_id) REFERENCES usuario(id),

  CONSTRAINT fk_topico_curso
    FOREIGN KEY (curso_id) REFERENCES curso(id)
);
CREATE TABLE resposta (
  id BIGINT PRIMARY KEY AUTO_INCREMENT,
  mensagem TEXT NOT NULL,
  data_criacao DATETIME NOT NULL,
  solucao BOOLEAN NOT NULL DEFAULT FALSE,

  topico_id BIGINT NOT NULL,
  autor_id  BIGINT NOT NULL,

  CONSTRAINT fk_resposta_topico
    FOREIGN KEY (topico_id) REFERENCES topico(id),

  CONSTRAINT fk_resposta_autor
    FOREIGN KEY (autor_id) REFERENCES usuario(id)
);