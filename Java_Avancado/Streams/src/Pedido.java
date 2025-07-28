import java.util.List;

public class Pedido {
    String cliente;
    List<Item> itens;

    public Pedido(String cliente, List<Item> itens) {
        this.cliente = cliente;
        this.itens = itens;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public List<Item> getItens() {
        return itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }
}
