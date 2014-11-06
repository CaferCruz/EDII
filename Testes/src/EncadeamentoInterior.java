/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.*;
import java.util.List;

public class EncadeamentoInterior {

    public static int NULO = -1;
    private final int tam = 23; // valor definido no enunciado
    private List<Campo> campos = null;
    private Tupla padrao = null;

    /**
     * Cria uma tabela hash vazia de tamanho tam, e salva no arquivo
     * nomeArquivoHash Compartimento que não tem lista encadeada associada deve
     * ter registro com chave de Cliente igual a -1 Quando o ponteiro para
     * próximo for null, ele deve ser igual ao endereço do compartimento
     *
     * @param campos
     */
    public EncadeamentoInterior(List<Campo> campos) {
        this.campos = campos;
        this.gerarPadrao();

    }

    private void gerarPadrao() {
        String padrao = "";
        for (int i = 0; i < campos.size() - 1; i++) {
            if (campos.get(i).getTipo() == Tipo.INTEGER) {
                padrao += "0, ";

            } else {
                padrao += ", ";
            }
        }
        if (campos.get(campos.size() - 1).getTipo() == Tipo.INTEGER) {
            padrao += "0";
        } else {
            padrao += " ";
        }
        this.padrao = new Tupla(campos, padrao);
    }

    public void criaHash(String nomeArquivoHash) throws IOException {
        RandomAccessFile arquivo = null;
        try { //TODO: criar a tabela hash
            arquivo = new RandomAccessFile(nomeArquivoHash, "rw");
            for (int i = 0; i < this.tam; i++) {
                this.padrao.setFlag(Tupla.LIBERADO);
                padrao.prox = i;
                padrao.salva(arquivo);
            }
        } catch (IOException e) {
            throw new IOException("Um erro ocorreu ao criar a Hash");
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
     * @param chave: chave do cliente que está sendo buscado
     * @param nomeArquivoHash nome do arquivo que contém a tabela Hash
     * @return Result contendo a = 1 se registro foi encontrado, e end igual ao
     * endereco onde o cliente foi encontrado ou a = 2 se o registro não foi
     * encontrado, e end igual ao primeiro endereço livre encontrado na lista
     * encadeada, ou -1 se não encontrou endereço livre
     * @throws java.lang.Exception
     */
    public Result busca(int chave, String nomeArquivoHash) throws Exception {
        //TODO: Inserir aqui o código do algoritmo    
        RandomAccessFile arquivo = null;
        int a = 0, j = NULO;
        int endereco = hash(chave);
        try {
            arquivo = new RandomAccessFile(nomeArquivoHash, "rw");
            while (a == 0) {
                arquivo.seek(endereco * padrao.getTamanhoRegistro());
                Tupla tupla = Tupla.le(arquivo, campos);
                if (tupla.getFlag() == Tupla.LIBERADO) {
                    j = endereco;
                }
                if ((tupla.getChave() == chave) && (tupla.getFlag() == Tupla.OCUPADO)) {
                    a = 1; // chave encontrada
                } else {
                    if (tupla.prox == endereco) {
                        a = 2; // chave não encontrada
                        endereco = j;
                    } else {
                        endereco = tupla.prox;
                    }
                }
            }
        } catch (IOException e) {
            throw new IOException("Um erro ocorreu na busca.");
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
     * @param tupla.getChave(): código do cliente a ser inserido
     * @param nomeArquivoHash nome do arquivo que contém a tabela Hash
     * @return endereço onde o cliente foi inserido, -1 se não conseguiu inserir
     * (inclusive em caso de overflow)
     * @throws java.lang.Exception
     */
    public int insere(Tupla tupla, String nomeArquivoHash) throws Exception {
        //TODO: Inserir aqui o código do algoritmo de inserção
        Result resultado = busca(tupla.getChave(), nomeArquivoHash);
        if (resultado.a == 1) {
            return -1; // o dado com essa chave já está na tabela 
        }
        int endereco = resultado.end;
        int i = 0, j, m;

        try (RandomAccessFile arquivo = new RandomAccessFile(nomeArquivoHash, "rw")) {
            if (endereco != NULO) {
                j = endereco;
            } else {
                j = hash(tupla.getChave());
                m = (int) arquivo.length();

                while (i < m) {
                    arquivo.seek(j * padrao.getTamanhoRegistro());
                    Tupla.le(arquivo, campos);
                    if (tupla.getFlag() == Tupla.OCUPADO) {
                        j = (j + 1) % m;
                        i = i + 1;
                    } else {
                        i = m + 2;
                    }
                }
                if (i == m + 1) {
                    return -1;
                }
                arquivo.seek(hash(tupla.getChave()) * padrao.getTamanhoRegistro());
                Tupla cliente = Tupla.le(arquivo, campos);
                int temp = cliente.prox;

                arquivo.seek(hash(tupla.getChave()) * padrao.getTamanhoRegistro());
                cliente = Tupla.le(arquivo, campos);
                cliente.prox = j;
                arquivo.seek(hash(tupla.getChave()) * padrao.getTamanhoRegistro());
                cliente.salva(arquivo);

                arquivo.seek(j * padrao.getTamanhoRegistro());
                cliente = Tupla.le(arquivo, campos);
                cliente.prox = temp;
                arquivo.seek(j * padrao.getTamanhoRegistro());
                cliente.salva(arquivo);

            }
            arquivo.seek(j * padrao.getTamanhoRegistro());
            Tupla aux = Tupla.le(arquivo, campos);
            aux.setChave();
            aux.setFlag(tupla.getFlag());
            arquivo.seek(j * padrao.getTamanhoRegistro());
            aux.salva(arquivo);

            endereco = j;
        } catch (IOException e) {
            throw new IOException("Erro ocorreu ao inserir");
        }

        return endereco;
    }

//    /**
//    * Executa exclusão em Arquivos por Encadeamento Exterior (Hash)
//     * @param tupla.getChave()
//    * @param nomeArquivoHash nome do arquivo que contém a tabela Hash
//    * @return endereço do cliente que foi excluído, -1 se cliente não existe
//     * @throws java.lang.Exception
//    */
//    public int exclui(int tupla.getChave(), String nomeArquivoHash) throws Exception {
//        //TODO: Inserir aqui o código do algoritmo de remoção
//        
//        Result resultado = busca(tupla.getChave(), nomeArquivoHash);
//        if(resultado.a == 2) return -1; // neste caso não achou o cliente para excluir 
//        
//        int endereco = resultado.end;        
//        
//        RandomAccessFile arquivo = null;
//        
//        try {
//            arquivo = new RandomAccessFile(new File(nomeArquivoHash), "rw");
//            
//            // para exclui apenas marcaremos a flag de cliente para LIBERADO
//            arquivo.seek(endereco*Cliente.tamanhoRegistro);
//            Cliente cliente = Cliente.le(arquivo);            
//            cliente.flag = Cliente.LIBERADO;
//            arquivo.seek(endereco*Cliente.tamanhoRegistro);
//            cliente.salva(arquivo);
//            
//        } catch(IOException e) {
//            //
//        } finally {
//            if(arquivo != null) {
//                arquivo.close();
//            }
//        }
//        
//        return endereco;
//    }
//
    private int hash(int x) {
        return (x % tam);
    }
}
