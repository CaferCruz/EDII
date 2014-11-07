package teste2;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Romulo
 */
public class Campo {

    private final Tipo tipo; //1: interiro 2: String
    private final String nome;

    public Campo(Tipo tipo, String nome) {
        this.tipo = tipo;
        this.nome = nome;
    }
    
    public Campo(int tipo, String nome) {
        if(tipo == 1) {
            this.tipo = Tipo.INTEGER;
        }
        else {
            this.tipo = Tipo.STRING;
        }
        this.nome = nome;
    }

    public Tipo getTipo() {
        return tipo;
    }
    
    public int getCodTipo() {
        return Tipo.getTipo(tipo);
    }

    public String getNomeAtributo() {
        return nome;
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
