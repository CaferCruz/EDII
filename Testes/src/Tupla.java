/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.*;

/**
 *
 * @author Romulo
 */
public class Tupla {

    private List<Campo> campos;
    private List<Object> atributos;
    public static boolean LIBERADO = true;
    public static boolean OCUPADO = false;
    private int tamanhoRegistro;
    public int prox;
    private int chave;
    private boolean flag = Tupla.OCUPADO;

    public Tupla(List<Campo> campos, String tupla) {
        this.campos = campos;
        this.atributos = new ArrayList<>();
        String[] tuplas = tupla.split(", ");
        if (tuplas.length != campos.size()) {
            System.out.println("Erro! informe uma tupla válida");
        } else {
            for (int i = 0; i < tuplas.length; i++) {
                if (campos.get(i).getTipo() == Tipo.INTEGER) { // inteiro
                    int valor = Integer.parseInt(tuplas[i].trim());
                    atributos.add(valor);
                } else {
                    if (tuplas[i].length() < 10) {
                        for (int j = tuplas[i].length(); j < 10; j++) {
                            tuplas[i] += " ";
                        }
                    }
                    atributos.add(tuplas[i]);
                }
            }
        }
        tamanhoRegistro = calculaTamanho();
        gerarChave();
    }

    public void setChave() {
        this.gerarChave();
    }

    public int getTamanhoRegistro() {
        return tamanhoRegistro;
    }

    public Tupla(int chave, boolean flag) {
        this.campos = new ArrayList<>();
        this.atributos = new ArrayList<>();
        this.chave = chave;
        this.flag = flag;
    }

    private int calculaTamanho() {
        int tamanho = 4 + 1 + 4;
        for (Campo c : campos) {
            if (c.getTipo() == Tipo.STRING) {
                tamanho += 12;
            } else {
                tamanho += 4;
            }
        }
        return tamanho;
    }

    private Tupla(List<Object> atributos, List<Campo> campos) {
        this.atributos = atributos;
        this.campos = campos;
    }

    public void salva(RandomAccessFile out) throws IOException {
        for (int i = 0; i < atributos.size(); i++) {
            if (campos.get(i).getTipo() == Tipo.INTEGER) {
                out.writeInt((int) atributos.get(i));
            } else { // neste caso recebemos um String
                out.writeUTF((String) atributos.get(i));
            }
        }
        out.writeBoolean(this.getFlag());
    }

    public void setProx(int prox) {
        this.prox = prox;
    }

    private void gerarChave() {
        this.chave = Math.abs(campos.get(0).hashCode());
    }

    public static Tupla le(RandomAccessFile in, List<Campo> campos) throws IOException {
        List<Object> atributos = new ArrayList<>();
        for (int i = 0; i < campos.size(); i++) {
            if (campos.get(i).getTipo() == Tipo.INTEGER) {
                atributos.add(in.readInt());
            } else {
                atributos.add(in.readUTF());
            }
        }
        Tupla tupla = new Tupla(atributos, campos);
        tupla.setFlag(in.readBoolean());
        return tupla;
    }

    public List<Campo> getCampos() {
        return campos;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public boolean getFlag() {
        return flag;
    }

    public int getChave() {
        return chave;
    }

    @Override
    public String toString() {
        String texto = " ";
        for (Object atributo : atributos) {
            texto += atributo + " ";
        }
        return texto;
    }

}
