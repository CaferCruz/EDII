/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dojoparticoes;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author G6
 */
public class Intercalacao {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        ArrayList<Cliente> elem = new ArrayList<Cliente>();
        elem.add(new Cliente(31, "a"));
        elem.add(new Cliente(70, "a"));
        elem.add(new Cliente(Integer.MAX_VALUE, "a"));
        criarArquivoEntrada(elem, "arq1.dat");

        elem.clear();
        elem.add(new Cliente(76, "a"));
        elem.add(new Cliente(Integer.MAX_VALUE, "a"));
        criarArquivoEntrada(elem, "arq2.dat");

        elem.clear();
        elem.add(new Cliente(41, "a"));
        elem.add(new Cliente(Integer.MAX_VALUE, "a"));
        criarArquivoEntrada(elem, "arq3.dat");

        elem.clear();
        elem.add(new Cliente(69, "a"));
        elem.add(new Cliente(Integer.MAX_VALUE, "a"));
        criarArquivoEntrada(elem, "arq4.dat");

        elem.clear();
        elem.add(new Cliente(13, "a"));
        elem.add(new Cliente(40, "a"));
        elem.add(new Cliente(Integer.MAX_VALUE, "a"));
        criarArquivoEntrada(elem, "arq5.dat");

        elem.clear();
        elem.add(new Cliente(2, "a"));
        elem.add(new Cliente(20, "a"));
        elem.add(new Cliente(51, "a"));
        elem.add(new Cliente(Integer.MAX_VALUE, "a"));
        criarArquivoEntrada(elem, "arq6.dat");

        elem.clear();
        elem.add(new Cliente(6, "a"));
        elem.add(new Cliente(10, "a"));
        elem.add(new Cliente(15, "a"));
        elem.add(new Cliente(60, "a"));
        elem.add(new Cliente(Integer.MAX_VALUE, "a"));
        criarArquivoEntrada(elem, "arq7.dat");

        ArrayList<String> arqEnt = new ArrayList<String>();
        arqEnt.add("arq1.dat");
        arqEnt.add("arq2.dat");
        arqEnt.add("arq3.dat");
        arqEnt.add("arq4.dat");
        arqEnt.add("arq5.dat");
        arqEnt.add("arq6.dat");
        arqEnt.add("arq7.dat");

        ArrayList<No> folhas = criarFolha(arqEnt);

        ArrayList<No> saida = new ArrayList<No>();

        ArvoreDosVencedores vencedor = new ArvoreDosVencedores(folhas);
        DataOutputStream arqAux = null;
        while (vencedor.getVencedor().getElem().codCliente != Integer.MAX_VALUE) {
            //verificar endereco no arq do vencedor
            DataInputStream nomearq = vencedor.getVencedor().getArq();
            //salvar vencedor em saida.
            saida.add(vencedor.getVencedor());
            //Criar Cliente
            Cliente novo = Cliente.le(nomearq);
            //Criar No e add no array
            int ind = 0;
            for (No n : folhas) {
                if (n.getElem().codCliente == vencedor.getVencedor().getElem().codCliente) {
                    folhas.set(ind, new No(novo, nomearq, null, null));
                }
                ind++;
            }
            //Fecha arquivo se for fim de arquivo.
            if (novo.codCliente == Integer.MAX_VALUE) {
                nomearq.close();
            }
            //ordenar array
            vencedor.construir(folhas);
        }
        System.out.println("***Arquivo saida***");
        for(No s: saida){
            System.out.println(s.getElem().codCliente);
        }
    }

    private static void criarArquivoEntrada(ArrayList<Cliente> elem, String arq) throws FileNotFoundException, IOException {
        DataOutputStream dados = null;
        try {
            dados = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(arq)));
            for (Cliente c : elem) {
                dados.writeInt(c.codCliente);
                dados.writeUTF(c.nome);
            }
        } catch (FileNotFoundException ex) {
            throw new FileNotFoundException("Arquivo não encontrado.");
        } catch (IOException e) {
            throw new IOException("Algo inesperado aconteceu");
        } finally {
            if (dados != null) {
                dados.close();
            }
        }
    }

    private static ArrayList<No> criarFolha(ArrayList<String> arq) throws FileNotFoundException, IOException {
        DataInputStream dados = null;
        ArrayList<No> folhas = new ArrayList<No>();

        try {
            for (String a : arq) {
                dados = new DataInputStream(new BufferedInputStream(new FileInputStream(a)));
                folhas.add(new No(Cliente.le(dados), dados, null, null));
                //dados.close();
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("Arquivo não encontrado.");
        } catch (IOException ex) {
            throw new IOException("Algo inesperado aconteceu");
        }
        return folhas;
    }
}
