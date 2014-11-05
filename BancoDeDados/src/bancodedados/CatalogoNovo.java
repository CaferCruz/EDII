/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bancodedados;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author Cafer
 */
public class CatalogoNovo {

    HashMap<String, Tabela> tabelas = null;

    private final String arq = "catalogo2.dat";
    private final String FIM = "FimDeArquivo";

    public CatalogoNovo() throws IOException {
        tabelas = new HashMap<>();
      //  this.salva();
    }

    boolean adicionar(Tabela tab) throws FileNotFoundException, IOException {
        if (!tabelas.containsKey(tab.getNome())) {
            tabelas.put(tab.getNome(), tab);
            this.salva();
            return true;
        }
        return false;
    }

    boolean remover(String nome) throws IOException {
        if (tabelas.containsKey(nome)) {
            tabelas.remove(nome);
            this.salva();
            return true;
        }
        return false;
    }

    public Tabela getTabela(String nome) {
        return tabelas.get(nome);
    }

    void salva() throws FileNotFoundException, IOException {
        try (RandomAccessFile arqT = new RandomAccessFile(arq, "rw")) {
            for (Tabela t : tabelas.values()) {
                arqT.writeUTF(t.getNome());
                arqT.writeInt(t.getCampos().size());
                for (Campo c : t.getCampos()) {
                    arqT.writeInt(c.getTipo());
                    arqT.writeUTF(c.getAtributo());
                    //arqT.writeBoolean(c.isPk());
                }
            }
            arqT.writeUTF(FIM);
        } catch (FileNotFoundException ex) {
            throw new FileNotFoundException("Não foi possivel encontrar arquivo.");
        } catch (IOException ex) {
            throw new IOException("Não foi possivel escrever no disco");
        }
    }

    void carregar() throws IOException {
        try (RandomAccessFile arqT = new RandomAccessFile(arq, "rw")) {
            String nome = arqT.readUTF();
            while (!nome.equals(FIM)) {
                int qtdCampos = arqT.readInt();
                Tabela t = new Tabela(nome);
                for (int i = 0; i < qtdCampos; i++) {
                    Campo c = new Campo(arqT.readInt(), arqT.readUTF());
                    t.addCampo(c);
                }
                t.setPK();
                tabelas.put(nome, t);
                nome = arqT.readUTF();
            }
        } catch (FileNotFoundException ex) {
            throw new FileNotFoundException("Arquivo não encontrado");
        } catch (IOException ex) {
            throw new IOException("Erro ao acessar o disco.");
        }
    }

    public void listar() {
        String resp = "";
        Iterator it = tabelas.values().iterator();
        while (it.hasNext()) {
            resp += it.next().toString();
        }
        System.out.println(resp);
    }

    public boolean buscar(String nome) {
        return tabelas.containsKey(nome);
    }
}
