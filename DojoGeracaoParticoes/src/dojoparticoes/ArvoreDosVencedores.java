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
public class ArvoreDosVencedores {

    private No vencedor = null;

    public ArvoreDosVencedores(ArrayList<No> folhas) {
        criar(folhas);
    }

    public ArvoreDosVencedores ordenar(ArvoreDosVencedores arvore) {
        return null;
    }

    public No getVencedor() {
        return this.vencedor;
    }

    public void adicionar(No elem) {

    }

    public void remover(No elem) {

    }

    private void criar(ArrayList<No> folhas) {
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
                for (int i = 0; i < raiz.size()/2; i = i + 2) {
                    if (raiz.get(i).getElem().codCliente < raiz.get(i + 1).getElem().codCliente) {
                        aux.add(new No(raiz.get(i).getElem(), raiz.get(i).getArq(), raiz.get(i + 1), raiz.get(i)));
                    } else {
                        aux.add(new No(raiz.get(i + 1).getElem(), raiz.get(i + 1).getArq(), raiz.get(i + 1), folhas.get(i)));
                    }
                }
                aux.add(raiz.get(raiz.size() - 1));
            }
            raiz = aux;
        } while (raiz.size() != 1);
        this.vencedor = raiz.get(0);
    }
}
