package dojoparticoes;


import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Cafer
 */
public class No {
    
    private final Cliente elem;
    private final DataInputStream arq;
    private final No dir;
    private final No esq;

    
    No(Cliente elem, DataInputStream arq, No dir, No esq){
        this.elem = elem;
        this.arq = arq;
        this.dir = dir;
        this.esq = esq;
    }
    public Cliente getElem() {
        return elem;
    }

    public DataInputStream getArq() {
        return arq;
    }

    public No getDir() {
        return dir;
    }

    public No getEsq() {
        return esq;
    }
    
}
