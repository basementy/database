# SQL + JDBC (N2)

Esse projeto serve para validar a N2, uma simples conexão entre Java e SQL Server.

## Conexão

Para realizar a conexão, deve-se primeiro criar um banco e um usuário. Após isso modificar o construtor `Database` para que seja possível realizar a conexão de forma efetiva, passando o nome do banco, o usuário e a senha.

## Tabelas

Após ter realizado a conexão, deve-se então criar as tabelas que serão usadas. Ambas estão localizadas em `src/scripts`.

## Consultas

Todas as consultas, sejam para `INSERT` ou `SELECT` estão localizadas dentro da classe `Database` como métodos, algumas utilizam do console para printar resultados e outras utilizam do `JOptionPane` para inserção no banco.