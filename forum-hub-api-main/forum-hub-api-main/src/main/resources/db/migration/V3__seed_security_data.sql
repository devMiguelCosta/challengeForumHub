INSERT INTO perfil (nome) VALUES
('ADMIN'),
('TECH'),
('USER');

UPDATE usuario
SET senha = '$2a$12$Wz/lr0IgN2Qkr0ddgcvz1u.Hrfe6xvnitjYLlKItfaT8xquNLgVDO'
WHERE email = 'teste@teste.com';

INSERT INTO usuario (nome, email, senha) VALUES
('Admin', 'admin@forum.com', '$2a$12$wTP3X4/455bi2pJ7.g9QqOeZ9UGlbr/y2ktEJyBIr23tKxzmww3Lm');

INSERT INTO usuario_perfil (usuario_id, perfil_id)
SELECT u.id, p.id
FROM usuario u
JOIN perfil p ON p.nome = 'USER'
WHERE u.email = 'teste@teste.com';

INSERT INTO usuario_perfil (usuario_id, perfil_id)
SELECT u.id, p.id
FROM usuario u
JOIN perfil p ON p.nome = 'ADMIN'
WHERE u.email = 'admin@forum.com';

