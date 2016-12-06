/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ai;

import java.util.Arrays;
import java.util.GregorianCalendar;
import java.util.Objects;

/**
 *
 * @author gil
 */
/*
 ponto 2 significa empate
1 significa que foi a de fora que ganhou
zero foi a equipa da casa
*/

public class Jogos {
    
    private GregorianCalendar dataJogo;
    private String [] equipas;
    private int [] resultado;
    private int pontos;  

    
    
    
    public Jogos() {
    
        this.dataJogo= new GregorianCalendar();
        this.equipas = new String[2];
        this.resultado = new int[2];
        this.pontos=-1;
    }
    
    public Jogos(Jogos j) {
    
        this.dataJogo=j.getDataJogo();
        this.equipas=j.getEquipas();
        this.resultado=j.getResultado();
        this.pontos=j.getPontos();
        
    
    }
    
    
    public Jogos(GregorianCalendar dataJogo, String[] equipas, int[] resultado, int pontos) {
        this.dataJogo = dataJogo;
        this.equipas = equipas;
        this.resultado = resultado;
        this.pontos = pontos;
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
        final Jogos other = (Jogos) obj;
        if (this.pontos != other.pontos) {
            return false;
        }
        if (!Objects.equals(this.dataJogo, other.dataJogo)) {
            return false;
        }
        if (!Arrays.deepEquals(this.equipas, other.equipas)) {
            return false;
        }
        if (!Arrays.equals(this.resultado, other.resultado)) {
            return false;
        }
        return true;
    }

   
    public GregorianCalendar getDataJogo() {
        return dataJogo;
    }

    public void setDataJogo(GregorianCalendar dataJogo) {
        this.dataJogo = dataJogo;
    }

    public String[] getEquipas() {
        return equipas;
    }

    public void setEquipas(String[] equipas) {
        this.equipas = equipas;
    }

    public int[] getResultado() {
        return resultado;
    }

    public void setResultado(int[] resultado) {
        this.resultado = resultado;
    }

    public int getPontos() {
        return pontos;
    }

    public void setPontos(int pontos) {
        this.pontos = pontos;
    }
    
    
    @Override
    public Jogos clone() {
    
        return new Jogos(this);
    }
    
    
    public void addJogos(GregorianCalendar data, String equipaCasa, String equipaFora, int resultadoCasa, int resultadoFora, int ponto){
    
        this.equipas[0]=equipaCasa;
        this.equipas[1]=equipaFora;
        this.dataJogo=data;
        this.resultado[0]=resultadoCasa;
        this.resultado[1]=resultadoFora;
        if (resultadoCasa > resultadoFora) {
        
            this.pontos =0;
        }
        else {
        
            if (resultadoFora>resultadoCasa)
            {
            
                this.pontos=1;
            }
            else this.pontos=0;
        }
        
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
