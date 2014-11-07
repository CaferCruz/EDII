/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package teste2;

import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Romulo
 */
public class Tabela {

    private final String nome;
    private List<Campo> campos;
    private List<Tupla> tuplas;
    private int posChave = 0;
    private int tamanhoRegistro;
    private String nomeArquivo;
    private EncadeamentoInterior hash;

    public Tabela(List<Campo> campos, String nome, int posChave) {
        this.campos = campos;
        this.nome = nome;
        this.posChave = posChave;
        this.tuplas = new ArrayList<>();
        nomeArquivo = nome + ".dat";
        calculaTamanhoRegistro();
    }

    public void inicializarTabela() throws IOException {
        hash = new EncadeamentoInterior(this);
        hash.criaHash(this, nomeArquivo, 100);
    }

    public void excluirTupla(int cod) throws Exception {
        if (hash.exclui(this, cod, nomeArquivo) == -1) {
            System.out.println();
            System.out.println("\t**************************");
            System.out.println("\t* Chave não existe.      *");
            System.out.println("\t**************************");
            System.out.println();
        } else {
            System.out.println();
            System.out.println("\t**************************");
            System.out.println("\t* Excluido com sucesso.  *");
            System.out.println("\t**************************");
            System.out.println();
        }
    }

    public void addTupla(Tupla tupla) throws Exception {
        try {
            hash.insere(this, tupla, getNomeArquivo());
        } catch (Exception ex) {
            throw new Exception("Erro em adicionar tupla.");
        }
    }

    public int getPosChave() {
        return posChave;
    }

    public List<Campo> getCampos() {
        return campos;
    }

    public int getTamanhoRegistro() {
        return tamanhoRegistro;
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void listarTodosDados() throws IOException {
        hash.listarTodosDados();
    }

    public void setAtributos(List<Campo> campos) {
        this.campos = campos;
    }

    public void setTabelaHash() {
        this.hash = new EncadeamentoInterior(this);
    }

    public final void calculaTamanhoRegistro() {
        tamanhoRegistro = 1 + 4; // inicializa com o 1 byte do flag e 4 bytes do ponteiro para o proximo
        for (Campo campo : campos) {
            if (campo.getTipo() == Tipo.INTEGER) {
                tamanhoRegistro = tamanhoRegistro + 4;
            } else if (campo.getTipo() == Tipo.STRING) {
                tamanhoRegistro = tamanhoRegistro + 12;
            }
        }
    }

    public String getNome() {
        return this.nome;
    }

    @Override
    public String toString() {
        String texto = "\n\tTabela: " + nome + "\n\t | ";
        for (Campo c : campos) {
            texto += c + " | ";
        }
        texto += "\n\t Chave primaria: " + campos.get(posChave);

        //  texto += "\n\t* " + "Registros" + "\n";
        //  for (Tupla t : tuplas) {
        //     texto += t + "\n\t* ";
        // }
        return texto;
    }

}
