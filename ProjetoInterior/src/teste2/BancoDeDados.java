package teste2;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Cafer
 */
public class BancoDeDados {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws Exception {
        Scanner tec = new Scanner(System.in);
        boolean loop = true;
        String nome;
        Catalogo catalogo = new Catalogo();
        catalogo.carregar();
        while (loop) {
            System.out.println();
            System.out.println("\t**************************");
            System.out.println("\t********** MENU **********");
            System.out.println("\t* 1. Adicionar Tabela.   *");
            System.out.println("\t* 2. Remover Tabela.     *");
            System.out.println("\t**************************");
            System.out.println("\t* 3. Adicionar Registro. *");
            System.out.println("\t* 4. Remover Registro.   *");
            System.out.println("\t* 5. Alterar Registro.    *");
            System.out.println("\t* 6. Buscar Registro.    *");
            System.out.println("\t**************************");
            System.out.println("\t* 7. Listar Catalogo.    *");
            System.out.println("\t* 8. Sair.               *");
            System.out.println("\t**************************");
            System.out.print("\t* escolha: ");
            int menu = tec.nextInt();
            switch (menu) {
                case 1:

                    System.out.println("\t**************************");
                    System.out.print("\t*Tabela: ");
                    tec.nextLine();
                    String nomeT = formatarNome(tec.nextLine());
                    if (catalogo.contem(nomeT)) {
                        System.out.println();
                        System.out.println("\t*********************************************");
                        System.out.println("\t* Nome existente, não é possível continuar. *");
                        System.out.println("\t**********************************************");
                    } else {
                        boolean loopTab = true;
                        List<Campo> tab = new ArrayList<>();
                        // Loop dos Campos
                        while (loopTab) {
                            System.out.println();
                            System.out.println("\t*************************");
                            System.out.println("\t********* Tabela ********");
                            System.out.println("\t* 1. Inserir campo.     *");
                            System.out.println("\t* 2. Salvar e sair.     *");
                            System.out.println("\t*************************");
                            System.out.print("\t* escolha: ");
                            int resp = tec.nextInt();
                            switch (resp) {
                                case 1:
                                    boolean isExiste = true;
                                    int tipo = 0;
                                    String nomeCampo = "";
                                    while (isExiste) {
                                        System.out.println();
                                        System.out.println("\t*************************");
                                        System.out.print("\t* Nome do Campo: ");
                                        tec.nextLine();
                                        nomeCampo = formatarNome(tec.nextLine());
                                        //TODO: verificar se tabela já existe
                                        System.out.println("\t*************************");
                                        System.out.println("\t********* Tipo **********");
                                        System.out.println("\t* 1. Inteiro            *");
                                        System.out.println("\t* 2. String             *");
                                        System.out.println("\t*************************");
                                        System.out.print("\t* escolha: ");
                                        tipo = tec.nextInt();
                                        //verificar se atributo já existe no arrayList.
                                        Campo auxC = new Campo(tipo, nomeCampo);
                                        if (tab.contains(auxC)) {
                                            System.out.println();
                                            System.out.println("\t*****************************************");
                                            System.out.println("\t* Atributo existente, insira outro nome *");
                                            System.out.println("\t*****************************************");
                                        } else {
                                            tab.add(auxC);
                                            isExiste = false;
                                        }
                                    }
                                    break;
                                case 2:
                                    if (tab.isEmpty()) {
                                        System.out.println();
                                        System.out.println("\t********************************");
                                        System.out.println("\t* Informe pelo menos um campo. *");
                                        System.out.println("\t********************************");
                                    } else {
                                        System.out.println();
                                        System.out.println("\t*************************");
                                        System.out.println("\t***** Definir Chave *****");
                                        for (int i = 1; i <= tab.size(); i++) {
                                            System.out.println("\t* " + i + ". " + tab.get(i - 1).getNomeAtributo());
                                        }
                                        System.out.println("\t*************************");
                                        System.out.print("\t* escolha: ");
                                        int ppk = tec.nextInt();
                                        //Criar a tabela.
                                        // tab.getCampos().get(pk - 1).setPK(true);
                                        Campo aux = tab.remove(ppk - 1);
                                        tab.add(0, aux);
                                        Tabela tabela = new Tabela(tab, nomeT, 0);
                                        //Salvar a tabela no catálogo
                                        catalogo.adicionar(tabela);
                                        tabela.inicializarTabela();
                                        loopTab = false;
                                    }
                                    break;
                            }
                        }
                    }
                    break;
                case 2:
                    System.out.println();
                    System.out.println("\t*************************");
                    System.out.println("\t******** Tabelas ********");
                    catalogo.listarNomeTabela();
                    System.out.println("\t*************************");
                    System.out.print("\t* escolha: ");
                    nome = catalogo.getNomeTabela(tec.nextInt());
                    if (catalogo.remover(nome)) {
                        System.out.println();
                        System.out.println("\t**************************");
                        System.out.println("\t* Removido com sucesso.  *");
                        System.out.println("\t**************************");
                        System.out.println();
                    } else {
                        System.out.println();
                        System.out.println("\t**************************");
                        System.out.println("\t* Tabela não existe.     *");
                        System.out.println("\t**************************");
                        System.out.println();
                    }
                    break;
                case 3:
                    System.out.println();
                    System.out.println("\t*************************");
                    System.out.println("\t******** Tabelas ********");
                    catalogo.listarNomeTabela();
                    System.out.println("\t*************************");
                    System.out.print("\t* escolha: ");
                    nome = catalogo.getNomeTabela(tec.nextInt());
                    if (!catalogo.contem(nome)) {
                        System.out.println();
                        System.out.println("\t**************************");
                        System.out.println("\t* Tabela inexistente.    *");
                        System.out.println("\t**************************");
                        System.out.println();
                    } else {
                        System.out.println();
                        System.out.println("\t**************************");
                        System.out.println("\t******** Dados *******");
                        Tabela tabela = catalogo.getTabela(nome);
                        List<Object> atributos = new ArrayList<>();
                        for (Campo campo : tabela.getCampos()) {
                            System.out.print("\t* " + campo + " -> ");
                            if (campo.getTipo() == Tipo.INTEGER) {
                                int v = tec.nextInt();
                                atributos.add(v);
                            } else {
                                String n = tec.next() + tec.nextLine();
                                if (n.length() < 10) {
                                    for (int i = n.length(); i < 10; i++) {
                                        n += " ";
                                    }
                                }
                                atributos.add(n);
                            }
                        }
                        tabela.addTupla(new Tupla(tabela, atributos));
                    }
                    break;
                case 4:
                    System.out.println();
                    System.out.println("\t*************************");
                    System.out.println("\t******** Tabelas ********");
                    catalogo.listarNomeTabela();
                    System.out.println("\t*************************");
                    System.out.print("\t* escolha: ");
                    nome = catalogo.getNomeTabela(tec.nextInt());
                    if (!catalogo.contem(nome)) {
                        System.out.println();
                        System.out.println("\t**************************");
                        System.out.println("\t* Tabela inexistente.    *");
                        System.out.println("\t**************************");
                        System.out.println();
                    } else {
                        System.out.println();
                        System.out.println("\t**************************");
                        System.out.println("\t********** Dados *********");
                        Tabela tabela1 = catalogo.getTabela(nome);
                        String cabecalho1 = "\t";
                        for (Campo c : tabela1.getCampos()) {
                            cabecalho1 += "| " + c.getTipo() + ", " + c.getNomeAtributo() + "| ";
                        }
                        System.out.println(cabecalho1);
                        tabela1.listarTodosDados();
                        System.out.println("\t**************************");
                        System.out.print("\t* Informe chave: ");
                        tabela1.excluirTupla(tec.nextInt());
                    }
                    break;
                case 5:
                    
                    
                    break;
                case 6:
                    System.out.println("\t**************************");
                    System.out.println("\t********** Busca *********");
                    System.out.println("\t* 1. select * from.      *");
                    System.out.println("\t* 2. Busca.              *");
                    System.out.println("\t**************************");
                    System.out.print("\t* escolha: ");
                    int esc = tec.nextInt();
                    switch (esc) {
                        case 1:
                            System.out.println();
                            System.out.println("\t*************************");
                            System.out.println("\t******** Tabelas ********");
                            catalogo.listarNomeTabela();
                            System.out.println("\t*************************");
                            System.out.print("\t* escolha: ");
                            nome = catalogo.getNomeTabela(tec.nextInt());
                            if (!catalogo.contem(nome)) {
                                System.out.println();
                                System.out.println("\t**************************");
                                System.out.println("\t* Tabela inexistente.    *");
                                System.out.println("\t**************************");
                                System.out.println();
                            } else {
                                System.out.println();
                                System.out.println("\t**************************");
                                System.out.println("\t*********** Dados **********");
                                Tabela tabela = catalogo.getTabela(nome);
                                String cabecalho = "\t";
                                for (Campo c : tabela.getCampos()) {
                                    cabecalho += "| " + c.getTipo() + ", " + c.getNomeAtributo() + "| ";
                                }
                                System.out.println(cabecalho);
                                tabela.listarTodosDados();
                            }
                            break;
                        case 2:
                            System.out.println("Em construção.");
                            break;
                    }
                    break;
                case 7:
                    System.out.println();
                    System.out.println("\t**************************");
                    System.out.println("\t******** Catalogo ********");
                    catalogo.listar();
                    System.out.println();
                    System.out.println("\t**************************");
                    break;
                case 8:
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
