/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package teste2;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;

/**
 *
 * @author Romulo
 */
public class Tupla {
    
    public static boolean LIBERADO = true;
    public static boolean OCUPADO = false;
    
    private int prox;
    private List<Object> atributos;
    private boolean flag;
    
    private final Tabela tabela;
    
    //------------------- Construtores --------------------------------------
    // Criar no encadeamento interior
    public Tupla(Tabela tabela, List<Object> atributos, int prox, boolean flag) {
        this.atributos = atributos;
        this.tabela = tabela;
        this.prox = prox;
        this.flag = flag;
    }
    //O método ler tupla arquivo usa.
    public Tupla(Tabela tabela) {
        this.atributos = new ArrayList<>();
        this.tabela = tabela;
    }
    // o usuário cria a tupla
    public Tupla(Tabela tabela, List<Object> atributos) {
        this.atributos = atributos;
        this.tabela = tabela;
    }
    
    //------------------- Metodos --------------------------------------------
    
    public void salva(RandomAccessFile out) throws IOException {
        int k = 0;
        for(Campo campo: tabela.getCampos()) {
            if(campo.getTipo() == Tipo.INTEGER) {
                out.writeInt((int) atributos.get(k));
            }
            else if(campo.getTipo() == Tipo.STRING){ // neste caso recebemos um String
                out.writeUTF((String) atributos.get(k));
            }
            k++;
        }
        out.writeInt(prox);
        out.writeBoolean(flag);
    }
    
    public static Tupla le(RandomAccessFile in, Tabela tabela) throws IOException {
        Tupla tupla = new Tupla(tabela);
        for (Campo campo : tabela.getCampos()) {
            if (campo.getTipo() == Tipo.INTEGER) {
                int valor = in.readInt();
                tupla.atributos.add(valor);
            } 
            else {
                String nome = in.readUTF();
                tupla.atributos.add(nome);   
            }													
        }
        tupla.prox = in.readInt();
        tupla.flag = in.readBoolean();
        return tupla;
    }
    
    // -------------- GETTERS E SETERS ---------------------------------------
    public int getProx() {
        return prox;
    }

    public boolean isFlag() {
        return flag;
    }
    
    public void setFlag(boolean flag) {
        this.flag = flag;
    }
    
    public void setProx(int prox) {
        this.prox = prox;
    }
    
    public List<Object> getAtributos() {
        return atributos;
    }
    
    public void setAtributos(List<Object> atributos) {
        this.atributos = atributos;
    }
        
    public int getChave() {
        if(tabela.getCampos().get(tabela.getPosChave()).getTipo() == Tipo.INTEGER) {
            int valor = (int)atributos.get(tabela.getPosChave());
            return Math.abs(valor);
        }
        else {
            String valor = (String)atributos.get(tabela.getPosChave());
            return Math.abs(valor.hashCode());
        }        
    }
    
    // ----------------------- to String -------------------------------------
    @Override
    public String toString() {
        String texto = "\t* ";
        int k = 0;
        for (Campo campo: tabela.getCampos()) {
            if(campo.getTipo() == Tipo.INTEGER) {
                texto += (int)atributos.get(k) + " ";                
            }
            else {
                texto += (String) atributos.get(k) + " ";
            }
            k++;
        }
//        String s;
//        if(flag) {
//            s = "LIVRE";
//        }
//        else {
//            s = "OCUPADO";
//        }
//        texto += " " + prox + " " + s;
        return texto;
    }
    
}
