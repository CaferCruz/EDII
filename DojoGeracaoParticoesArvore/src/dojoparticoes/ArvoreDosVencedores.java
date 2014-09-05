/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dojoparticoes;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Cafer
 */
public class ArvoreDosVencedores {

    private No vencedor = null;

    public ArvoreDosVencedores(ArrayList<No> folhas) {
        construir(folhas);
    }

    public No getVencedor() {
        return this.vencedor;
    }

    public void removerVencedor() throws IOException {
        No topo = this.vencedor;
        DataInputStream nomearq = this.vencedor.getArq();
        Cliente novo = Cliente.le(nomearq);
        if (novo.codCliente == Integer.MAX_VALUE) {
            nomearq.close();
        }
        int elemSub = this.vencedor.getElem().codCliente;
        No sub = new No(novo, nomearq, topo.getDir(), topo.getEsq());
        //this.vencedor = sub;
        No aux = this.vencedor;
        while (topo.getDir() != null || topo.getEsq() != null) {
            //sub = new No(novo, nomearq, topo.getDir(), topo.getEsq());
            topo = new No(novo, nomearq, topo.getDir(), topo.getEsq());
            if (topo.getDir().getElem().codCliente == elemSub) {
                topo = topo.getDir();
                topo = new No(novo, nomearq, topo.getDir(), topo.getEsq());
            } else if (topo.getEsq().getElem().codCliente == elemSub) {
                topo = topo.getEsq();
                topo = new No(novo, nomearq, topo.getDir(), topo.getEsq());
            }

            if (aux.getDir().getElem().codCliente < aux.getEsq().getElem().codCliente) {
//                aux = new No(aux.getDir(), aux., aux, aux);
            } else {
               // this.vencedor = 
            }
        }

    }

    public final void construir(ArrayList<No> folhas) {
        if (folhas == null) {
            throw new IllegalArgumentException("Não existem folhas.");
        }
        ArrayList<No> raiz = folhas;
        ArrayList<No> aux;
        do {
            aux = new ArrayList<No>();
            if (raiz.size() % 2 == 0) {
                for (int i = 0; i < raiz.size(); i = i + 2) {
                    if (raiz.get(i).getElem().codCliente < raiz.get(i + 1).getElem().codCliente) {
                        aux.add(new No(raiz.get(i).getElem(), raiz.get(i).getArq(), raiz.get(i + 1), raiz.get(i)));
                    } else {
                        aux.add(new No(raiz.get(i + 1).getElem(), raiz.get(i + 1).getArq(), raiz.get(i + 1), raiz.get(i)));
                    }
                }
            } else {
                for (int i = 0; i <= (raiz.size() / 2 + 1); i = i + 2) {
                    if (raiz.get(i).getElem().codCliente < raiz.get(i + 1).getElem().codCliente) {
                        aux.add(new No(raiz.get(i).getElem(), raiz.get(i).getArq(), raiz.get(i + 1), raiz.get(i)));
                    } else {
                        aux.add(new No(raiz.get(i + 1).getElem(), raiz.get(i + 1).getArq(), raiz.get(i + 1), folhas.get(i)));
                    }
                }
                aux.add(new No(raiz.get(raiz.size() - 1).getElem(), raiz.get(raiz.size() - 1).getArq(), raiz.get(raiz.size() - 1), raiz.get(raiz.size() - 1)));
            }
            raiz = aux;
        } while (raiz.size() != 1);
        this.vencedor = raiz.get(0);
    }
}
