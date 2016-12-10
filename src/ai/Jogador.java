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
public class Jogador {
    
   
    
    private String nome;
    private String posicao;
    private int idade;
    private int skill;
    private boolean status;

    public Jogador () {
    
        this.nome = "";
        this.posicao = "";
        this.idade=-1;
        this.skill = -1;
        this.status = false;
    }
    
    
    public Jogador (Jogador m) {
    
        this.nome= m.getNome();
        this.posicao=m.getPosicao();
        this.skill=m.getSkill();
        this.status=m.getStatus();
        this.idade=m.getIdade();
    }
    
    public Jogador(String nome, String posicao,int idade,int skill, boolean status) {
        this.nome = nome;
        this.posicao = posicao;
        this.skill = skill;
        this.status = status;
        this.idade=idade;
    }

    @Override
    public String toString() {
        return "Jogador : \n" + 
                "nome : " + nome + 
                "\n posicao : " + posicao + 
                "\n skill : " + skill + 
                "\n status : " + status 
                +"\n idade : " +idade;
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
        final Jogador other = (Jogador) obj;
        if (this.skill != other.skill) {
            return false;
        }
        if (this.status != other.status) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.posicao, other.posicao)) {
            return false;
        }
        if (this.idade != other.idade) {
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

    public String getPosicao() {
        return posicao;
    }

    public void setPosicao(String posicao) {
        this.posicao = posicao;
    }

    public int getSkill() {
        return skill;
    }

    public void setSkill(int skill) {
        this.skill = skill;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
    
    @Override
    public Jogador clone() {
    
    return new Jogador(this);
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
    
    
    
}
