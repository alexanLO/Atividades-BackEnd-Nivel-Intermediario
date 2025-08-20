# 🚀 Desafio – Monitoramento e Logs de Sistema com Starter Packs

## 🎯 Objetivo (Gerenciamento de Usuários)

Criar um serviço simples usando **Spring Boot Starter Packs** e **Autoconfiguração** para monitorar informações do sistema e expor endpoints de saúde e métricas — sem configurar nada manualmente.

## 📦 Dependências (Starters)

Ao criar o projeto no Spring Initializr, inclua:

- `spring-boot-starter-web` – criar endpoints REST
- `spring-boot-starter-actuator` – endpoints de monitoramento prontos
- `spring-boot-starter-logging` (vem por padrão) – logs com Logback
- **Opcional**: `spring-boot-starter-validation` – para validar parâmetros de entrada

## 📝 Regras do Desafio

1. Criar um endpoint `/api/info` que retorne:
   - Nome da aplicação (do `application.properties`)
   - Versão do Java em execução
   - Horário atual no servidor
   - IP do servidor
2. Configurar o **Spring Boot Actuator** para expor:
   - `/actuator/health`
   - `/actuator/info`
   - `/actuator/metrics`
3. Alterar o `application.properties` para:
   - `server.port=9090`
   - Configurar `spring.application.name`
   - Configurar informações personalizadas no `/actuator/info`
4. Criar logs em diferentes níveis (`INFO`, `WARN`, `ERROR`) usando `Logger`.

## 📂 Estrutura esperada

```text
src/
 └── main/
      ├── java/
      │    └── com.exemplo.monitoramento/
      │          ├── MonitoramentoApplication.java
      │          ├── controller/
      │          │     └── InfoController.java
      │          └── config/
      │                └── AppConfig.java (opcional)
      └── resources/
           ├── application.properties
           └── logback-spring.xml (opcional)
```

## 📌 Critérios de Aceite

- Usar apenas Starter Packs (sem adicionar libs externas)
- Deixar o Actuator expor métricas sem configurar beans manualmente (autoconfiguração)
- Endpoint `/api/info` funcionando e retornando JSON com as informações do servidor
- Logs aparecendo no console ao chamar o endpoint

---

# 🏆 Desafio Prático – REST Controller: Gerenciamento de Usuários

## 🎯 Objetivo

Criar uma API REST com Spring Boot para gerenciar um cadastro simples de usuários.

## 📝 Regras do Sistema

Cada usuário deve ter:

- id (Long, gerado automaticamente)
- name (String, obrigatório)
- email (String, obrigatório)
- status (enum: ATIVO, INATIVO)
- creationDate (LocalDateTime, preenchida automaticamente)

## A API deve permitir

- Criar um novo usuário
- Listar todos os usuários
- Buscar usuário por ID
- Atualizar nome e email do usuário
- Alterar apenas o status
- Deletar usuário

## 📌 Endpoints esperados

| Método | Endpoint             | Descrição               |
|--------|---------------------|-------------------------|
| POST   | /v1/users           | Criar novo usuário      |
| GET    | /v1/users           | Listar todos os usuários|
| GET    | /v1/users/{id}      | Buscar usuário por ID   |
| PUT    | /v1/users/{id}      | Atualizar nome e email  |
| PATCH  | /v1/users/{id}/status| Alterar apenas o status |
| DELETE | /v1/users/{id}      | Remover usuário         |

## 📄 Exemplo de corpo JSON

POST /api/usuarios

```json
{
  "nome": "Alexan Lima",
  "email": "alexan@email.com",
  "status": "ATIVO"
}
```

PATCH /api/usuarios/1/status

```json
{
  "status": "INATIVO"
}
```

## ✅ Critérios de Aceite

