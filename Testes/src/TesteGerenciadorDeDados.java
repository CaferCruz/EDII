
import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Romulo
 */
public class TesteGerenciadorDeDados {
    
    public static void main(String[] args) throws Exception {
        
        Tabela tabela = new Tabela("Pessoa"); 
        
        tabela.addCampo(new Campo(Tipo.INTEGER, "Idade"));
        tabela.addCampo(new Campo(Tipo.STRING, "Nome"));
        tabela.addCampo(new Campo(Tipo.STRING, "CPF"));
        tabela.addCampo(new Campo(Tipo.INTEGER, "Codigo"));
        
        tabela.addTupla("19, Joao, 333-444, 1");
        tabela.addTupla("23, Larissa, 292-575, 2");
        tabela.addTupla("17, Mariana, 992-777, 3");
        tabela.addTupla("16, Ramon, 888-949, 4");        
        
        System.out.println("" + tabela);
        
    }
    
}
