/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancodedados;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

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
            System.out.println("*** MENU ***");
            System.out.println("1. Adicionar");
            System.out.println("2. Remover");
            System.out.println("3.Inserir Registro");
            System.out.println("4. Buscar Registro");
            System.out.println("5. Listar Catalogo");
            System.out.println("6. Sair");
            System.out.println("***********");
            System.out.print("escolha: ");
            int menu = tec.nextInt();
            switch (menu) {
                case 1:
                    System.out.println("***********");
                    System.out.print("Tabela: ");
                    tec.nextLine();
                    String nomeT = formatarNome(tec.nextLine());
                    if (Catalogo.buscar(nomeT)) {
                        System.out.println("Nome existente, não é possível continuar.");
                        System.out.println("***********");
                    } else {
                        Tabela tab = new Tabela(nomeT);
                        boolean loopTab = true;
                        //repetir
                        while (loopTab) {
                            System.out.println("*** Campos da tabela ***");
                            System.out.println("1. Inserir campo.");
                            System.out.println("2. Salvar e sair.");
                            System.out.println("***********");
                            System.out.print("escolha: ");
                            int resp = tec.nextInt();
                            switch (resp) {
                                case 1:
                                    boolean isExiste = true;
                                    int tipo = 0;
                                    String nomeCampo = "";
                                    while (isExiste) {
                                        System.out.print("Nome do Campo:");
                                        tec.nextLine();
                                        nomeCampo = formatarNome(tec.nextLine());
                                        System.out.println("*** Tipo ***");
                                        System.out.println("1. Inteiro");
                                        System.out.println("2. String");
                                        System.out.println("***********");
                                        System.out.print("escolha: ");
                                        tipo = tec.nextInt();
                                        //verificar se atributo já existe na tabela.
                                        isExiste = false;
                                        for (Campo c : tab.getCampos()) {
                                            if (c.getAtributo().equalsIgnoreCase(nomeCampo)) {
                                                System.out.println("Atributo existente, insira outro nome.");
                                                isExiste = true;
                                            }
                                        }
                                    }
                                    tab.addCampo(new Campo(tipo, nomeCampo));
                                    break;
                                case 2:
                                    if (tab.isCampoVazio()) {
                                        System.out.println("Informe pelo menos um campo.");
                                    } else {
                                        System.out.println("***********");
                                        System.out.println("*****Definir Chave Primaria (PK)******");
                                        for (int i = 1; i <= tab.getCampos().size(); i++) {
                                            System.out.println(i + ". " + tab.getCampos().get(i - 1).getAtributo());
                                        }
                                        System.out.println("***********");
                                        System.out.print("Escolha a chave primaria:");
                                        int pk = tec.nextInt();
                                        tab.getCampos().get(pk - 1).setPK(true);
                                        //Salvar a tabela no catálogo
                                        tab.salva();
                                        loopTab = false;
                                    }
                                    break;
                            }
                        }
                    }
                    break;
                case 2:
                    System.out.println("Em construção");
                    break;
                case 3:
                    System.out.println("Digite o nome da tabela à ser inserida");
                    String nome = tec.nextLine();
                    
                    if(!Catalogo.buscar(nome)){
                        System.out.println("Não existe esta tabela");
                    }else{
                        
                    }
                    
                    
                    
                    
                    
                    
                    break;
                case 4:
                    
                    break;
                case 5:
                    Catalogo.listar();
                    break;
                case 6:
                    loop = false;
                    break;
            }
        }
    }

    private static String formatarNome(String nTabela) {
        nTabela = nTabela.replace(" ", "_");
        return nTabela;
    }
}
