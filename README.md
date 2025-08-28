## 🚀 **Nível Intermediário (Consolidação)**
Guia de tudo que foi estudado e descrição dos desáfios práticos.

### 1. **Java Profissional**

- Domine:
    - Streams API
    - Lambdas
    - Generics
    - Concurrency (ExecutorService, Future, CompletableFuture)
    - Tratamento de erros com boas práticas

### 2. **Spring & Ecossistema**

- Spring Boot (starter packs, autoconfiguração)
- Spring MVC (REST controllers, interceptors, filters)
- Spring Data JPA (JPQL, projections, specifications)
- Spring Security (autenticação JWT + autorização RBAC)
- Spring Validation
- Spring AOP (boas práticas de logging, auditoria)

### 3. **Banco de Dados**

- SQL avançado (joins complexos, índices, transações)
- JPA/Hibernate:
    - Lazy vs Eager
    - N+1 problem
    - Ciclo de vida da entidade
- Flyway ou Liquibase (versionamento de schema)

### 4. **Testes**

- JUnit 5 (mock, parametrizado, nested)
- Mockito
- Testcontainers para testes de integração com banco
- Spring Boot Test

### 5. **Ferramentas e Práticas**

- Git (branches, rebase, merge requests)
- Docker (build de imagens, containers com PostgreSQL)
- Postman/Insomnia
- Integração com Swagger/OpenAPI


# 🧪 Java Backend - Atividades Práticas

Este repositório contém exercícios e desafios práticos para consolidar os tópicos de estudo Java Backend. Cada seção está organizada com checklists para acompanhamento do progresso.

---

## ✅ Estrutura

- [x] Projeto configurado com Maven
- [x] Ambiente Java 21 configurado
- [x] IDE: vsCode / IntelliJ

---

## 1. 🔁 Java Profissional — Streams, Optionals, Exceptions, CompletableFuture, Lambda e Generics.

### 🔸 Exercícios Streams
- [x] [✔️] **Exercício: Análise de Pedidos de Clientes**  
      📂 Pasta: `Java_Avancado/Streams`  
      ✅ Tópicos: `filter`, `map`, `collect`, `groupingBy`, `flatMap`, `reduce`
      📝 Descrição: Processar uma lista de pedidos contendo clientes, itens e valores para extrair métricas como total por cliente, produtos mais vendidos e valor médio dos pedidos.
      
### 🔸 Exercícios Optional
- [x] [✔️] **Exercício: Cadastro e Verificação de E-mails com Optional**  
      📂 Pasta: `Java_Avancado/Optional`  
      ✅ Tópicos: `Optional.ofNullable`, `filter`, `map`, `orElse`, `orElseThrow`, `ifPresent`
      📝 Descrição: Criar uma lógica de autenticação segura utilizando Optional para evitar NullPointerException e tratar todos os cenários possíveis com elegância funcional.
      
### 🔸 Exercícios Exception
- [x] [✔️] **Exercício: Transferência Bancária com Exceções Personalizadas**
📂 Pasta: `Java_Avancado/Exceptions`
✅ Tópicos: `try/catch`, `throws`, `finally`, exceções personalizadas (`extends Exception`)
📝 Descrição: Desenvolver um sistema de transferência entre contas com validações e uso de exceções como SaldoInsuficienteException, ContaInvalidaException, e ValorInvalidoException.

### 🔸 Exercícios CompletableFuture
- [x] [✔️] **Desafio: Sistema de Envio de E-mails Assíncronos**
📂 Pasta: `editar`
✅ Tópicos: `CompletableFuture`, `programação assíncrona`, `exceptionally`, `orTimeout`
📝 Descrição:  Desenvolver um sistema assíncrono que envia e-mails simulando tempos variáveis de envio e possíveis falhas, como endereço inválido. Utilizar CompletableFuture.supplyAsync() e encadeamentos para tratamento de erros. Medir tempo total e imprimir resultados detalhados de sucesso e falha.
---

## 2. 🌱 Spring & Ecossistema

**Obs:** Nestas atividades do Spring & Ecossistema não seram separar por pastas igual as atividades do Java Profissional, elas seram separadas por commits.

### 1. Spring Boot (starter packs, autoconfiguração)

### 🔸 Exercícios Spring Boot – Starter Pack & AutoConfig
- [x] [✔️] Exercício: Monitoramento e Logs com Starter Packs
📂 Pasta: `spring-ecossistema`
✅ Tópicos: `starter packs`, `autoconfiguração`, `Spring Boot Actuator`, `logs`, `info endpoint`, `application.properties`
📝 Descrição: Criar uma aplicação Spring Boot utilizando apenas starter packs e autoconfiguração para expor informações de saúde, métricas e dados do sistema.

