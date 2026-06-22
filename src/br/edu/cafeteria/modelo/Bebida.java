package br.edu.cafeteria.modelo;

public class Bebida extends Produto{
	
	    private String tamanho;
	    private double quantidadeCafeina;

	    public Bebida(int id, String nome, double precoBase, int quantityEstoque, String tamanho, double quantidadeCafeina) {
	        super(id, nome, precoBase, quantityEstoque);
	        this.tamanho = tamanho;
	        this.quantidadeCafeina = quantidadeCafeina;
	    }

	    // Getters e Setters específicos
	    public String getTamanho() { return tamanho; }
	    public void setTamanho(String tamanho) { this.tamanho = tamanho; }

	    public double getQuantidadeCafeina() { return quantidadeCafeina; }
	    public void setQuantidadeCafeina(double quantidadeCafeina) { this.quantidadeCafeina = quantidadeCafeina; }
	}

