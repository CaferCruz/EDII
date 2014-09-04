/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dojointercalacao;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import sun.misc.Queue;

/**
 * Executa o algoritmo Intercalação Ótima
 *
 * @param nomeParticoes array com os nomes dos arquivos que contêm as partições
 * de entrada
 * @param nomeArquivoSaida nome do arquivo de saída resultante da execução do
 * algoritmo
 */
public class IntercalacaoOtima {

    public void executa(List<String> nomeParticoes, String nomeArquivoSaida) throws Exception {
        while(!nomeParticoes.isEmpty()){
        List<String> candidatos = new ArrayList<String>();
            if (nomeParticoes.size() == 1) {
                nomeArquivoSaida = nomeParticoes.remove(0);
            }else if (nomeParticoes.size() == 2) {
                candidatos.add(nomeParticoes.remove(0));
                candidatos.add(nomeParticoes.remove(1));
                intercalacao(candidatos);
                
            }
            
            
            
        }
        
        //abrir arquivo
        //testar se arquivo vazio
    }
}
