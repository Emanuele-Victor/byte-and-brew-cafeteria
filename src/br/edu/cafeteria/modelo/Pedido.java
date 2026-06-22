package br.edu.cafeteria.modelo;

import java.util.ArrayList;
import java.util.List;
import br.edu.cafeteria.excecao.EstoqueInsuficienteException;

public class Pedido {
    private static int contadorId = 0;
    private int numeroSequencial;
    private String atendente;
    private Cliente cliente;
    private List<ItemPedido> items;
    private double descontoAplicado;

    public Pedido(String atendente, Cliente cliente) {
        contadorId++;
        this.numeroSequencial = contadorId;
        this.atendente = atendente;
        this.cliente = cliente;
        this.items = new ArrayList<>();
        this.descontoAplicado = 0.0;
    }

    public void adicionarItem(Produto p) throws EstoqueInsuficienteException {
        this.adicionarItem(p, 1);
    }

    public void adicionarItem(Produto p, int quantidade) throws EstoqueInsuficienteException {
        if (p.getQuantidadeEstoque() < quantidade) {
            throw new EstoqueInsuficienteException("Estoque insuficiente! O produto " + p.getNome() + 
                    " só possui " + p.getQuantidadeEstoque() + " unidades.");
        }
        
        ItemPedido item = new ItemPedido(p, quantidade);
        items.add(item);
        p.baixarEstoque(quantidade);
    }

    public double calcularValorTotal() {
        double total = 0.0;
        for (ItemPedido item : items) {
            total += item.getSubtotal();
        }
        return total - descontoAplicado;
    }

    public void aplicarDesconto(Pedido pedido) {
        this.descontoAplicado = 0.0;
    }

    public void finalizarVenda() {
        System.out.println("Venda finalizada com sucesso!");
    }

    public int getNumeroSequencial() {
        return numeroSequencial;
    }

    public String getAtendente() {
        return atendente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public List<ItemPedido> getItems() {
        return items;
    }

    public double getDescontoAplicado() {
        return descontoAplicado;
    }
}