import java.util.Optional;

import exceptions.ContaInvalidaException;
import exceptions.SaldoInsuficienteException;
import exceptions.ValorInvalidoException;

public class ContaBancaria {
    private String numero;
    private double saldo;

    public ContaBancaria(String numero, double saldoInicial) {
        this.numero = numero;
        this.saldo = saldoInicial;
    }

    public String getNumero() {
        return numero;
    }

    public double getSaldo() {
        return saldo;
    }

    public void sacar(double valor) throws SaldoInsuficienteException, ValorInvalidoException {

        if(valor > saldo){
            throw new SaldoInsuficienteException("Saldo insuficiente para operação.");
        }

        if(valor <= 0){
            throw new ValorInvalidoException("Valor inválido para operação.");
        }
        
        saldo -= valor;
    }

    public void depositar(double valor) throws ValorInvalidoException {
        if (valor <= 0) {
              throw new ValorInvalidoException("Valor inválido para operação.");
        }

        saldo += valor;
    }

    public void transferir(ContaBancaria destino, double valor) throws Exception {
        
        if (Optional.of(destino).isEmpty()) {
            throw new ContaInvalidaException("Conta de destino é inválida.");
        }

        try {
            saldo -= valor;
            destino.saldo += valor;
        } finally {
           System.out.println("Transferência encerrada");
        }
    }
}
