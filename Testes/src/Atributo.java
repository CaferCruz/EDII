/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Romulo
 */
public class Atributo {
    
    private int valor;
    private String nome; // o nome tem 4 caracteres 
    private final Tipo tipo;
    
    public Atributo(String nome) {
        if(nome.length() < 10) {
            for (int i = nome.length(); i < 10; i++) {
                nome += " ";
            }
        }
        this.nome = nome;
        tipo = Tipo.STRING;
    }    
    
    public Atributo(int valor) {
        this.valor = valor;
        tipo = Tipo.INTEGER;
    }
    
    public int getValor() {
        return valor;
    }
    
    public String getNome() {
        return nome;
    }
    
    public Tipo getTipo() {
        return tipo;
    }
    
    @Override
    public String toString() {
        if(tipo == Tipo.STRING) {
            return this.nome + " ";
        }
        else if(tipo == Tipo.INTEGER){
            return this.valor + " ";
        }
        else {
            return "";
        }
    }    
    
}
