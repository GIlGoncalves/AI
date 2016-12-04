/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai;

import java.util.List;

/**
 *
 * @author gil
 */
public class AI {

    /**
     * @param args the command line arguments
     
     */
    public static void main(String[] args)  {
        // TODO code application logic here
        List<Jogador> j ;
        Leitura l=new Leitura();
        j=l.lerJogadores("./Liga-NOS/CDF.xml");
       
        
        for(Jogador a : j) {
        
        System.out.println(a);
        }
        
        
    }
    
}
