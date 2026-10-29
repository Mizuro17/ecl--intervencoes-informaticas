package pt.ecl;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Pedido {
public final int id;
public final String nome, sala, equipamento, descricao;
public final String data;
public final String estado, observacao;

    public Pedido(int id, String nome, String sala, String equipamento, String descricao) {
        this(id, nome, sala, equipamento, descricao, LocalDateTime.now().format(
        DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")),
        "Por analisar", "");
    }
    private Pedido(int id, String nome, String sala, String equipamento, String descricao, String data, String estado, String observacao) {
        this.id = id;
        this.nome = nome;
        this.sala = sala;
        this.equipamento = equipamento;
        this.descricao = descricao;
        this.data = data;
        this.estado = estado;
        this.observacao = observacao;
    }

// Produz uma nova versão, conservando os dados originais.
    public Pedido atualizado(String estado, String observacao) {
        return new Pedido(id, nome, sala, equipamento,
        descricao, data, estado, observacao);
    }
}