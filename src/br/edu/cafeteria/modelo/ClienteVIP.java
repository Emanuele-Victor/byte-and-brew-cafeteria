package br.edu.cafeteria.modelo;

import br.edu.cafeteria.excecao.PontosInsuficientesException;

public class ClienteVIP extends Cliente {
    public static final double TAXA_CONVERSAO_XP = 10.0;

    public ClienteVIP(String nome, String cpf, double saldoXP) {
        super(nome, cpf, saldoXP);
    }

    @Override
    public double calcularXPGanhado(double valorGasto) {
        return valorGasto * 2.0;
    }

    public void pagarComXP(double valorTotal) throws PontosInsuficientesException {
        double pontosNecessarios = valorTotal * TAXA_CONVERSAO_XP;
        
        if (this.getSaldoXP() < pontosNecessarios) {
            throw new PontosInsuficientesException("Saldo de XP insuficiente! Você precisa de " 
                    + pontosNecessarios + " XP, mas possui apenas " + this.getSaldoXP() + " XP.");
        }
        
        this.setSaldoXP(this.getSaldoXP() - pontosNecessarios);
    }
}