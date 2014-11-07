/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package teste2;

import java.io.*;
import java.util.*;

public class EncadeamentoInterior {

    public static int NULO = -1;
    private Tabela tabela = null;

    /**
     * Cria uma tabela hash vazia de tamanho tam, e salva no arquivo
     * nomeArquivoHash Compartimento que não tem lista encadeada associada deve
     * ter registro com chave de Cliente igual a -1 Quando o ponteiro para
     * próximo for null, ele deve ser igual ao endereço do compartimento
     *
     * @param tabela
     * @param nomeArquivoHash nome do arquivo hash a ser criado
     * @param tam tamanho da tabela hash a ser criada
     * @throws java.io.IOException
     */
    public EncadeamentoInterior(Tabela tabela) {
        this.tabela = tabela;
    }

    public void criaHash(Tabela tabela, String nomeArquivoHash, int tam) throws IOException {
        //TODO: criar a tabela hash
        RandomAccessFile arquivo = null;
        List<Object> atributos = new ArrayList<>();
        try {
            arquivo = new RandomAccessFile(new File(nomeArquivoHash), "rw");

            for (Campo campo : tabela.getCampos()) {
                if (campo.getTipo() == Tipo.INTEGER) {
                    atributos.add(-1);
                } else if (campo.getTipo() == Tipo.STRING) {
                    atributos.add("          ");
                }
            }

            for (int i = 0; i < tam; i++) {
                new Tupla(tabela, atributos, i, Tupla.LIBERADO).salva(arquivo);
            }
        } catch (IOException e) {
            //
        } finally {
            if (arquivo != null) {
                arquivo.close();
            }
        }
    }

    /**
     * Executa busca em Arquivos por Encadeamento Interior (Hash) Assumir que
     * ponteiro para próximo nó é igual ao endereço do compartimento quando não
     * houver próximo nó
     *
     * @param tabela
     * @param codCli: chave do cliente que está sendo buscado
     * @param nomeArquivoHash nome do arquivo que contém a tabela Hash
     * @return Result contendo a = 1 se registro foi encontrado, e end igual ao
     * endereco onde o cliente foi encontrado ou a = 2 se o registro não foi
     * encontrado, e end igual ao primeiro endereço livre encontrado na lista
     * encadeada, ou -1 se não encontrou endereço livre
     * @throws java.lang.Exception
     */
    public Result busca(Tabela tabela, int codCli, String nomeArquivoHash) throws Exception {
        //TODO: Inserir aqui o código do algoritmo    
        RandomAccessFile arquivo = null;
        int a = 0, j = NULO;
        int endereco = hash(codCli);

        try {
            arquivo = new RandomAccessFile(new File(nomeArquivoHash), "rw");
            while (a == 0) {
                arquivo.seek(endereco * tabela.getTamanhoRegistro());
                Tupla tupla = Tupla.le(arquivo, tabela);
                if (tupla.isFlag() == Tupla.LIBERADO) {
                    j = endereco;
                }
                if ((tupla.getChave() == codCli) && (tupla.isFlag() == Tupla.OCUPADO)) {
                    a = 1; // chave encontrada
                } else {
                    if (tupla.getProx() == endereco) {
                        a = 2; // chave não encontrada
                        endereco = j;
                    } else {
                        endereco = tupla.getProx();
                    }
                }
            }
        } catch (IOException e) {
            throw new IOException("Erro ao buscar.");
        } finally {
            if (arquivo != null) {
                arquivo.close();
            }
        }

        return (new Result(a, endereco));
    }

