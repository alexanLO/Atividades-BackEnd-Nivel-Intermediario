## Desafio: Sistema de Envio de E-mails Assíncronos

Você está desenvolvendo um sistema que envia e-mails de forma assíncrona para os usuários após determinadas ações (ex: compra realizada, cadastro efetuado, recuperação de senha). Como o envio real leva tempo e pode falhar, vamos simular esse comportamento.

### Objetivo
Criar um sistema em Java que:

1. Recebe uma lista de e-mails e mensagens.
2. Envia cada e-mail usando CompletableFuture.supplyAsync(...).
3. Simula um tempo de envio entre 0.5 e 2 segundos.
4. Trata possíveis falhas (ex: se o endereço for inválido).
5. Exibe quais envios foram bem-sucedidos e quais falharam.
6. Mostra o tempo total de envio.

### Critérios de aceitação

- Receber uma lista de e-mails e mensagens.
- Usar CompletableFuture.supplyAsync(...) para enviar e-mails.
- Simular um tempo de envio entre 0.5 e 2 segundos.
- Tratar falhas, considerando e-mails inválidos (sem @).
- Exibir os envios bem-sucedidos e os que falharam.
- Mostrar o tempo total de envio.

### Regras:

* O e-mail é considerado inválido se não contiver @.
* O envio deve simular uma demora aleatória (entre 500ms e 2000ms).
* Usar encadeamentos do CompletableFuture: .thenApply, .handle, .whenComplete, .exceptionally, etc.
* Usar CompletableFuture.allOf(...) para aguardar todos os envios.
* Medir o tempo com Instant.now() e Duration.between(...).

### Estrutura Sugerida:

``` Java
public class Email {     
    private String destinatario;     
    private String mensagem;      
    // Construtor, getters, toString() 
} 

public class EmailService {     
    public CompletableFuture<String> enviarEmail(Email email) {         
        // Simula envio com Thread.sleep e valida endereço     
    } 
} 

public class App {     
    public static void main(String[] args) {         
        // Cria lista de e-mails         
        // Envia todos assíncronamente         
        // Aguarda todos finalizarem         
        // Mostra resultado e tempo     
    } 
} 
```

### Resultado esperado:

``` yaml
Enviando email para: usuario1@gmail.com 
Enviando email para: emailinvalido.com 
Enviando email para: cliente@empresa.com 
Falha ao enviar para emailinvalido.com: Endereço inválido 
Email enviado com sucesso para: usuario1@gmail.com 
Email enviado com sucesso para: cliente@empresa.com 
Tempo total: 2.126s
```