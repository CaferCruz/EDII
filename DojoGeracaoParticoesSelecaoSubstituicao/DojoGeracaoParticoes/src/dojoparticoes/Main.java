/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dojoparticoes;

import java.util.ArrayList;

/**
 *
 * @author Cafer
 */
public class Main {
    public static void main(String[] args) throws Exception {
        GeracaoParticoes g = new GeracaoParticoes();
        ArrayList<String> arqSaida = new ArrayList<String>();
        arqSaida.add("p1.dat");
        arqSaida.add("p2.dat");
        arqSaida.add("p3.dat");
        arqSaida.add("p4.dat");
        g.executaSelecaoComSubstituicao("entrada.dat", arqSaida, 5);
    }
}
