import utils.OperacaoUtils;

public class App {
    public static void main(String[] args) throws Exception {

        ContaBancaria conta01 = new ContaBancaria("001", 500);
        ContaBancaria conta02 = new ContaBancaria("002", 300);
    

        try {
            System.out.println("Saldo inicial da conta 001: " + conta01.getSaldo());
            System.out.println("Saldo inicial da conta 002: " + conta02.getSaldo());
            System.out.println();

            OperacaoUtils.executar("sacar: 600R$ da conta 001: ", () -> conta01.sacar(600));
            OperacaoUtils.executar("sacar: 0R$ da conta 001: ", () -> conta01.sacar(0));
            OperacaoUtils.executar("sacar: -100R$ da conta 001: ", () -> conta01.sacar(-100));
            OperacaoUtils.executar("sacar: 50R$ da conta 001: ", () -> conta01.sacar(50));
            System.out.println("Saldo atual da conta 001: " + conta01.getSaldo());
            System.out.println();

            OperacaoUtils.executar("Depositar 0R$ na conta 002:", () -> conta02.depositar(0));
            OperacaoUtils.executar("Depositar 200R$ na conta 002: ", () -> conta02.depositar(0));
            System.out.println("Saldo atual da conta 002: " + conta02.getSaldo());
            System.out.println();

    
            OperacaoUtils.executar("Transferindo 0R$ da conta 002 para conta 001: ", () -> conta02.transferir(conta01, 0));
            OperacaoUtils.executar("Transferindo 200R$ da conta 002 para conta 001: ", () -> conta02.transferir(conta01, 200));
            System.out.print("Saldo atual da conta 001: " + conta01.getSaldo());
            System.out.print("Saldo atual da conta 002: " + conta02.getSaldo());
            System.out.println();

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}