- Uso de @RestController
- Uso de @RequestMapping ou @PostMapping, @GetMapping, etc.
- Retornar respostas HTTP apropriadas: 201 Created, 200 OK, 404 Not Found, 204 No Content
- Uso de @PathVariable e @RequestBody
- Enum para o campo status
- Armazenar dados temporariamente em uma lista List<User> (sem banco de dados)
- Preencher dataCriacao automaticamente no momento da criação do usuário

---

# 🛡️ Desafio: Interceptor de Requisições HTTP

## 🎯 Objetivo

Adicionar um interceptor que registre logs personalizados antes e depois da execução de cada requisição.

## 📄 Cenário Atual

Você já tem uma API de usuários com:

- Endpoints CRUD completos
- Lista de usuários em memória
- UserService e UserController

## 🧩 Requisitos do Desafio

### 1. Criar um Interceptor

Crie uma classe RequestInterceptor que:

- Implemente HandlerInterceptor

No método preHandle, registre:

- Timestamp da requisição
- Endpoint acessado
- Método HTTP usado

No método afterCompletion, registre:

- Status da resposta
- Tempo total de execução

### 2. Registrar o Interceptor

Crie uma classe WebMvcConfig que implemente WebMvcConfigurer
Registre seu RequestInterceptor no método addInterceptors

### 3. Exemplo de Log esperado

```json
➡️ [INICIO] [2025-08-07 14:22:10] GET /v1/users
⬅️ [FIM] [Status: 200] [Duração: 12ms]
```

## 🏗️ Estrutura Esperada

```text
config/
 ├── WebMvcConfig.java
 └── RequestInterceptor.java
```

## 🔧 Dicas Técnicas

- Use System.currentTimeMillis() ou Instant.now() para medir duração.
- Você pode armazenar o timestamp de início como atributo da HttpServletRequest.

Exemplo:

```java
request.setAttribute("startTime", System.currentTimeMillis());
```

E no afterCompletion:

```java
Long startTime = (Long) request.getAttribute("startTime");
long duration = System.currentTimeMillis() - startTime;
```

---

# 🛡️ Desafio Prático – Filtro de Requisições HTTP

## Objetivo

Criar um filtro que faça o registro de logs detalhados para cada requisição, além de verificar a presença de um header customizado de autenticação simples para proteger os endpoints do seu projeto Users.

## Regras do Sistema

O filtro deve:

