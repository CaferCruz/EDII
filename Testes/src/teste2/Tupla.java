/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package teste2;

import java.util.List;

/**
 *
 * @author Romulo
 */
public class Tupla {
    
    private List<Object> atributos;
    private List<Campo> campos;
    private int chavePrimaria;
    
    public Tupla(List<Campo> campos) {
        this.campos = campos; 
    }
    
    
    
}
