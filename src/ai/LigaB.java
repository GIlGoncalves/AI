package ai;

import java.util.HashSet;

import java.io.Serializable;
import sun.util.calendar.LocalGregorianCalendar.Date;

public class LigaB implements Serializable{
   public LigaB(String liga) {
		this.nome = liga;
		Jogos = new HashSet<>();
		//data = agora ?
	}
public  String nome;
	public HashSet<Prediction> Jogos;
	public Date data;
}