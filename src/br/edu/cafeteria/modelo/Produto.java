package br.edu.cafeteria.modelo;


public abstract class Produto {
	
	    private int id;
	    private String nome;
	    private double precoBase;
	    private int quantidadeEstoque;
	    public Produto(int id, String nome, double precoBase, int quantidadeEstoque) {
	        this.id = id;
	        this.nome = nome;
	        this.precoBase = precoBase;
	        this.quantidadeEstoque = quantidadeEstoque;
	    }

	    public void baixarEstoque(int quantidade) {
	        this.quantidadeEstoque -= quantidade;
	    }

	    public int getId() { return id; }
	    public void setId(int id) { this.id = id; }

	    public String getNome() { return nome; }
	    public void setNome(String nome) { this.nome = nome; }

	    public double getPrecoBase() { return precoBase; }
	    public void setPrecoBase(double precoBase) { this.precoBase = precoBase; }

	    public int getQuantidadeEstoque() { return quantidadeEstoque; }
	    public void setQuantidadeEstoque(int quantidadeEstoque) { this.quantidadeEstoque = quantidadeEstoque; }
	}

