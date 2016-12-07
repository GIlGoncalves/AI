package ai;

import java.util.GregorianCalendar;
import java.util.HashSet;

import java.io.Serializable;
import sun.util.calendar.LocalGregorianCalendar.Date;


public class LigaB implements Serializable{
	
	private  String nome;
	private HashSet<Prediction> Jogos;
	private float res[][];
	private GregorianCalendar data; // nao ta bom
	
	public LigaB(){
		this.nome = "tbd";
		Jogos = new HashSet<>();
	data = new GregorianCalendar();
	}
   public LigaB(String liga) {
		this.nome = liga;
		Jogos = new HashSet<>();
		data =  new GregorianCalendar();
	}
  
   public void criaRes(int x ){
	   res = new float [x][4];
   }
   
   public HashSet<Prediction> getPred(){
	return Jogos;
}
   
   public int getNJogos(){
	return Jogos.size();
}
   public String getNome(){
	return nome;
}
   public void addPred(Prediction x ){
	   Jogos.add(x);
   }
   public void  updateRes(int i ,int x ,float y){
	   res[i][x] = y ;
	  
	   }
	   
   
   public float[] getCriterios(int index){
	   float ret [] = new float [4];
	   for(int i=0; i < 4 ; i++){
		   ret[i] = res[index][i];
	   }
	   
	   return ret;
   }
}
