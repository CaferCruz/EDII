/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package teste2;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Romulo
 */
public class MainTeste {
    
    public static void main(String[] args) {
        
        List<Campo> campos = new ArrayList<>();
        campos.add(new Campo(Tipo.STRING, "NOME"));
        campos.add(new Campo(Tipo.INTEGER, "IDADE"));
        campos.add(new Campo(Tipo.STRING, "CPF"));
        
        Tabela tabela  = new Tabela(campos, "Pessoa", 0);
        
    }
    
}
