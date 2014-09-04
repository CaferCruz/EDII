/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancodedados;

import java.io.DataInputStream;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Cafer
 */
public class Tabela {

    private String nomeTabela;

    public Tabela(String nomeTabela, ArrayList<Campo> campos) throws FileNotFoundException, IOException {
        this.nomeTabela = nomeTabela;
        criar(this.nomeTabela, campos);
    }

    private void criar(String nome, ArrayList<Campo> campos) throws FileNotFoundException, IOException {
        DataOutputStream escrever = null;
        try {
            escrever = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(nome.concat(".dat"))));
            //colocar as campos no arquivo.
        }//
        catch (FileNotFoundException ex) {
            throw new FileNotFoundException("Arquivo não pode ser criado.");
        } ///   
        catch (IOException e) {
            throw new IOException("Um Erro ocorreu.");
        } finally {
            if (escrever != null) {
                escrever.close();
            }
        }
    }

}
