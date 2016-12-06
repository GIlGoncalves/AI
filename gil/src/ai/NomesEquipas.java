/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai;

import java.util.*;

/**
 *"Sp. Braga","Sporting","Tondela","V. Guimarães","V. Setúbal"
 * @author gil
 */
public class NomesEquipas {
    private Map<String,Map<String,String>> equipas;
    
    public NomesEquipas(){
    this.equipas = new HashMap<>();
        
        Map<String,String> equipasP = new HashMap<>();
        equipasP.put("Arouca","FCA");
        equipasP.put("Belenenses","CFB");
        equipasP.put("Benfica","SLB");
        equipasP.put("Boavista","BFC");
        equipasP.put("Desp. Chaves","GDC");
        equipasP.put("Estoril","GDE");
        equipasP.put("FC Porto","FCP");
        equipasP.put("Feirense","CDF");
        equipasP.put("Marítimo","CSM");
        equipasP.put("Moreirense","MFC");
        equipasP.put("Nacional","CDN");
        equipasP.put("Paços Ferreira","PDF");
        equipasP.put("Rio Ave","RAC");
        equipasP.put("Sp. Braga","SCB");
        equipasP.put("Sporting","SCP");
        equipasP.put("Tondela","CDT");
        equipasP.put("V. Guimarães","VFC");
        equipasP.put("V. Setúbal","VDS");
        
        this.equipas.put("Liga Portuguesa",equipasP);

       
    }

    
    public String[] procuraEquipa(String liga, String equipaC, String equipaF){
    
        String [] equipa = new String [2];
        
        equipa [0]= this.equipas.get(liga).get(equipaC);
        equipa [1]= this.equipas.get(liga).get(equipaF);
        
        
    
    
    return equipa;
    
    }
    
    
    
    
}
