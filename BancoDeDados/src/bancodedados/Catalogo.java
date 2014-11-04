/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancodedados;

import java.io.*;
import java.util.ArrayList;

/**
 *
 * @author Cafer
 */
public class Catalogo {

    private final static String arq = "catalogo.dat";
    private final static String arqTabela = "nomeTabela.dat";

    public static void adicionar(String nome, ArrayList<Campo> campos) throws FileNotFoundException, IOException {
        DataOutputStream out = null;
        DataOutputStream outT = null;
        try {
            out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(arq, true)));
            outT = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(arqTabela, true)));
            //Armazenar nome da Tabela, nas lista de Tabela.
            outT.writeUTF(nome);
            //Fazer que esteja garantindo que seja escrito no final.
            out.writeUTF(nome);
            //busca e registra pk.
            for (Campo c : campos) {
                if (c.isPk()) {
                    out.writeUTF(c.getAtributo());
                }
            }
            //registra Campos
            for (Campo c : campos) {
                out.writeInt(c.getTipo());
                out.writeUTF(c.getAtributo());
            }
            out.writeInt(-1);
        } catch (FileNotFoundException ex) {
            throw new FileNotFoundException("Arquivo não pode ser criado.");
        } catch (IOException e) {
            throw new IOException("Um Erro ocorreu.");
        } finally {
            if (out != null) {
                out.close();
            }
            if (outT != null) {
                outT.close();
            }
        }
    }

    public static void listar() throws FileNotFoundException, IOException {
        DataInputStream in = null;
        String type;
        try {
            in = new DataInputStream(new BufferedInputStream(new FileInputStream(arq)));

            System.out.println("***Lista de Tabelas***");
            while (true) {
                System.out.println();
                System.out.println("Tabela: " + in.readUTF());
                System.out.println("PK: " + in.readUTF());

                int tipo = in.readInt();
                do {
                    if (tipo == 1) {
                        type = "Inteiro";
                    } else {
                        type = "String";
                    }

                    System.out.println(type + ": " + in.readUTF());
                    tipo = in.readInt();
                } while (tipo != -1);
            }
        } catch (FileNotFoundException ex) {
            throw new FileNotFoundException("Arquivo não pode ser encontrado.");
        } catch (EOFException eof) {
            System.out.println("***********");
        } catch (IOException e) {
            throw new IOException("Um Erro ocorreu.");
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }

    public static boolean buscar(String nomeTabela) throws FileNotFoundException, IOException {
        DataInputStream inT = null;
        try {
            inT = new DataInputStream(new BufferedInputStream(new FileInputStream(arqTabela)));
            while (true) {
                if (inT.readUTF().equalsIgnoreCase(nomeTabela)) {
                    return true;
                }
            }
        } catch (FileNotFoundException ex) {
            throw new FileNotFoundException("Arquivo não pode ser encontrado.");
        } catch (EOFException eof) {
        } finally {
            if (inT != null) {
                inT.close();
            }
        }
        return false;
    }

    

}
