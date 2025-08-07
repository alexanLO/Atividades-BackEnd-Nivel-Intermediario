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

## 2. 🔁 Spring & Ecossistema

### 1. Spring Boot (starter packs, autoconfiguração)

### 🔸 Exercícios Spring Boot – Starter Pack & AutoConfig
- [x] [✔️] Exercício: Monitoramento e Logs com Starter Packs
📂 Pasta: `spring-ecossistema`
✅ Tópicos: `starter packs`, `autoconfiguração`, `Spring Boot Actuator`, `logs`, `info endpoint`, `application.properties`
📝 Descrição: Criar uma aplicação Spring Boot utilizando apenas starter packs e autoconfiguração para expor informações de saúde, métricas e dados do sistema.

### 2. Spring MVC (REST controllers, interceptors, filters)

### 🔸 Exercícios Spring MVC – REST Controller
- [] [✔️] Exercício: API de Gerenciamento de Tarefas (To-Do List)
📂 Pasta: `editar`
✅ Tópicos: `@RestController`, `@RequestMapping`, `@GetMapping`, `@PostMapping`, `@PutMapping`, `@PatchMapping`, `@DeleteMapping`, `@PathVariable`, `@RequestBody`, `enum`
📝 Descrição: Desenvolver uma API REST para gerenciar tarefas com operações CRUD, alteração de status via PATCH, validação de campos obrigatórios e uso de enum para status. Os dados devem ser armazenados temporariamente em uma List<Tarefa>, sem uso de banco de dados. A API deve retornar os códigos HTTP apropriados e seguir boas práticas com Spring Boot.

### 🔸 Exercícios Spring MVC – Interceptor
- [] [✔️] Exercício: Interceptor de Log e Autenticação
📂 Pasta: `Java_Avancado/SpringMVC/Interceptor`
✅ Tópicos: `HandlerInterceptor`, `preHandle`, `afterCompletion`, `WebMvcConfigurer`, `interceptação de requisições HTTP`, `autenticação com token`
📝 Descrição: Criar um interceptor que registre informações detalhadas de cada requisição (endpoint, método HTTP, IP do cliente, tempo de execução) e valide autenticação via token presente no cabeçalho Authorization. O interceptor deve bloquear requisições sem token ou com token inválido, retornando 401 Unauthorized, e ser configurado para ignorar rotas /login e /public/**.

## ✍️ Anotações e Aprendizados

Use esta seção para registrar insights, erros comuns, ou dicas:
