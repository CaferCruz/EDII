
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Romulo
 */
public class Tabela {

    private final String nome;
    private String nomeArquivo;
    private List<Tupla> tuplas;
    private List<Campo> campos;

    private EncadeamentoInterior tabela = null;

    public Tabela(String nome) throws IOException {
        this.nome = nome;
        campos = new ArrayList<>();
        tuplas = new ArrayList<>();
        nomeArquivo = nome + ".dat";
    }

    public String getNomeArquivo() {
        return nomeArquivo;
    }

    public void addCampo(Campo campo) {
        campos.add(campo);
    }

    public void inicializarTabela() throws IOException{
        tabela = new EncadeamentoInterior(campos);
        tabela.criaHash(nomeArquivo);
    }

    public void setTabelaHash(){
        tabela = new EncadeamentoInterior(campos);
    }
    
    public void addTupla(String tupla) throws Exception {
        tabela.insere(new Tupla(campos, tupla), nomeArquivo);
        
    }

    public String getNome() {
        return this.nome;
    }

    public boolean isCampoVazio() {
        return this.campos.isEmpty();
    }

    public List<Campo> getCampos() {
        return campos;
    }

    @Override
    public String toString() {
        String texto = "Tabela: " + nome + "\n | ";
        for (Campo c : campos) {
            texto += c + " | ";
        }
        texto += "\n";
        for (Tupla t : tuplas) {
            texto += t + "\n";
        }
        return texto;
    }
}
