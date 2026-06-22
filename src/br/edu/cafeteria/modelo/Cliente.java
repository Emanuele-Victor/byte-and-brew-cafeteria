package br.edu.cafeteria.modelo;

public abstract class Cliente {
	
	    private String nome;
	    private String cpf;
	    private double saldoXP;

	    public Cliente(String nome, String cpf, double saldoXP) {
	        this.nome = nome;
	        this.cpf = cpf;
	        this.saldoXP = saldoXP;
	    }

	    // Método Abstrato: força o polimorfismo por sobrescrita nas subclasses
	    public abstract void adicionarXP(double valorGasto);

	    // Getters e Setters
	    public String getNome() { return nome; }
	    public void setNome(String nome) { this.nome = nome; }

	    public String getCpf() { return cpf; }
	    public void setCpf(String cpf) { this.cpf = cpf; }

	    public double getSaldoXP() { return saldoXP; }
	    public void setSaldoXP(double saldoXP) { this.saldoXP = saldoXP; }
	}

