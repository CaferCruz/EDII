/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package arvore_de_vencedores;

import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.IOException;

/*
 * Classe que implementa um cliente com codigo, nome e dataNascimento
 */
public class Cliente implements Entidade {

    public int codCliente;
    public String nome;    

    /*
     * Construtor do Cliente
     */
    public Cliente(int codCliente, String nome) {
        this.codCliente = codCliente;
        this.nome = nome;        
    }

    /**
     * Salva um Cliente no arquivo out, na posição atual do cursor
     * @param out aquivo onde os dados serão gravados
     */
    public void salva(DataOutputStream out) throws IOException {
        out.writeInt(codCliente);
        out.writeUTF(nome);        
    }

    /**
     * Lê um Cliente do arquivo in na posição atual do cursor
     * e retorna uma instância de Cliente populada com os dados lidos do arquivo
     * @param in Arquivo de onde os dados serão lidos
     * @return instância de Cliente populada com os dados lidos
     */
    public static Cliente le(DataInputStream in) throws IOException {
        return new Cliente(in.readInt(), in.readUTF());
    }

    /**
     * Gera uma String com uma representação de um Cliente
     */
    @Override
    public String toString() {
        return this.codCliente + ", " + this.nome;
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
        final Cliente other = (Cliente) obj;
        if (this.codCliente != other.codCliente) {
            return false;
        }
        if ((this.nome == null) ? (other.nome != null) : !this.nome.equals(other.nome)) {
            return false;
        }        
        return true;
    }

    /*
     * Gera o hashCode para uma instância de Cliente
     * @return hashCode gerado
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.codCliente;
        hash = 71 * hash + (this.nome != null ? this.nome.hashCode() : 0);        
        return hash;
    }
}

