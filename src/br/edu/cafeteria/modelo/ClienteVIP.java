package br.edu.cafeteria.modelo;


	public class ClienteVIP extends Cliente {
	    // Uso de atributo estático constante (Checklist de OO)
	    public static final int TAXA_CONVERSAO_XP = 10;

	    public ClienteVIP(String nome, String cpf, double saldoXP) {
	        super(nome, cpf, saldoXP);
	    }

	    @Override
	    public void adicionarXP(double valorGasto) {
	        // Regra VIP: R$ 1,00 = 2 XP
	        double xpGanho = valorGasto * 2.0;
	        this.setSaldoXP(this.getSaldoXP() + xpGanho);
	    }

	    // Método exclusivo do VIP para pagar usando pontos
	    public void pagarComXP(double valorTotal) {
	        double pontosNecessarios = valorTotal * TAXA_CONVERSAO_XP;
	        // A validação de pontos lançará a exceção customizada (vamos ligar isso no fluxo final)
	        this.setSaldoXP(this.getSaldoXP() - pontosNecessarios);
	    }
	}