- Logar o método HTTP, URI, IP do cliente e timestamp do início da requisição.
- Bloquear requisições que não possuam o header X-Auth-Token ou cujo token não seja igual a uma string fixa (ex: "secrettoken123").
- Retornar status 401 Unauthorized e mensagem de erro quando o token for inválido ou ausente.
- Permitir passar livremente as requisições para os endpoints /v1/users/login e /v1/users/public/** (essas rotas devem ignorar a checagem do token).
- Logar no final da requisição o tempo total gasto (em ms).

## Detalhes técnicos

- Criar a classe do filtro implementando javax.servlet.Filter (ou jakarta.servlet.Filter dependendo da versão).
- Registrar o filtro usando uma classe de configuração (@Configuration) e FilterRegistrationBean, configurando a ordem para que ele execute antes dos interceptors.
- Usar o logger para os logs.
- Usar as classes HttpServletRequest, HttpServletResponse e FilterChain para manipulação da requisição.
- Manter o uso do formato de data/hora legível (exemplo: "dd-MM-yyyy HH:mm:ss").

## Critérios de Aceite

- Filtro funcionando em todos os endpoints do projeto Users, exceto rotas liberadas.
- Requisições bloqueadas sem token válido respondem com 401 e mensagem JSON clara.
- Logs aparecem no console com informações completas da requisição e tempo.
- Código organizado e com tratamento adequado de exceções.

---

# 🧪 Desafio Prático – Spring Validation: Validação de Dados do Usuário

## 🎯 Objetivo

Adicionar validação robusta ao endpoint de criação de usuário, garantindo a integridade dos dados recebidos.

## 📄 Modelo da Requisição (Atualizado)

POST /v1/users

```json
{
  "name": "Alexan Lima",
  "email": "alexan@email.com",
  "status": "ATIVO"
}
```

## 🛠️ Regras de Validação

Aplique as validações no DTO de entrada (UserRequestDTO) com as seguintes regras:

- name: Obrigatório, entre 3 e 50 caracteres – @NotBlank, @Size(min=3, max=50)
- email: Obrigatório, formato válido – @NotBlank, @Email
- status: Obrigatório, deve ser ATIVO ou INATIVO (enum) – @NotNull

## ⚠️ Requisições Inválidas

Se algum campo estiver inválido, a API deve retornar HTTP 400 Bad Request com a seguinte estrutura:

```json
{
  "status": 400,
  "erros": [
    {
      "campo": "email",
      "mensagem": "Formato de e-mail inválido"
    },
    {
      "campo": "name",
      "mensagem": "O nome deve ter entre 3 e 50 caracteres"
    }
  ]
}
```

## 🧩 Componentes obrigatórios

1. DTO com validações
   - Nome é obrigatório
   - min = 3, max = 50, message = "Nome deve ter entre 3 e 50 caracteres"
   - Email é obrigatório
   - Formato de e-mail inválido
   - Status é obrigatório
2. No Controller
   - Adicione @Valid na assinatura do método POST.
3. Handler Global
   - Crie uma classe GlobalExceptionHandler com @ControllerAdvice para capturar MethodArgumentNotValidException e formatar os erros no padrão acima.

## ✅ Critérios de Aceite

- Validações aplicadas corretamente no DTO
- Mensagens de erro personalizadas
- Handler global funcionando com formato esperado
- Testado no Postman com diferentes erros
- Não permitir criar usuários inválidos

## ⭐ Extra (se quiser ir além)

- Centralizar mensagens em ValidationMessages.properties
- Criar validação customizada de email corporativo (ex: só permitir email que termina com @empresa.com)
- Criar testes automatizados com Spring Boot Test para casos válidos e inválidos

---

# 🏆 Desafio Prático – Spring AOP: Logging e Auditoria

## 🎯 Objetivo

Adicionar aspectos transversais de logging e auditoria utilizando Spring AOP, de forma não intrusiva ao código de negócio.

## 📝 Descrição

Criar um aspecto que registre automaticamente informações relevantes sempre que métodos do controller ou service forem executados, incluindo:

- Nome do método e classe
- Parâmetros recebidos
- Tempo de execução
- Resultado retornado (quando aplicável)

Além disso, registrar ações sensíveis de auditoria, como criação, atualização e exclusão de usuários.

## 📌 Requisitos Técnicos

- Utilizar @Aspect e @Around, @Before, @AfterReturning onde apropriado.
- Registrar logs de:
  - Tempo de execução dos métodos (@Around)
  - Ações de CRUD no service (@AfterReturning)
- Criar uma anotação customizada @Auditable para marcar métodos que devem ser auditados.
- Criar uma classe Audit para representar os loggers de auditoria contendo:
  - Usuário executando a ação (mockado ou fixo para fins do exercício)
  - Ação executada (ex: "CRIAR_USUARIO")
  - Data/hora
  - Informações relevantes (ex: ID do usuário afetado)

## 📄 Exemplo de JSON simulado de auditoria

```json
{
  "usuario": "admin",
  "acao": "CRIAR_USUARIO",
  "dataHora": "2025-08-11T12:35:22",
  "detalhes": "Usuário com ID 15 criado com sucesso."
}
```

## 📂 Estrutura sugerida

```text
aop/
 ├── LoggingAspect.java
 ├── AuditAspect.java
 ├── Auditable.java
 └── Audit.java
```

## ✅ Dependências necessárias no pom.xml

- [Spring Boot Starter AOP](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-aop)

## ✅ Critérios de Aceite

- Logs devem conter nome do método, tempo de execução e parâmetros.
- Operações de criação, atualização e exclusão devem ser auditadas.
- Anotação @Auditavel usada corretamente.
- A lógica de auditoria deve estar separada da lógica de negócio.
- Auditorias devem ser impressas no console ou armazenadas em uma lista para simulação.

# 🏆 Desafio Prático – Spring Security com JWT + RBAC

## 🎯 Objetivo

Adicionar autenticação e autorização baseadas em JWT e papéis de usuário (ROLE_ADMIN, ROLE_USER) no projeto de gerenciamento de usuários.

## 🛡️ Regras do Sistema

### 🔐 Autenticação

- Criar endpoint de login (POST /auth/login) que aceita email e senha.
- Retornar um JWT Token válido contendo as roles do usuário.
- Usar BCryptPasswordEncoder para armazenar senhas com segurança.

### 🔓 Autorização (RBAC)

- Usuários com ROLE_ADMIN podem:
  - Criar, atualizar, deletar e listar todos os usuários.
- Usuários com ROLE_USER podem:
  - Listar e buscar apenas o próprio usuário (GET /v1/users/{id}).
  - Não podem criar, atualizar ou deletar outros usuários.

## 📌 Endpoints esperados

|Método|  Endpoint             |  Acesso          | Descrição                |
|------|-----------------------|------------------|--------------------------|
|POST  |  /auth/login          |  Público         | Login com e-mail/senha   |
|GET  |  /v1/users            |  ROLE_ADMIN     | Listar todos os usuários |
|GET  |  /v1/users/{id}       | ROLE_USER/ADMIN | Buscar usuário por ID    |
|POST  |  /v1/users            | ROLE_ADMIN      | Criar novo usuário       |
|PUT   |  /v1/users/{id}       | ROLE_ADMIN      | Atualizar usuário        |
|PATCH |  /v1/users/{id}/status| ROLE_ADMIN      | Alterar status do usuário|
|DELETE|  /v1/users/{id}       | ROLE_ADMIN      | Deletar usuário          |

## 🔐 Segurança

- Toda requisição (exceto /auth/login) deve conter o header:
  - Authorization: Bearer <token>
  - O token deve expirar após X minutos (ex: 30min).
  - Implementar filtro JwtAuthFilter que valida o token antes de acessar os endpoints protegidos.

## 👤 Modelo de Usuário

- Atualize o modelo User para conter:

```java
@ElementCollection(fetch = FetchType.EAGER)
private Set<Role> roles; // ex: [ROLE_ADMIN]
```

## 🔧 Sugestões de Implementação

- Pacote security.config → SecurityConfig.java
- Pacote security.jwt → JwtService.java, JwtAuthFilter.java
- Pacote auth.controller → AuthController.java
- Pacote auth.dto → LoginRequest, LoginResponse
- Interface UserDetailsService para carregar usuários por e-mail
- Classe UserDetailsImpl para adaptar o modelo de usuário

## 📝 Exemplo de Requisição

**POST /auth/login**

```json
{
  "email": "admin@email.com",
  "senha": "123456"
}
```

**Resposta**

```json
{
  "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
  "tipo": "Bearer"
}
```

## 🧱 Estrutura de Pacotes Sugerida

```text
src/main/java/com/seuprojeto/
  └── auth/
      ├── controller/AuthController.java
      ├── dto/LoginRequest.java, LoginResponse.java
  └── security/
      ├── config/SecurityConfig.java
      ├── jwt/JwtService.java
      ├── jwt/JwtAuthFilter.java
      ├── service/UserDetailsServiceImpl.java
      ├── model/UserDetailsImpl.java
```

## ✅ Dependências necessárias no pom.xml

- [Spring Boot Starter Security](https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-security)
- [Spring Security Test](https://mvnrepository.com/artifact/org.springframework.security/spring-security-test)
- [Libraries for Token Signing/Verification](https://www.jwt.io/libraries?programming_language=java)

## ✅ Critérios de Aceite

- Login funcional com JWT válido
- Senhas criptografadas com BCrypt
- Endpoint /v1/users/** protegido por roles corretas
- JWT contém informações básicas do usuário (sub, roles, exp)
- JWT validado em todas as requisições autenticadas
- Acesso negado para usuários sem permissão correta




# 🔸 Desafio Prático – SQL Avançado no Projeto de Gerenciamento de Usuários

Neste desafio, você deve aplicar conhecimentos de SQL avançado ao seu projeto atual. O foco será em:
Joins complexos para relatórios e consultas personalizadas
Criação de índices para otimização de desempenho
Execução de transações com controle explícito
Versionamento dos scripts SQL no repositório Git

## 🎯 Objetivo

Você irá criar um conjunto de scripts SQL que definem, populam e consultam as tabelas envolvidas no gerenciamento de usuários. Também deve garantir que esses scripts estejam organizados e versionados corretamente no seu projeto.

## 🛠️ Instruções

1. **Estrutura de Diretórios**

No seu projeto Java com Spring Boot, crie a seguinte estrutura:

```text
src/
└── main/
    └── resources/
        └── db/
            └── migration/
                V1__create_schema.sql
                V2__insert_seed.sql
                V3__complex_queries.sql
```

`OBS`: Mesmo sem usar Flyway agora, essa estrutura facilita o versionamento e futura automação.

2. **Script V1 – Schema**

Crie o script V1__create_schema.sql com as tabelas abaixo:

`users:`

- id (PK)
- username (único)
- email
- password
- role (ex: ADMIN, USER)
- created_at

`profiles:`

- id (PK)
- user_id (FK para users.id)
- bio
- avatar_url

`login_attempts:`

- id (PK)
- user_id (FK para users.id)
- attempt_time
- success (boolean)

**Requisitos:**

- Use FOREIGN KEY adequadamente
- Defina pelo menos um índice composto que faça sentido (ex: para busca rápida de tentativas de login por usuário + data)

3. **Script V2 – Dados Falsos (Seed)**

Crie o script V2__insert_seed.sql com pelo menos:

- 3 usuários (ADMIN e USER)
- 3 perfis correspondentes
- 5 tentativas de login (sucessos e falhas)
- Use instruções SQL claras e comentadas.

4. **Script V3 – Consultas Avançadas**

Crie consultas SQL que:

- Retornem usuários com mais de 2 tentativas de login falhas nos últimos 7 dias
- Listem todos os usuários com seus perfis (usando JOIN)
- Contem o número de tentativas de login por usuário (com LEFT JOIN)
- Mostrem usuários e a data da última tentativa de login (use JOIN + MAX)
- Usem CASE WHEN para exibir status de login baseado em última tentativa

`OBS`: **Não copie diretamente da internet. Comente seu raciocínio no próprio SQL.**

5. **Transações**

Crie um bloco de transação que:

- Insere um novo usuário
- Insere seu perfil
- Simula uma tentativa de login bem-sucedida
- Se algum passo falhar, nada deve ser persistido.
- Use comandos como:

```sql
BEGIN;
-- comandos
COMMIT;
-- ou ROLLBACK em caso de erro
```

## ✅ Entregáveis

- Scripts .sql criados na pasta /resources/db/migration
- Código versionado no GitHub (não subir o banco em si, apenas scripts)
- Um README explicando como rodar os scripts manualmente (em DBeaver, pgAdmin, etc)
- (Opcional) Configurar o Flyway no projeto e ativar execução automática ao rodar a aplicação

## 📚 Dicas

- Você pode testar os scripts usando seu próprio banco de desenvolvimento local
- Use EXPLAIN ANALYZE para checar performance se quiser aprofundar
- Se usar Flyway, não precisa criar tabelas manualmente — ele controla as versões para você

# proxima atividade