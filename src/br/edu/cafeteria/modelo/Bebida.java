package br.edu.cafeteria.modelo;

public class Bebida extends Produto {
    private String tamanho;
    private double quantidadeCafeinaMg;
    private int tempoPreparoMinutos;

    public Bebida(int codigo, String nome, double precoBase, int quantidadeEstoque, String tamanho, double quantidadeCafeinaMg, int tempoPreparoMinutos) {
        super(codigo, nome, precoBase, quantidadeEstoque);
        this.tamanho = tamanho;
        this.quantidadeCafeinaMg = quantidadeCafeinaMg;
        this.tempoPreparoMinutos = tempoPreparoMinutos;
    }

    public String getTamanho() { return tamanho; }
    public void setTamanho(String tamanho) { this.tamanho = tamanho; }
    public double getQuantidadeCafeinaMg() { return quantidadeCafeinaMg; }
    public int getTempoPreparoMinutos() { return tempoPreparoMinutos; }
}