/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Cafer
 */
public class Campo {

    private final Tipo tipo; //1: interiro 2: String
    private final String nome;
    private boolean chavePrimaria;

    public Campo(Tipo tipo, String nome) {
        this.tipo = tipo;
        this.nome = nome;
        this.chavePrimaria = false;
    }
    
    public Campo(int tipo, String nome) {
        if(tipo == 1) {
            this.tipo = Tipo.INTEGER;
        }
        else {
            this.tipo = Tipo.STRING;
        }
        this.nome = nome;
        this.chavePrimaria = false;
    }

    public Tipo getTipo() {
        return tipo;
    }
    
    public int getCodTipo() {
        return Tipo.getTipo(tipo);
    }

    public String getAtributo() {
        return nome;
    }

    public boolean isChavePrimaria() {
        return chavePrimaria;
    }

    public void setPK(boolean pk) {
        this.chavePrimaria = pk;
    }
    
    @Override
    public String toString() {
        String nomeTipo;
        if(tipo == Tipo.INTEGER) {
            nomeTipo = "INTEGER";
        }
        else {
            nomeTipo = "STRING";
        }
        return nomeTipo + ", " + nome;
    }
}
