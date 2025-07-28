package utils;

public class OperacaoUtils {

    public static void executar(String descricao, OperacaoComExcecao operacao) {
        System.out.print(descricao + ": ");
        try {
            operacao.executar();
        } catch (Exception e) {
            System.out.println("Erro - " + e.getMessage());
        }
    }
}
