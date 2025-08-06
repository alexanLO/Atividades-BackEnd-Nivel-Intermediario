## 🚀 **Nível Intermediário (Consolidação)**
Guia de tudo que foi estudado e descrição dos desáfios práticos.

### 1. **Java Profissional**

- Domine:
    - Streams API `ok`
    - Lambdas
    - Generics
    - Concurrency (ExecutorService, Future, CompletableFuture) `ok`
    - Tratamento de erros com boas práticas `ok`

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


# 🧪 Java Backend Roadmap - Atividades Práticas

Este repositório contém exercícios e desafios práticos para consolidar os tópicos do roadmap de estudo Java Backend. Cada seção está organizada com checklists para acompanhamento do progresso.

---

## ✅ Estrutura

- [x] Projeto configurado com Maven
- [x] Ambiente Java 21 configurado
- [x] IDE: vsCode / IntelliJ

---

## 1. 🔁 Java Avançado — Streams, Optionals, Exceptions e CompletableFuture.

[here] https://alexanol.atlassian.net/browse/ES-1

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

## 1. 🔁 Spring MVC (REST controllers, interceptors, filters)

### 🔸 Exercícios Spring MVC – REST Controller
- [] [✔️] Exercício: API de Gerenciamento de Tarefas (To-Do List)
📂 Pasta: `editar`
✅ Tópicos: `@RestController`, `@RequestMapping`, `@GetMapping`, `@PostMapping`, `@PutMapping`, `@PatchMapping`, `@DeleteMapping`, `@PathVariable`, `@RequestBody`, `enum`
📝 Descrição: Criar uma API REST para gerenciar tarefas contendo os seguintes recursos:
1. Criar tarefa (POST /api/tarefas)
2. Listar todas as tarefas (GET /api/tarefas)
3. Buscar tarefa por ID (GET /api/tarefas/{id})
4. Atualizar tarefa inteira (PUT /api/tarefas/{id})
5. Alterar apenas o status (PATCH /api/tarefas/{id}/status)
6. Deletar tarefa (DELETE /api/tarefas/{id})
7. Usar enum para status (PENDENTE, EM_ANDAMENTO, CONCLUIDA)
8. Armazenar dados temporariamente em List<Tarefa> sem banco de dados.
📌 Critérios:
* Retornar códigos HTTP adequados (201, 200, 404, 400).
* Validar campos obrigatórios (titulo, status).
* Utilizar boas práticas de organização de código no Spring Boot.

### 🔸 Exercícios Spring MVC – Interceptor
- [] [✔️] Exercício: Interceptor de Log e Autenticação
📂 Pasta: `Java_Avancado/SpringMVC/Interceptor`
✅ Tópicos: `HandlerInterceptor`, `preHandle`, `afterCompletion`, `WebMvcConfigurer`, `interceptação de requisições HTTP`, `autenticação com token`
📝 Descrição: Criar um interceptor que registre informações detalhadas de cada requisição (endpoint, método HTTP, IP do cliente, tempo de execução) e valide autenticação via token presente no cabeçalho Authorization. O interceptor deve bloquear requisições sem token ou com token inválido, retornando 401 Unauthorized, e ser configurado para ignorar rotas /login e /public/**.

## ✍️ Anotações e Aprendizados

Use esta seção para registrar insights, erros comuns, ou dicas:
