Miguel Silva da Costa
Desenvolvedor Java Pleno
Este repositório contém o projeto FórumHub, desenvolvido como parte do Desafio da Alura em parceria com o Oracle ONE. O foco do projeto é a criação de um back-end robusto para um fórum de tópicos e respostas.

A ideia é oferecer uma API organizada e segura para criação, consulta, atualização e exclusão de tópicos, seguindo boas práticas e regras de negócio do desafio.

✨ O que já foi implementado
CRUD de Tópicos: Criar, listar com paginação, detalhar, atualizar e excluir.

Validações: Regras de negócio aplicadas a campos obrigatórios e prevenção de duplicidade.

Segurança: Autenticação com login e geração de token JWT.

Proteção de Rotas: Controle de acesso com Spring Security via Bearer Token.

🧰 Tecnologias utilizadas
Java 21

Spring Boot (com Spring Web, Spring Data JPA, Spring Security)

MySQL (Banco de dados)

Flyway (Migrations do banco)

JWT (JSON Web Token)

Lombok

Bean Validation

🚀 Como a API funciona (visão geral)
O fluxo padrão para consumir a API é:

O usuário faz login em /login fornecendo email e senha.

A API autentica e retorna um token JWT.

Este token deve ser enviado em todas as requisições subsequentes no header:
Authorization: Bearer SEU_TOKEN_AQUI

🔮 Próximos passos / melhorias futuras
O FórumHub é um projeto em constante evolução. As próximas atualizações devem incluir:

Expansão do Modelo: Mais endpoints para Respostas, Usuários, Perfis e Cursos.

Autorização Detalhada: Permissões baseadas em perfis de usuário.

Front-end: Desenvolvimento de uma interface simples para facilitar o uso do fórum.

📌 Observações
Projeto desenvolvido como estudo e prática, priorizando organização, legibilidade e evolução incremental conforme as etapas do desafio.
