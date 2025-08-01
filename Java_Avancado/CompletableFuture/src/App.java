import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.stream.Collectors;

import model.Email;
import service.EmailService;

public class App {
    public static void main(String[] args) throws Exception {

        EmailService service = new EmailService();

        Instant iniciarEnvio = Instant.now();
        // Cria lista de e-mails
        List<Email> emails = List.of(
                new Email("usuario1@gmail.com", ""),
                new Email("emailinvalido.com", ""),
                new Email("cliente@empresa.com", ""),
                new Email("funcionario@empresa.com", ""));

        // Envia todos assíncronamente
        List<CompletableFuture<String>> emailsEnviando = emails.stream()
                .map(email -> service.enviarEmail(email)
                        .orTimeout(1999, TimeUnit.MILLISECONDS)
                        .thenApply(result -> "Email enviado com sucesso para: " + result)
                        .exceptionally(ex -> {
                            if (ex.getCause() instanceof RuntimeException) {
                                return "Endereço inválido: " + email.getDestinatario();
                            }
                            if (ex.getCause() instanceof TimeoutException) {
                                return "Tempo de envio do email: " + email.getDestinatario() + " foi excedido!";
                            }
                            return email.getDestinatario();
                        }))
                .collect(Collectors.toList());

        // Aguarda todos finalizarem
        CompletableFuture.allOf(
                emailsEnviando.toArray(new CompletableFuture[0])).thenRun(() -> {
                    emailsEnviando.forEach(e -> System.out.println(e.join()));
                }).join();

        Instant finalizarEnvio = Instant.now();
        Double duracao = Duration.between(iniciarEnvio, finalizarEnvio).toMillis() / 1000.0;
        System.out.printf("Tempo total de envio: %.3fs%n", duracao);
    }
}
