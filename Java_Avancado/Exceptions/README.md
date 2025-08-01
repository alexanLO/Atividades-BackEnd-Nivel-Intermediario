## Desafio – Sistema de Transferência Bancária com Exceções

### Objetivo

Implementar um sistema de transferência entre contas bancárias com tratamento robusto de exceções, lidando com erros comuns como:

1. Saldo insuficiente
2. Conta inválida
3. Valor de transferência inválido

**Você deverá usar:**

- Exceções personalizadas (extends Exception)
- Blocos try-catch
- Uso de throws
- Bloco finally para garantir encerramento de operação

### Estrutura inicial

``` Java
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

    public void sacar(double valor) throws SaldoInsuficienteException {
        // TODO
    }

    public void depositar(double valor) throws ValorInvalidoException {
        // TODO
    }

    public void transferir(ContaBancaria destino, double valor) throws Exception {
        // TODO
    }
}
```

### Regras do sistema

**Criar as exceções personalizadas:**

- SaldoInsuficienteException
- ValorInvalidoException
- ContaInvalidaException

**No método sacar:**

- Lançar SaldoInsuficienteException se não houver saldo suficiente
- Lançar ValorInvalidoException se o valor for negativo ou zero

**No método depositar:**

- Lançar ValorInvalidoException se o valor for negativo ou zero

**No método transferir:**

- Lançar ContaInvalidaException se a conta de destino for null
- Sacar da conta de origem
- Depositar na conta de destino
- Usar try-finally para exibir "Transferência encerrada" ao final, mesmo que ocorra erro

**Exemplo de uso**

``` Java 
public class Main {
    public static void main(String[] args) {
        ContaBancaria origem = new ContaBancaria("001", 500);
        ContaBancaria destino = new ContaBancaria("002", 300);

        try {
            origem.transferir(destino, 200);
            System.out.println("Transferência realizada com sucesso.");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }

        System.out.println("Saldo origem: " + origem.getSaldo());
        System.out.println("Saldo destino: " + destino.getSaldo());
    }
}
```

### Resultado esperado (se tudo funcionar):

``` yaml
Transferência realizada com sucesso.
Transferência encerrada
Saldo origem: 300.0
Saldo destino: 500.0
```

### Casos que devem lançar exceção:

**Situação**	             |      **Exceção esperada**	      |     ***Mensagem sugerida***
*Valor da transferência ≤ 0* |    *ValorInvalidoException*	      |   "Valor inválido para operação."
*Conta de destino = null*    | 	  *ContaInvalidaException*	      |   "Conta de destino é inválida."
*Saldo insuficiente*	     |   *SaldoInsuficienteException*	  |   "Saldo insuficiente para transferência."

### Bônus

* Registrar em um log.txt a operação (pode simular com System.out.println)
* Implementar testes automatizados para cada erro