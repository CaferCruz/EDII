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
    private String coluna;
    
    public Campo(int tipo, String coluna) {
        this.tipo = tipo;
        this.coluna = coluna;
    }

    public int getTipo() {
        return tipo;
    }

    public String getColuna() {
        return coluna;
    }
    
}
