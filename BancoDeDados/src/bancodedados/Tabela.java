/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancodedados;

import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author Cafer
 */
public class Tabela {

    private String nomeTabela;
    private ArrayList<Campo> campos;

    public Tabela(String nomeTabela) {
        this.nomeTabela = nomeTabela;
        campos = new ArrayList<>();
    }

    public void addCampo(Campo c) {
        this.campos.add(c);
    }

    public void salva() throws FileNotFoundException, IOException {
        //Salvar no catálogo

        //Criação do arquivo da tabela
        DataOutputStream out = null;
        try {
            out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(new File(nomeTabela.concat(".dat")))));

        } catch (FileNotFoundException ex) {
            throw new FileNotFoundException("Arquivo não encontrado.");
        } finally {
            if (out != null) {
                out.close();
            }
            
        }
        /********/
        
    }

}
