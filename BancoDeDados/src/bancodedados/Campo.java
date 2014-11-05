/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancodedados;

/**
 *
 * @author Cafer
 */
public class Campo {

    private int tipo; //1: interiro 2: String
    private String atributo;
    private boolean pk;

    public Campo(int tipo, String coluna) {
        this.tipo = tipo;
        this.atributo = coluna;
        this.pk = false;
    }

    public int getTipo() {
        return tipo;
    }

    public String getAtributo() {
        return atributo;
    }

    public boolean isPk() {
        return pk;
    }

    public void setPK(boolean pk) {
        this.pk = pk;
    }
    
    public String toSring(){
        return "Tipo: " + (this.tipo == 1 ? "Inteiro" : "String" )+"\t" + "Nome: " + this.atributo;
        
    }
}
