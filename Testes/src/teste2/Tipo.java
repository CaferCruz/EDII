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
public enum Tipo {
    INTEGER, STRING;
    
    public static int getTipo(Tipo tipo) {
        if(tipo == INTEGER) {
            return 1;
        }
        else { // case seja String 
            return 2;
        }
    }
}
