/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancodedados;

import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Cafer
 */
public class BancoDeDados {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Scanner tec = new Scanner(System.in);
        boolean loop = true;

        while (loop) {
            System.out.println("***MENU***");
            System.out.println("1. Adicionar");
            System.out.println("2. Remover");
            System.out.println("3. Buscar");
            System.out.println("4. Sair");
            System.out.println("**********");
            System.out.print("escolha: ");
            int menu = tec.nextInt();
            Runtime.getRuntime().exec("cls");
            switch (menu) {
                case 1:
                    System.out.println("**********");
                    System.out.print("Tabela: ");
                    String tabela = tec.next();
                    boolean loopTab = true;
                    //repetir
                    while (loopTab) {
                        System.out.println("***Campos da tabela***");
                        System.out.println("1. Inserir novo campo.");
                        System.out.println("2. Salvar e sair.");
                        System.out.print("escolha: ");
                        int resp = tec.nextInt();
                        switch (resp) {
                            case 1:
                                System.out.println("***Tipo***");
                                System.out.println("1. Inteiro");
                                System.out.println("2. String");
                                System.out.print("escolha: ");
                                int tipo = tec.nextInt();
                                //criar Campo
                                break;
                            case 2:
                                //Criar Tabela
                                loopTab = false;
                                break;
                        }
                    }
                    break;
                case 2:
                    System.out.println("Em construção");
                    break;
                case 3:
                    System.out.println("Em construção");
                    break;
                case 4:
                    loop = false;
                    break;
            }
        }
    }

}