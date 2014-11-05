/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancodedados;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 *
 * @author Cafer
 */
public class Tabela {

    private String nomeTabela = "";
    private final ArrayList<Campo> campos;
    private int tamRegistro;
    private Campo pk = null;

    public Tabela(String nomeTabela) throws IOException {
        this.nomeTabela = nomeTabela;
        campos = new ArrayList<>();
    }

    public void addCampo(Campo c) {
        this.campos.add(c);
        if (c.getTipo() == 1) {
            tamRegistro = tamRegistro + 4;
        }
        if (c.getTipo() == 2) {
            tamRegistro = tamRegistro + 12;
        }
    }

    public String getNome() {
        return this.nomeTabela;
    }

    public boolean isCampoVazio() {
        return this.campos.isEmpty();
    }

    public ArrayList<Campo> getCampos() {
        return campos;
    }

    public void salva() throws FileNotFoundException, IOException {
        setPK();
        //Salvar no catálogo
        Catalogo.adicionar(this.nomeTabela, this.campos);
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
    }

    @Override
    public String toString() {
        
        String texto = "\n"+"Tabela: " + this.nomeTabela + "\n" + "Chave Primária: " + campos.get(0).toSring();
        for (Campo c : campos) {
            texto += "\n" + c.toSring();

        }

//        Iterator it = campos.iterator();
//        while (it.hasNext()) {
//            texto += "\n" + it.next();
//        }
        return texto;
    }

    public void setPK() {
        pk = campos.get(0);
        campos.get(0).setPK(true);
    }

//    public int insere(int codCli, String nomeCli, String nomeArquivoHash) throws Exception {
//        //TODO: Inserir aqui o código do algoritmo de inserção
//        Result resultado = busca(codCli, nomeArquivoHash); 
//        if(resultado.a == 1) return -1; // o cliente com essa chave já está na tabela 
//        
//        int endereco = resultado.end;
//        int i = 0, j , m;
//        
//        RandomAccessFile arquivo = null;
//        try {
//            arquivo = new RandomAccessFile(new File(nomeArquivoHash), "rw");
//            
//            if(endereco != NULO) {
//                j = endereco;
//            }
//            else {
//                j = hash(codCli);
//                m = (int) arquivo.length();
//                
//                while(i < m) {
//                    arquivo.seek(j*Cliente.tamanhoRegistro);
//                    Cliente cliente = Cliente.le(arquivo);
//                    if(cliente.flag == Cliente.OCUPADO) {
//                        j = (j + 1) % m;
//                        i = i + 1;
//                    }
//                    else {
//                        i = m + 2;
//                    }
//                }
//                if(i == m + 1) {
//                    return -1;
//                }
//                arquivo.seek(hash(codCli)*Cliente.tamanhoRegistro);
//                Cliente cliente = Cliente.le(arquivo);
//                int temp = cliente.prox;
//                
//                arquivo.seek(hash(codCli)*Cliente.tamanhoRegistro);
//                cliente = Cliente.le(arquivo);
//                cliente.prox = j;
//                arquivo.seek(hash(codCli)*Cliente.tamanhoRegistro);
//                cliente.salva(arquivo);
//                
//                arquivo.seek(j*Cliente.tamanhoRegistro);
//                cliente = Cliente.le(arquivo);
//                cliente.prox = temp;
//                arquivo.seek(j*Cliente.tamanhoRegistro);
//                cliente.salva(arquivo);                
//                
//            }
//            arquivo.seek(j*Cliente.tamanhoRegistro);
//            Cliente cliente = Cliente.le(arquivo);
//            cliente.codCliente = codCli;
//            cliente.nome = nomeCli;
//            cliente.flag = Cliente.OCUPADO;
//            arquivo.seek(j*Cliente.tamanhoRegistro);
//            cliente.salva(arquivo);
//            
//            endereco = j;
//        } catch(IOException e) {
//            // tratar exceção
//        } finally {
//            if(arquivo != null) {
//                arquivo.close();
//            }
//        }       
//        
//        return endereco;
//    }
}
