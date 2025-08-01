package service;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

import model.Email;

public class EmailService {

    public CompletableFuture<String> enviarEmail(Email email) {

        return CompletableFuture.supplyAsync(() -> {
            System.out.println("Enviando email para: " + email.getDestinatario());
            simularTempoEnvio();
            if (!email.getDestinatario().contains("@")) {
                throw new RuntimeException();
            }
            return email.getDestinatario();
        });
    }

    private void simularTempoEnvio() {
        try {
            Thread.sleep(500 + new Random().nextInt(2001));
        } catch (Exception e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }
}