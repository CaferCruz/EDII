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

    public static void adicionar(String nome, ArrayList<Campo> campos) throws FileNotFoundException, IOException {
        DataOutputStream out = null;
        try {
            out = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(arq, true)));
            //Fazer que esteja garantindo que seja escrito no final.
            out.writeUTF(nome);
            for (Campo c : campos) {
                out.writeInt(c.getTipo());
                out.writeUTF(c.getColuna());
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
        }
    }

    public static void listar() throws FileNotFoundException, IOException {
        DataInputStream in = null;
        String type;
        try {
            in = new DataInputStream(new BufferedInputStream(new FileInputStream(arq)));
            //Fazer que esteja garantindo que seja escrito no final.
            System.out.println("***Lista de Tabelas***");
            while (true) {
                System.out.println("Tabela: "+in.readUTF());
                int tipo = in.readInt();
                if (tipo == 1) {
                     type = "Inteiro"; 
                }else{
                     type = "String";
                }
                do {
                    System.out.println(type + ": " + in.readUTF());
                    tipo = in.readInt();
                } while (tipo != -1);
            }
        } catch (FileNotFoundException ex) {
            throw new FileNotFoundException("Arquivo não pode ser criado.");
        } catch(EOFException eof){
            System.out.println("***********");
        }catch (IOException e) {
            throw new IOException("Um Erro ocorreu.");
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }
}
