/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package arvore_de_vencedores;

import java.io.*;
import java.util.*;

/**
 *
 * @author Cafer
 */
public class ArvoreDeVencedores {

    private void manipularFolhas(Stack<No> pilhaDeNos, Stack<No> pilhaAux) {
        No vencedor;
        No esq = null, dir = null;
        while(!pilhaDeNos.empty()) {
            if(!pilhaDeNos.empty()){
                esq = pilhaDeNos.pop();
            }
            else {
                esq = null;
            }
            if(!pilhaDeNos.empty()) {
                dir = pilhaDeNos.pop();                
            }
            else {
                dir = null;
            }
            if(esq != null && dir != null) {
                if(esq.vencedor > dir.vencedor) {
                    vencedor = new No(dir.vencedor, dir.codVencedor, esq, dir);
                    pilhaAux.push(vencedor);
                }
                else {
                    vencedor = new No(esq.vencedor, esq.codVencedor, esq, dir);
                    pilhaAux.push(vencedor);
                }                         
            }

        }
    }

    private boolean condicao(Stack<No> pilhaDeNos, Stack<No> pilhaAux) {
        return (pilhaDeNos.empty() && (pilhaAux.capacity() == 1)) || ((pilhaDeNos.capacity() == 1) && pilhaAux.empty());
    }
    
    private class No {
        int vencedor;
        int codVencedor;
        No esquerda;
        No direita;
        
        public No(int vencedor, int codVencedor, No esquerda, No direita) {
            this.vencedor = vencedor;
            this.codVencedor = codVencedor;
            this.esquerda = esquerda;
            this.direita = direita;
        }
    }
    
    private No raiz;
        
    public ArvoreDeVencedores(List<String> nomeArquivos) {
        executar(nomeArquivos);
        
        System.out.println();
    }
    
    public void executar(List<String> nomeArquivos) {
        int tamanho = nomeArquivos.size();
        DataInputStream[] in = new DataInputStream[tamanho];
        No no = null;
        Cliente[] c = new Cliente[tamanho];
        Stack<No> pilhaDeNos = new Stack<>();        
        try {
            for(int i = 0; i < tamanho; i++) {
                in[i] = new DataInputStream(new BufferedInputStream(new FileInputStream(nomeArquivos.get(i))));
                c[i] = Cliente.le(in[i]);
                if(c[i].codCliente != Integer.MAX_VALUE) {
                    no = new No(c[i].codCliente, i, null, null);
                    pilhaDeNos.push(no);                    
                }
            }
        } catch(IOException e) {
            //
        }
        this.raiz = gerarRaiz(pilhaDeNos);
        
        System.out.println(c[raiz.codVencedor]);
        
    }
    
    public No gerarRaiz(Stack<No> pilhaDeNos) {
        Stack<No> pilhaAux = new Stack<>();
        while(!condicao(pilhaDeNos, pilhaAux)) {
            manipularFolhas(pilhaDeNos, pilhaAux);  
            if(!condicao(pilhaDeNos, pilhaAux)) {
                manipularFolhas(pilhaAux, pilhaDeNos);
            }
        }
        
        if(pilhaDeNos.empty()) {
            return pilhaAux.pop();
        }
        else {
            return pilhaDeNos.pop();
        }        
    }
    
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
        
        new ArvoreDeVencedores(arqEnt);
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
}
