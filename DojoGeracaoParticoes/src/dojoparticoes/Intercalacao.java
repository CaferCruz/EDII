/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dojoparticoes;

import java.util.ArrayList;

/**
 *
 * @author G6
 */
public class Intercalacao {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ArrayList<No> folhas = new ArrayList<No>();
        Cliente c1 = new Cliente(1, "Cafer");
        Cliente c2 = new Cliente(0, "Carrara");
        Cliente c3 = new Cliente(-3, "Ana Paula S2");
        Cliente c4 = new Cliente(-1, "S2 Ana Paula S2");
        Cliente c5 = new Cliente(5, "Ana Paula S2");
        Cliente c6 = new Cliente(6, "Ana Paula S2");
        folhas.add(new No(c1,null,null,null));
        folhas.add(new No(c2,null,null,null));
        folhas.add(new No(c3,null,null,null));
        folhas.add(new No(c4,null,null,null));
        ArvoreDosVencedores vencedor = new ArvoreDosVencedores(folhas);
        System.out.println("topo:" + vencedor.getVencedor().getElem().codCliente);
    }
}
