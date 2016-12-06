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
public class Equipa {
    
    private List<Jogador> jogadores;
    private int posicaoTabela;
    private int ponto;
    private String nome;
    private String sigla;
    private List<Jogos> jogosEfectuados;
    

    public Equipa() {
    
        this.jogadores= new ArrayList<>();
        this.posicaoTabela = 0;
        this.ponto=-1;
        this.nome = "";
        this.sigla = "";
    }
    
    public Equipa (Equipa m) {
    
        this.jogadores=m.getJogadores();
        this.posicaoTabela=m.getPosicaoTabala();
        this.ponto=m.getPonto();
        this.nome = m.getNome();
        this.sigla=m.getSigla();
    }




    public Equipa(ArrayList<Jogador> jogadores, int posicaoTabala, int ponto, String nome, String sigla) {
        this.jogadores = jogadores;
        this.posicaoTabela = posicaoTabala;
        this.ponto = ponto;
        this.nome = nome;
        this.sigla = sigla;
    }

    @Override
    public String toString() {
        return "Equipa : \n" + 
                "jogadores : " + jogadores +
                "\n posicaoTabala : " + posicaoTabela + 
                "\n ponto : " + ponto + 
                "\n nome : " + nome + 
                "\n sigla : " + sigla + '}';
    }

   
    public void addJogador(Jogador j) {
    
        this.jogadores.add(j.clone());
    
    }
    
    public String imprimeJogadores () {
     
        StringBuilder s = new StringBuilder();
        
        for (Jogador e : this.jogadores) {
        
            s.append(e.toString()).append("\n");
        
        }
        
    return s.toString();
    
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
        if (this.posicaoTabela != other.posicaoTabela) {
            return false;
        }
        if (this.ponto != other.ponto) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.sigla, other.sigla)) {
            return false;
        }
        if (!Objects.equals(this.jogadores, other.jogadores)) {
            return false;
        }
        return true;
    }

    public List<Jogador> getJogadores() {
        return jogadores;
    }

    public void setJogadores(List<Jogador> jogadores) {
        this.jogadores = jogadores;
    }

    public int getPosicaoTabala() {
        return posicaoTabela;
    }

    public void setPosicaoTabala(int posicaoTabala) {
        this.posicaoTabela = posicaoTabala;
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

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
    
    
    @Override
    public Equipa clone() {
    
        return new Equipa(this);
    
    }
    
    
    
    
    
    
    
    
    
    
    
}
