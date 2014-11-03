/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancodedados;


import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Cafer
 */
public class Tabela {

    private final String nomeTabela;
    private final ArrayList<Campo> campos;
    private int tamRegistro;

    public Tabela(String nomeTabela) throws IOException {
        this.nomeTabela = nomeTabela;
        campos = new ArrayList<>();
    }

    public void addCampo(Campo c) {
        this.campos.add(c);
        if(c.getTipo()==1){
            tamRegistro = tamRegistro+4;
        }
        if(c.getTipo()==2){
            tamRegistro = tamRegistro+12;
        }
    }

    public boolean isCampoVazio() {
        return this.campos.isEmpty();
    }

    public ArrayList<Campo> getCampos() {
        return campos;
    }
    
    public void salva() throws FileNotFoundException, IOException {
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
    
    public void insereRegistro(){
        ArrayList<TipoData> datas = new ArrayList();
        Scanner tec = new Scanner(System.in);
        
        for (int i = 0; i < campos.size(); i++) {
            if(campos.get(i).getTipo()==1){
                TipoData  nova = new TipoData(tec.nextInt());
                datas.add(nova);
            }
            if(campos.get(i).getTipo()==2){
                TipoData  nova = new TipoData(tec.next());
                datas.add(nova);
            }
            
        }
    }
    
    
    public Registro le(RandomAccessFile in) throws IOException {
        ArrayList<TipoData> datas = new ArrayList();
        for (int i = 0; i < datas.size(); i++) {
            if(campos.get(i).getTipo()==1){
                TipoData nova = new TipoData(in.readInt());
                datas.add(nova);
            }
            if(campos.get(i).getTipo()==2){
                TipoData nova = new TipoData(in.readUTF());
                datas.add(nova);
            }
        }
        return new Registro (datas,in.readInt(),in.readBoolean());
    }
    
    public void criaHash(String nomeArquivoHash, int tam) throws IOException {
        //TODO: criar a tabela hash
        RandomAccessFile arquivo = null;        
        try {
            arquivo = new RandomAccessFile(new File(nomeArquivoHash),"rw");
            ArrayList<TipoData> datasVazias =new ArrayList();
            for (int j = 0; j < this.campos.size(); j++) {
                TipoData nova;
                    if(campos.get(j).getTipo()==1){
                        nova = new TipoData(0);
                    }
                    if(campos.get(j).getTipo()==2){
                        nova = new TipoData("            ");
                    }
                    
            }
            for(int i = 0; i < tam; i++) { 
                new Registro(datasVazias,i, Registro.LIBERADO).salva(arquivo);
            }
        
        } catch(IOException e) {
            //
        } finally {
            if(arquivo != null) {
                arquivo.close();
            }
        }
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
    
    
    

