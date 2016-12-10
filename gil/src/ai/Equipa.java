/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai;


import java.util.Objects;

/**
 *
 * @author gil
 */
public class Equipa {
    
    private int ponto;
    private String nome;
   
    

    public Equipa() {
    
       
        this.ponto=-1;
        this.nome = "";
        
    }
    
    public Equipa (Equipa m) {
    
       
        this.ponto=m.getPonto();
        this.nome = m.getNome();
       
    }




    public Equipa(int ponto, String nome) {
       
        this.ponto = ponto;
        this.nome = nome;
       
    }

    @Override
    public String toString() {
        return "Equipa : \n" + 
                
                "\n ponto : " + ponto + 
                "\n nome : " + nome + 
                 '}';
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
        final Equipa other = (Equipa) obj;
        
        if (this.ponto != other.ponto) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
       
        return true;
    }

    
    public int getPonto() {
        return ponto;
    }

    public void setPonto(int ponto) {
        this.ponto = ponto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    
    
    
    @Override
    public Equipa clone() {
    
        return new Equipa(this);
    
    }
    
    
    
    
    
    
    
    
    
    
    
}