### 2. Spring MVC (REST controllers, interceptors, filters)

### 🔸 Exercícios Spring MVC – REST Controller
- [x] [✔️] Exercício: API de Gerenciamento de Tarefas
📂 Pasta: `spring-ecossistema`
✅ Tópicos: `@RestController`, `@RequestMapping`, `@PathVariable`, `@RequestBody`, `HTTP status`
📝 Descrição: Criar uma API REST para gerenciar tarefas com operações de CRUD, uso de enum para status e armazenamento temporário em memória.

### 🔸 Desafio: Interceptor de Requisições HTTP
- [x] [✔️]
📂 Pasta: `spring-ecossistema`
✅ Tópicos: `HandlerInterceptor`, `preHandle`, `afterCompletion`, `log de requisição`, `tempo de execução`
📝 Descrição: Criar um interceptor que registre logs no início e no fim de cada requisição HTTP, exibindo URI, método, timestamp e tempo de execução.

### 🔸 Desafio Prático – Filtro de Requisições HTTP
- [x] [✔️]
📂 Pasta: `spring-ecossistema`
✅ Tópicos: `Filter`, `FilterChain`, `ServletRequest/Response`, `headers`, `validação de token`, `log`
📝 Descrição: Criar um filtro que registre informações da requisição (timestamp, método, URI, IP do cliente) e bloqueie requisições que não contenham o header X-Auth-Token com o valor esperado.
--- 

### 3. Validation

### 🔸 Desafio Prático – Validação com Spring Validation
- [x] [✔️]
📂 Pasta: `spring-ecossistema`
✅ Tópicos: `@Valid`, `@NotNull`, `@Email`, `@Size`, `@Pattern`, `BindingResult`, `ExceptionHandler`
📝 Descrição: Adicionar validações nos campos da entidade User usando anotações do Bean Validation. Garantir que o nome não seja vazio, o e-mail seja válido e o status seja obrigatório. Em caso de erro de validação, retornar mensagens claras no corpo da resposta com código HTTP 400.

### 🔸 Desafio Prático – Auditoria e Logging com Spring AOP
- [x] [✔️]
📂 Pasta: `spring-ecossistema`
✅ Tópicos: `@Aspect`, `@Before`, `@AfterReturning`, `@Around`, `@Annotation`, `JoinPoint`, `ProceedingJoinPoint`, `Logs`, `Auditoria`
📝 Descrição: Implementar um aspecto com @Aspect para registrar logs e auditoria em métodos de controllers e services. Usar a anotação personalizada @Auditable para indicar ações sensíveis (como criação, atualização e exclusão), registrando usuário, ação, entidade e tempo de execução automaticamente no terminal.

### 🔸 Desafio Prático – Spring Security com JWT + RBAC
- [x] [✔️]
📂 Pasta: spring-ecossistema
✅ Tópicos: `Spring Security`, `JWT`, `RBAC`, `Filter`, `AuthenticationManager`, `UserDetailsService`, `Authorization`, `Token`, `Roles`, `@PreAuthorize`, `@Secured`
📝 Descrição: Implementar autenticação com JWT (JSON Web Token) e controle de acesso baseado em regras (RBAC) no projeto. Usuários devem se autenticar com email e senha, receber um token JWT e usá-lo para acessar rotas protegidas. Usar anotações como @PreAuthorize para restringir endpoints a papéis como ADMIN e USER. Criar filtros customizados para autenticação e verificação do token.

### 🔸 Desafio Prático – SQL Avançado no Projeto de Gerenciamento de Usuários
- [x] [✔️]
📂 Pasta: sql-avancado
✅ Tópicos: `JOIN`, `GROUP BY`, `HAVING`, `SUBQUERY`, `AGGREGATION`, `TIMESTAMP`, `CASE`, `MAX`, `INNER JOIN`, `LEFT JOIN`, `DATE FILTERING`
📝 Descrição: Criar queries SQL avançadas para resolver problemas reais do sistema, como: identificar usuários com múltiplas tentativas de login falhas nos últimos 7 dias, listar usuários com seus perfis, contar tentativas de login por usuário, mostrar última tentativa de login e indicar o status mais recente com base em sucesso ou falha. Utilizar boas práticas e expressões SQL limpas.

## ✍️ Anotações e Aprendizados

Use esta seção para registrar insights, erros comuns, ou dicas:
