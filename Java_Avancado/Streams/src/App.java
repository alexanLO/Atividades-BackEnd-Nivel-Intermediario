import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class App {
    public static void main(String[] args) throws Exception {

        List<Pedido> pedidos = Arrays.asList(
                new Pedido("Maria", Arrays.asList(
                        new Item("Caneta", 10, 1.5),
                        new Item("Caderno", 2, 12.0))),
                new Pedido("João", Arrays.asList(
                        new Item("Lápis", 5, 0.5),
                        new Item("Caderno", 1, 12.0))),
                new Pedido("Caio", Arrays.asList(
                        new Item("Borracha", 3, 0.75))));

        // ---------- ATIVIDADE 1 ----------

        System.out.println("1. Listar todos os nomes dos clientes distintos: ");
        List<String> clientes = pedidos.stream().map(Pedido::getCliente).collect(Collectors.toList());

        System.out.println(clientes + "\n");

        // ---------- ATIVIDADE 2 ----------

        System.out.println("2. Calcular o valor total de cada pedido: ");
        List<Double> valorTotalPedidos = pedidos.stream()
                .map(pedido -> pedido.itens.stream().mapToDouble(item -> item.precoUnitario * item.quantidade).sum())
                .collect(Collectors.toList());

        System.out.println("Total de cada pedido: " + valorTotalPedidos + "\n");

        // ---------- ATIVIDADE 3 ----------

        System.out.println(
                "3. Gerar um Map<String, Double> com o nome do cliente e o total gasto por ele (somando todos os seus pedidos): ");
        Map<String, Double> totalPorCliente = pedidos.stream()
                .collect(Collectors.groupingBy(Pedido::getCliente, Collectors.summingDouble(
                        pedido -> pedido.itens.stream().mapToDouble(item -> item.precoUnitario * item.quantidade)
                                .sum())));

        System.out.println("Total por cliente: " + totalPorCliente + "\n");

        // ---------- ATIVIDADE 4 ----------

        System.out.println("4. Listar todos os itens únicos pedidos (sem repetir nomes): ");
        Set<String> itensUnicos = pedidos.stream().flatMap(pedido -> pedido.getItens().stream()).map(Item::getNome)
                .collect(Collectors.toSet());

        System.out.println("Lista de itens: " + itensUnicos + "\n");

        // ---------- ATIVIDADE 5 ----------

        System.out.println("5. Obter a média de valor por item em todos os pedidos: ");
        DoubleSummaryStatistics valorMedioPedido = pedidos.stream()
                .flatMap(pedido -> pedido.itens.stream())
                .mapToDouble(item -> item.precoUnitario)
                .summaryStatistics();

        System.out.println("Valor médio de todos os pedido: " + valorMedioPedido.getAverage() + "\n");

        // ---------- ATIVIDADE 6 ----------

        System.out.println("6. Listar os pedidos cujo valor total ultrapassa R$ 20: ");
        List<Pedido> pedidosAcimaDe20 = pedidos.stream()
                .filter(pedido -> pedido.itens.stream().mapToDouble(item -> item.precoUnitario * item.quantidade).sum() > 20)
                .collect(Collectors.toList());

        System.out.println("Pedidos: " + pedidosAcimaDe20.size());

    }
}
