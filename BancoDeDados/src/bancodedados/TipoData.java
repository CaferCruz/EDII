/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package bancodedados;

/**
 *
 * @author WALMIR
 */
public class TipoData {
    public int dataI;
    public String dataS;
    public int tipo;
    
    TipoData(int dataI){
        this.dataI= dataI;
    }
    TipoData(String dataS){
        this.dataS = dataS;
    }
    
    
    @Override
    public String toString(){
        if(tipo ==1){
            return ""+dataI;
        }if(tipo ==2){
            return dataS;
        }
        else{
            return "";
        }
    }
    
   @Override
    public boolean equals(Object obj) {
       if (obj == null) {
            return false;
       }
       if (getClass() != obj.getClass()) {
            return false;
       }
       final TipoData other = (TipoData) obj;
       if( this.tipo!= other.tipo){
           return false;
       }
       if( this.dataI!= other.dataI){
           return false;
       }
       if( this.dataS!= other.dataS){
           return false;
       }
       return true;
   }
    
}
