# Guia para rodar os scripts SQL

O banco de dados usado foi Oracle.

- Primeiro rodas o arquivo V1_create_schema.sql para poder criar as tabelas.
- Agora você consegue rodar o V2_insert_seed_sql para inserir os dados iniciais.
- E por fim rodar o V3_complex_queries.sql para as consultas e transações.

## Dependência necessária
  <!-- https://mvnrepository.com/artifact/com.oracle.database.jdbc/ojdbc11 -->
  <dependency>
   <groupId>com.oracle.database.jdbc</groupId>
   <artifactId>ojdbc11</artifactId>
   <version>23.7.0.25.01</version>
  </dependency>

## Ferramentas

Você pode adicionar a extensão SQL Develop para acessar ao banco ou usar o SQL Develop baixado no site: https://www.oracle.com/br/database/sqldeveloper/
Também irá precisar installar o db eu usei o XE 21c: https://www.oracle.com/br/database/technologies/xe-downloads.html