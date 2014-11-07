package teste2;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author G6
 */
public class Catalogo {

    private HashMap<String, Tabela> tabelas = null;

    private final String arq = "catalogo.dat";
    private final String FIM = "FimDeArquivo";

    public Catalogo() throws IOException {
        tabelas = new HashMap<>();
    }

    public boolean adicionar(Tabela tab) throws FileNotFoundException, IOException {
        if (!tabelas.containsKey(tab.getNome())) {
            tabelas.put(tab.getNome(), tab);
            this.salva();
            return true;
        }
        return false;
    }

    boolean remover(String nome) throws IOException {
        if (tabelas.containsKey(nome)) {
            new File(tabelas.get(nome).getNomeArquivo()).delete();
            tabelas.remove(nome);
            this.salva();
            return true;
        }
        return false;
    }

    public Tabela getTabela(String nome) {
        return tabelas.get(nome);
    }

    public void salva() throws FileNotFoundException, IOException {
        File f = new File(arq);
        f.delete();

        try (RandomAccessFile arqT = new RandomAccessFile(f, "rw")) {
            for (Tabela t : tabelas.values()) {
                arqT.writeUTF(t.getNome());
                arqT.writeInt(t.getCampos().size());
                for (Campo c : t.getCampos()) {
                    arqT.writeInt(c.getCodTipo());
                    arqT.writeUTF(c.getNomeAtributo());
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
        if (new File(arq).exists()) {
            try (RandomAccessFile arqT = new RandomAccessFile(arq, "rw")) {
                String nome = arqT.readUTF();
                while (!nome.equals(FIM)) {
                    int qtdCampos = arqT.readInt();
                    List<Campo> campos = new ArrayList<>();
                    for (int i = 0; i < qtdCampos; i++) {
                        campos.add(new Campo(arqT.readInt(), arqT.readUTF())) ;
                    }
                    Tabela t = new Tabela(campos, nome,0);
                    t.setTabelaHash();
                    tabelas.put(nome, t);
                    nome = arqT.readUTF();
                }
            } catch (FileNotFoundException ex) {
                throw new FileNotFoundException("Arquivo não encontrado");
            } catch (IOException ex) {
                throw new IOException("Erro ao acessar o disco.");
            }
        }
    }

    public void listar() {
        String resp = "";
        Iterator it = tabelas.values().iterator();
        while (it.hasNext()) {
            resp += "\n\t "+it.next().toString();
        }
        System.out.println(resp);
    }

    public void listarNomeTabela() {
        Iterator it = tabelas.keySet().iterator();
        int pos = 1;
        while (it.hasNext()) {
            System.out.println("\t* " + pos + ". " + it.next().toString());
            pos++;
        }
    }

    public String getNomeTabela(int ind) {
        Iterator it = tabelas.keySet().iterator();
        int cont = 1;
        while (it.hasNext()) {
            if (cont == ind) {
                return it.next().toString();
            }
            cont++;
            it.next();
        }
        return null;
    }

    public boolean contem(String nome) {
        return tabelas.containsKey(nome);
    }
}
