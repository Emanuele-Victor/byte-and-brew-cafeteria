package br.edu.cafeteria.modelo;

public class ItemPedido {
	
	    private Produto produto;
	    private int quantidade;
	    private double precoUnitarioNoMomento;

	    public ItemPedido(Produto produto, int quantidade) {
	        this.produto = produto;
	        this.quantidade = quantidade;
	        this.precoUnitarioNoMomento = produto.getPrecoBase(); // Congela o preço atual
	    }

	    public double getSubtotal() {
	        return this.precoUnitarioNoMomento * this.quantidade;
	    }

	    // Getters
	    public Produto getProduto() { return produto; }
	    public int getQuantidade() { return quantidade; }
	    public double getPrecoUnitarioNoMomento() { return precoUnitarioNoMomento; }
	}

