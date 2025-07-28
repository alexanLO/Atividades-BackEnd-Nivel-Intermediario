## Exercício: Cadastro e Verificação de E-mails com Optional

#### Resumo
Desenvolver um sistema de login simples que utilize Optional para evitar que o sistema quebre devido a valores null.

#### Contexto
O sistema deve gerenciar usuários cadastrados em memória. Alguns usuários podem não ter email ou senha definidos.

#### Critérios de aceitação
- Se o email for null, retornar "Email não pode ser nulo".
- Se o usuário não for encontrado, retornar "Usuário não encontrado".
- Se o usuário for encontrado mas não tiver senha cadastrada, retornar "Usuário sem senha cadastrada".
- Se a senha estiver errada, retornar "Senha incorreta".
- Se tudo estiver certo, retornar "Login bem-sucedido para [nome]".

#### Outras informações
###### Estrutura

``` Java
class Usuario {    
    private String nome;     
    private String email;     
    private String senha;      
    public Usuario(String nome, String email, String senha) {         
        this.nome = nome;         
        this.email = email;         
        this.senha = senha;     
    }      
    // Getters 
}
```

``` Java
class UsuarioRepository {     
    private List<Usuario> usuarios = Arrays.asList(         
        new Usuario("Maria", "maria@gmail.com", "1234"),         
        new Usuario("João", null, "abcd"),         
        new Usuario("Ana", "ana@gmail.com", null)     
    );      
    public Optional<Usuario> buscarPorEmail(String email) {         
        return usuarios.stream()             
            .filter(u -> email != null && email.equalsIgnoreCase(u.getEmail()))             
            .findFirst();     
    } 
} 
```

###### Desafio
Implementar a seguinte classe de serviço:

``` Java
class LoginService {     
    private UsuarioRepository repo;      
    public LoginService(UsuarioRepository repo) {         
        this.repo = repo;     
    }      
    public String autenticar(String email, String senha) {         
        // TODO: Implemente usando Optional     
    } 
} 
```

###### Exemplos de uso

``` Java
LoginService login = new LoginService(new UsuarioRepository());  
System.out.println(login.autenticar(null, "123"));           // Email não pode ser nulo 
System.out.println(login.autenticar("desconhecido@email.com", "123")); // Usuário não encontrado 
System.out.println(login.autenticar("ana@gmail.com", "qualquer"));     // Usuário sem senha cadastrada 
System.out.println(login.autenticar("maria@gmail.com", "errado"));     // Senha incorreta 
System.out.println(login.autenticar("maria@gmail.com", "1234"));       // Login bem-sucedido para Maria 
```

###### Restrições
- Deve usar Optional em todas as verificações possíveis.
- Não pode usar `if (obj == null):` use `Optional.ofNullable(obj)` e seus métodos (map, filter, orElse, orElseThrow, etc).