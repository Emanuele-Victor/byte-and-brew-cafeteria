package br.edu.cafeteria.modelo;

public class Comida extends Produto {
	
	    private int tempoPreparo;
	    private boolean isVeganoSemGluten;

	    // O construtor usa o "super" para passar os dados para a classe mãe
	    public Comida(int id, String nome, double precoBase, int quantidadeEstoque, int tempoPreparo, boolean isVeganoSemGluten) {
	        super(id, nome, precoBase, quantidadeEstoque);
	        this.tempoPreparo = tempoPreparo;
	        this.isVeganoSemGluten = isVeganoSemGluten;
	    }

	    // Getters e Setters específicos
	    public int getTempoPreparo() { return tempoPreparo; }
	    public void setTempoPreparo(int tempoPreparo) { this.tempoPreparo = tempoPreparo; }

	    public boolean isVeganoSemGluten() { return isVeganoSemGluten; }
	    public void setVeganoSemGluten(boolean veganoSemGluten) { isVeganoSemGluten = veganoSemGluten; }
	}

