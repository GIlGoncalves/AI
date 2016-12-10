/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai;

import java.util.*;

/**
 *
 * @author gil
 */
public class TrataInformacao {
    
    private Map<String,List<Jogador>> equipa;

    
    public TrataInformacao() {
        this.equipa = new HashMap<>();
        List<Jogador> jogadoresGR = new ArrayList<>();
        List<Jogador> jogadoresDEF = new ArrayList<>();
        List<Jogador> jogadoresMED = new ArrayList<>();
        List<Jogador> jogadoresAVA = new ArrayList<>();
        
        
        this.equipa.put("GR", jogadoresGR);
        this.equipa.put("DEF", jogadoresDEF);
        this.equipa.put("MED", jogadoresMED);
        this.equipa.put("AVA", jogadoresAVA);
        
    }
    
    public TrataInformacao(Map<String, List<Jogador>> equipa) {
        this.equipa = equipa;
    }
    
    
    public TrataInformacao(TrataInformacao m) {
    
        this.equipa=m.getEquipa();
    }

    public Map<String,List<Jogador>>  equipa(List<Jogador> m) {
        List<Jogador> tmp;
         List<Jogador> naoInscrito;
       
        
        for (Jogador j : m) {
        
            tmp= this.equipa.get(j.getPosicao());
            
            if(tmp.isEmpty() && j.getStatus()) {
               
                tmp.add(j.clone());
               this.equipa.put(j.getPosicao(), tmp);
               
            
            }
            
            else {
                   if(j.getStatus()) {
                       
                        tmp=trataJogos(j,tmp);
                        this.equipa.put(j.getPosicao(), tmp);
                        
                        
                   }
              
            
            }
       
        
        } 
        
        naoInscrito=naoInscritos(m);
        
        equipaTotal(naoInscrito);
        
        return this.equipa;
        
        
    }
    
    private void equipaTotal(List<Jogador> jogadores) {
      Jogador maior = new Jogador();
      Jogador medio = new Jogador();
      Jogador fraco = new Jogador();
      int i;  
      
      for(i=0;i<jogadores.size();i++) {
          
          if(jogadores.get(i).getSkill()>maior.getSkill() && jogadores.get(i).getStatus()) {
          
              fraco = medio;
              medio=maior;
              maior= jogadores.get(i);
          
          }
          
          else {
               
                 if(jogadores.get(i).getSkill()>medio.getSkill() && jogadores.get(i).getStatus()) {
          
                        fraco = medio;
                        medio=jogadores.get(i);
              
          
                } 
           
                 else {
                         if(jogadores.get(i).getSkill()>fraco.getSkill() && jogadores.get(i).getStatus()) {
          
                                
                                fraco=jogadores.get(i);


                       } 
                 
                     
                     
                 
                 
                 }
              
              
          
          
          }
          
          
      
      
      }
      
      if (maior.getStatus() && medio.getStatus() && fraco.getStatus()) {
     
      
      this.equipa.get(maior.getPosicao()).add(maior.clone());
      this.equipa.get(medio.getPosicao()).add(medio.clone());
      this.equipa.get(fraco.getPosicao()).add(fraco.clone());
      }
      
      else {
      
          if(maior.getStatus()) {
          
              this.equipa.get(maior.getPosicao()).add(maior.clone());
            
          }
          
          if(medio.getStatus()) {
          
              this.equipa.get(medio.getPosicao()).add(medio.clone());
              
          }
          
          if(fraco.getStatus()) {
          
              this.equipa.get(fraco.getPosicao()).add(fraco.clone());
          }
          
      
      }
      
   
      
    }
    
    public void tamanhao() {
    
        for (List<Jogador> m : this.equipa.values()) {
        
            System.out.println(m.size());
        
        }
        
    
    }
    
  
    private List<Jogador> naoInscritos(List<Jogador> jogadores) {
    
        List<Jogador> tmp = new ArrayList<>();
        
        for(Jogador a : jogadores) {
        
        if(!this.equipa.get(a.getPosicao()).contains(a)) {
        
            tmp.add(a.clone());
        }
      
            }
        
        return tmp;
      
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
        final TrataInformacao other = (TrataInformacao) obj;
        if (!Objects.equals(this.equipa, other.equipa)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
       StringBuilder s = new StringBuilder();
        
        for(List<Jogador> a : this.equipa.values()) {
          
            for(Jogador jogador : a) {
            
                
                s.append(jogador).append("\n");
                
            
            }
            
        }
        
        return s.toString();
        
        
        
    }
    
    
    
    
    
    /**
     *
     * @return
     * @throws CloneNotSupportedException
     */
    @Override
    public TrataInformacao clone() throws CloneNotSupportedException {
    
        return new TrataInformacao(this);
    }
    
    
    
   
    
    
    
    public Map<String,List<Jogador>> getEquipa() {
        return equipa;
    }

    public void setEquipa(Map<String,List<Jogador>> equipa) {
        this.equipa = equipa;
    }

    private List<Jogador> trataJogos(Jogador jogador, List<Jogador> tmp) {
        String posicao = jogador.getPosicao();
        Jogador k;
        
        if(posicao.equals("GR") || posicao.equals("AVA")) {
            
            
            k=tmp.get(0);
             
            if(jogador.getSkill()>k.getSkill()) {
                tmp.remove(k);
                tmp.add(jogador.clone());
                
            }
            
        }
        
        else {
        
            
            if(posicao.equals("DEF")) {
                
                if(tmp.size()<4) {
                
                    tmp.add(jogador.clone());
                
                }
                else {
                    
                  k=retiraMinimo(tmp);
                  
                  
                  if (jogador.getSkill() > k.getSkill()) {
                    
                       tmp.remove(k);
                       tmp.add(jogador.clone());
                      
                  }
                  
                }
                 
            }
            
            else {
                
                
                if(tmp.size()<5) {
                
                    tmp.add(jogador.clone());
                
                }
                else {
                    
                  k=retiraMinimo(tmp);
                  
                  
                  if (jogador.getSkill() > k.getSkill()) {
                    
                       tmp.remove(k);
                       
                       tmp.add(jogador.clone());
                      
                      
                  }
                  
                }
                

            
            }
         
        }
        
        
        
        
        return tmp;

    }

    private Jogador retiraMinimo(List<Jogador> tmp) {
        Jogador minimo = tmp.get(0);
        int i;
        
        for(i=1;i<tmp.size();i++) {
        
            if(tmp.get(i).getSkill()<minimo.getSkill()) {
            
                minimo=tmp.get(i);
            }
        
        }
        
        return minimo;
       
    }
    
    public float mediaIdade() {
    
       float mediaIdade=0;
       int conta=0;
        int i ;
       for(List<Jogador> j : this.equipa.values()) {
         
           for(i=0;i<j.size();i++) {
           
               mediaIdade=mediaIdade+j.get(i).getIdade();
             conta++;
           }
       }
        
    
    
    
        return mediaIdade/conta;
    }
    
    public float mediaJogadores() {
    
        int size=0;
        int soma=0;
        int i=0;
        
        for (List<Jogador> j : this.equipa.values()) {
        
            for(i=0;i<j.size();i++) {
           
               soma=soma+j.get(i).getSkill();
             size++;
           }
            
            
        
        } 
        
    return (float)(soma/size);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
