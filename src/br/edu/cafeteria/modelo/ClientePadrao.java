package br.edu.cafeteria.modelo;


	public class ClientePadrao extends Cliente {

	    public ClientePadrao(String nome, String cpf, double saldoXP) {
	        super(nome, cpf, saldoXP);
	    }

	    @Override
	    public void adicionarXP(double valorGasto) {
	        // Regra: R$ 1,00 = 1 XP
	        double xpGanho = valorGasto * 1.0;
	        this.setSaldoXP(this.getSaldoXP() + xpGanho);
	    }
	}

