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
public class Tabela {

    private final String nome;
    private final List<Campo> campos;
    private final int posChave;
        
    public Tabela(List<Campo> campos, String nome, int posChave) {
        this.campos = campos;
        this.nome = nome;
        this.posChave = posChave;
    }
    
        
    
    
}