    /**
     * Executa inserção em Arquivos por Encadeamento Exterior (Hash)
     *
     * @param tabela
     * @param tupla
     * @param nomeArquivoHash nome do arquivo que contém a tabela Hash
     * @return endereço onde o cliente foi inserido, -1 se não conseguiu inserir
     * (inclusive em caso de overflow)
     * @throws java.lang.Exception
     */
    public int insere(Tabela tabela, Tupla tupla, String nomeArquivoHash) throws Exception {
        //TODO: Inserir aqui o código do algoritmo de inserção
        Result resultado = busca(tabela, tupla.getChave(), nomeArquivoHash);
        if (resultado.a == 1) {
            return -1; // o cliente com essa chave já está na tabela 
        }
        int endereco = resultado.end;
        int i = 0, j, m;

        RandomAccessFile arquivo = null;
        try {
            arquivo = new RandomAccessFile(new File(nomeArquivoHash), "rw");

            if (endereco != NULO) {
                j = endereco;
            } else {
                j = hash(tupla.getChave());
                m = (int) arquivo.length();

                while (i < m) {
                    arquivo.seek(j * tabela.getTamanhoRegistro());
                    Tupla tuplaAux = Tupla.le(arquivo, tabela);
                    if (tuplaAux.isFlag() == Tupla.OCUPADO) {
                        j = (j + 1) % m;
                        i = i + 1;
                    } else {
                        i = m + 2;
                    }
                }
                if (i == m + 1) {
                    return -1;
                }
                arquivo.seek(hash(tupla.getChave()) * tabela.getTamanhoRegistro());
                Tupla tuplaAux = Tupla.le(arquivo, tabela);
                int temp = tuplaAux.getProx();

                arquivo.seek(hash(tupla.getChave()) * tabela.getTamanhoRegistro());
                tuplaAux = Tupla.le(arquivo, tabela);
                tupla.setProx(j); //tuplaAux.prox = j;
                arquivo.seek(hash(tupla.getChave()) * tabela.getTamanhoRegistro());
                tuplaAux.salva(arquivo);

                arquivo.seek(j * tabela.getTamanhoRegistro());
                tuplaAux = Tupla.le(arquivo, tabela);
                tupla.setProx(j);//tuplaAux.prox = j;
                arquivo.seek(j * tabela.getTamanhoRegistro());
                tuplaAux.salva(arquivo);

            }
            arquivo.seek(j * tabela.getTamanhoRegistro());
            Tupla tuplaAux = Tupla.le(arquivo, tabela);
            tuplaAux.setAtributos(tupla.getAtributos());
            tuplaAux.setFlag(Tupla.OCUPADO);
            arquivo.seek(j * tabela.getTamanhoRegistro());
            tuplaAux.salva(arquivo);

            endereco = j;
        } catch (IOException e) {
            throw new IOException("Erro ao inserir.");
        } finally {
            if (arquivo != null) {
                arquivo.close();
            }
        }
        return endereco;
    }

    /**
     * Executa exclusão em Arquivos por Encadeamento Exterior (Hash)
     *
     * @param tabela
     * @param codCli
     * @param nomeArquivoHash nome do arquivo que contém a tabela Hash
     * @return endereço do cliente que foi excluído, -1 se cliente não existe
     * @throws java.lang.Exception
     */
    public int exclui(Tabela tabela, int codCli, String nomeArquivoHash) throws Exception {
        //TODO: Inserir aqui o código do algoritmo de remoção

        Result resultado = busca(tabela, codCli, nomeArquivoHash);
        if (resultado.a == 2) {
            return -1; // neste caso não achou o cliente para excluir 
        }
        int endereco = Math.abs(resultado.end);

        RandomAccessFile arquivo = null;

        try {
            arquivo = new RandomAccessFile(new File(nomeArquivoHash), "rw");

            // para exclui apenas marcaremos a flag de cliente para LIBERADO
            arquivo.seek(endereco * tabela.getTamanhoRegistro());
            Tupla tupla = Tupla.le(arquivo, tabela);
            tupla.setFlag(Tupla.LIBERADO); //tupla.flag = Cliente.LIBERADO;
            arquivo.seek(endereco * tabela.getTamanhoRegistro());
            tupla.salva(arquivo);

        } catch (IOException e) {
            throw new IOException("Erro ao excluir");
        } finally {
            if (arquivo != null) {
                arquivo.close();
            }
        }
        return endereco;
    }

    public void listarTodosDados() throws IOException {
        RandomAccessFile arquivo = null;

        try {
            arquivo = new RandomAccessFile(new File(tabela.getNomeArquivo()), "rw");
            for (int i = 0; i < 100; i++) {
                arquivo.seek(i * tabela.getTamanhoRegistro());
                Tupla tupla = Tupla.le(arquivo, tabela);
                if (tupla.isFlag() != Tupla.LIBERADO) {
                    System.out.println(" " + tupla);
                }
            }
        } catch (IOException e) {
            throw new IOException("Erro ao listar todos os dados.");
        } finally {
            if (arquivo != null) {
                arquivo.close();
            }
        }
    }

    private int hash(int x) {
        return (x % 23);
    }

}
