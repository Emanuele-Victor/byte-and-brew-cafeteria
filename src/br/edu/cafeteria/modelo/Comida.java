package br.edu.cafeteria.modelo;

public class Comida extends Produto {
    private boolean vegano;
    private boolean semGluten;
    private boolean semLactose;
    private int tempoPreparoMinutos;

    public Comida(int codigo, String nome, double precoBase, int quantidadeEstoque, boolean vegano, boolean semGluten, boolean semLactose, int tempoPreparoMinutos) {
        super(codigo, nome, precoBase, quantidadeEstoque);
        this.vegano = vegano;
        this.semGluten = semGluten;
        this.semLactose = semLactose;
        this.tempoPreparoMinutos = tempoPreparoMinutos;
    }

    public boolean isVegano() { return vegano; }
    public boolean isSemGluten() { return semGluten; }
    public boolean isSemLactose() { return semLactose; }
    public int getTempoPreparoMinutos() { return tempoPreparoMinutos; }
}