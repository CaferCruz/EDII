/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacao;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Cafer
 */
public class Catalago {

    public static void adicionar(String nome, ArrayList<Campo> campos) throws FileNotFoundException, IOException {
        DataOutputStream escrever = null;
        try {
            escrever = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(nome)));
            //Fazer que esteja garantindo que seja escrito no final.
            escrever.writeUTF(nome);
            for(Campo c: campos){
                escrever.write(c.getTipo());
                escrever.writeUTF(c.getColuna());
            }
        }
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
