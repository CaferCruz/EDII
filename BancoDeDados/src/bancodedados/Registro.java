/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bancodedados;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 *
 * @author WALMIR
 */
public class Registro  {
    public static boolean LIBERADO = true;
    public static boolean OCUPADO = false;
    public int tamanhoRegistro;
    
   
    //nome terá sempre tamanho 10 caracteres
    public ArrayList<TipoData> datas;
    public int prox;
    public boolean flag;
    

    /*
     * Construtor do Cliente
     */
    public Registro(ArrayList<TipoData> datas, int prox, boolean flag) {
        
        this.prox = prox;
        this.flag = flag;
        
        this.datas = datas;
        
    }

    /**
     * Salva um Cliente no arquivo out, na posição atual do cursor
     * @param out aquivo onde os dados serão gravados
     */
    public void salva(RandomAccessFile out) throws IOException {
        
        for (int i = 0; i < datas.size(); i++) {
            
            if(datas.get(i).tipo==1){
                
                out.writeInt(datas.get(i).dataI);
            }
            if(datas.get(i).tipo==2){
                out.writeUTF(datas.get(i).dataS);
            }
            
        }
        
        
        
        out.writeBoolean(flag);
    }

    /**
     * Lê um Cliente do arquivo in na posição atual do cursor
     * e retorna uma instância de Cliente populada com os dados lidos do arquivo
     * @param in Arquivo de onde os dados serão lidos
     * @return instância de Cliente populada com os dados lidos
     */
    /*public Registro le(RandomAccessFile in, ArrayList<Campo> campos) throws IOException {
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

    /**
     * Gera uma String com uma representação de um Cliente
     */
    public String toString() {
        String s;
        if (this.flag == Registro.LIBERADO) {
            s = "LIBERADO";
        } else {
            s = "OCUPADO";
        }
        String saida ="";
        for (int i = 0; i < datas.size(); i++) {
            saida.concat(this.datas.get(i).toString()+",");
            
        }
        
        return saida;
    }

    /*
     * Compara o cliente atual com outro cliente
     * retorna true se os valores dos atributos de ambos os clientes forem iguais,
     * e falso caso contrário
     * @param obj Cliente a ser comparado
     * @return True se clientes forem iguais, False caso contrário
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Registro other = (Registro) obj;
        for (int i = 0; i < datas.size(); i++) {
            if(!datas.get(i).equals(other.datas.get(i))){
                return false;
            }
            
        }
        
        
        if (this.prox != other.prox) {
            return false;
        }
        if (this.flag != other.flag) {
            return false;
        }
        return true;
    }

    /*
     * Gera o hashCode para uma instância de Cliente
     * @return hashCode gerado
     */
    /*public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.codCliente;
        hash = 71 * hash + (this.nome != null ? this.nome.hashCode() : 0);
        hash = 71 * hash + this.prox;
        return hash;
    }*/
}

