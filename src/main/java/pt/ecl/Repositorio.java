package pt.ecl;
import java.util.ArrayList;
import java.util.List;

public class Repositorio {
    private final List<Pedido> pedidos = new ArrayList<>();
    private int proximoId = 1;

// synchronized evita alterações simultâneas na lista.

    public synchronized void adicionar(String nome, String sala,
        String equipamento, String descricao) {
        pedidos.add(new Pedido(proximoId++, nome, sala,
        equipamento, descricao));
    }

    public synchronized List<Pedido> listar() {
        return new ArrayList<>(pedidos);
    }
    public synchronized boolean atualizar(int id, String estado,
    String observacao) {
        for (int i = 0; i < pedidos.size(); i++) {
        if (pedidos.get(i).id == id) {
        Pedido p = pedidos.get(i);
        pedidos.set(i, p.atualizado(estado, observacao));
        return true;
        }
    }
    return false;
    }
}