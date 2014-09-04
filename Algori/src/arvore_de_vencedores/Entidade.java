/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package arvore_de_vencedores;

import java.io.DataOutputStream;
import java.io.IOException;

/**
 *
 * @author Cafer
 */
public interface Entidade {
    
    public void salva(DataOutputStream out) throws IOException;
    
}
