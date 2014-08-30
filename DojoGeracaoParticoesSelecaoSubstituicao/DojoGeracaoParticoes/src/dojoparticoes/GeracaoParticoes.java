package dojoparticoes;

import java.util.List;
import java.io.*;

public class GeracaoParticoes {

    /**
     * Executa o algoritmo de geração de partições por Classificação Interna
     *
     * @param nomeArquivoEntrada arquivo de entrada
     * @param nomeArquivoSaida array list contendo os nomes dos arquivos de
     * saída a serem gerados
     * @param M tamanho do array em memória para manipulação dos registros
     */
    public void executaClassificacaoInterna(String nomeArquivoEntrada, List<String> nomeArquivoSaida, int M) throws Exception {

        //TODO: Inserir aqui o código do algoritmo de geração de partições
    }

    /**
     * Executa o algoritmo de geração de partições por Seleção com Substituição
     *
     * @param nomeArquivoEntrada arquivo de entrada
     * @param nomeArquivoSaida array list contendo os nomes dos arquivos de
     * saída a serem gerados
     * @param M tamanho do array em memória para manipulação dos registros
     */
    public void executaSelecaoComSubstituicao(String nomeArquivoEntrada, List<String> nomeArquivoSaida, int M) throws Exception {

        //TODO: Inserir aqui o código do algoritmo de geração de partições
        DataInputStream dados = null;
        DataOutputStream dadosSaida = null;
        boolean[] congelados;
        boolean fimDeArquivo = false;
        try {
            congelados = new boolean[M];
            dados = new DataInputStream(new BufferedInputStream(new FileInputStream(nomeArquivoEntrada)));
            Cliente[] clientes = new Cliente[M];
            int posMax = 0;
            Cliente elem = Cliente.le(dados);
            int p = 0;
            while (elem.codCliente < Integer.MAX_VALUE || p < M){
                clientes[p++] = elem;
                elem = Cliente.le(dados);
            } 
            //Caso arquivo chegar ao fim antes do tamanho possivel de memoria
            int iniPart = 0;
            dadosSaida = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(nomeArquivoSaida.get(iniPart))));
            if(elem.codCliente == Integer.MAX_VALUE){
                fimDeArquivo = true;
                for(int i = 0; i < p; i++) {
                    clientes[i].salva(dadosSaida);
                }
            }

            //Caso o array clientes esteja completo            
            dadosSaida = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(nomeArquivoSaida.get(++iniPart))));
            while (!fimDeArquivo) {
                int pos = buscarMenorPosicaoChave(clientes, congelados, posMax);
                if (pos != -1) {
                    clientes[pos].salva(dadosSaida);
                    int ant = clientes[pos].codCliente;
                    clientes[pos] = Cliente.le(dados);
                    if (ant < clientes[pos].codCliente) {
                        congelados[pos] = false;
                    }
                } else {
                    dadosSaida.close();
                    dadosSaida = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(nomeArquivoSaida.get(++iniPart))));
                    congelados = new boolean[M];
                }
            }
        } catch (IOException e) {
            throw new IOException("Não foi possível acessar.");
        } finally {
            if (dados != null) {
                dados.close();
            }
            if (dadosSaida != null) {
                dadosSaida.close();
            }
        }

    }

    /**
     * Executa o algoritmo de geração de partições por Seleção Natural
     *
     * @param nomeArquivoEntrada arquivo de entrada
     * @param nomeArquivoSaida array list contendo os nomes dos arquivos de
     * saída a serem gerados
     * @param M tamanho do array em memória para manipulação dos registros
     * @param n tamanho do reservatório
     */
    public void executaSelecaoNatural(String nomeArquivoEntrada, List<String> nomeArquivoSaida, int M, int n) throws Exception {

        //TODO: Inserir aqui o código do algoritmo de geração de partições
    }

    private int buscarMenorPosicaoChave(Cliente[] clientes, boolean[] congelado, int posMax) {
        int menorChave = Integer.MAX_VALUE;
        int posicao = -1;
        for (int i = 0; i < posMax; i++) {
            if ((!congelado[i]) && (menorChave > clientes[i].codCliente)) {
                menorChave = clientes[i].codCliente;
                posicao = i;
            }
        }
        return posicao;
    }

}
