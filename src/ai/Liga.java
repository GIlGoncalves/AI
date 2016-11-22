/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author gil
 */
public class Liga {
    private String nome;
    private String pais;
    private List<Equipa> equipas;
    

    public Liga(String nome, String pais, List<Equipa> equipas) {
        this.nome = nome;
        this.pais = pais;
        this.equipas = equipas;
        
    }

    
    
    public Liga() {
    
        this.nome="";
        this.pais="";
        this.equipas = new ArrayList<>();
        
    }
    
    public Liga(Liga m) {
    
        this.nome=m.getNome();
        this.pais=m.getPais();
        this.equipas=m.getEquipas();
       
    
    }

    @Override
    public String toString() {
        return "Liga : \n"
                + "Nome da liga : " + nome + 
                "\n Pais : " + pais + 
                "\n Equipas : " + equipas;
                
    }
    
  

   
    
   public String imprimeEquipas() {
   
       
        StringBuilder s = new StringBuilder();
        
        for (Equipa e : this.equipas) {
        
            s.append(e.toString()).append("\n");
        
        }
        
    return s.toString();
   
   }

   public void addEquipa(Equipa e) {
   
       this.equipas.add(e.clone());
   
   }

    

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Liga other = (Liga) obj;
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.pais, other.pais)) {
            return false;
        }
        if (!Objects.equals(this.equipas, other.equipas)) {
            return false;
        }
       
        return true;
    }
   
    
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public List<Equipa> getEquipas() {
        return equipas;
    }

    public void setEquipas(List<Equipa> equipas) {
        this.equipas = equipas;
    }

    
    @Override
    public Liga clone() {
    
        return new Liga(this);
    }
    
    
    
    
    
    
    
    
}
