package ai;

import java.io.Serializable;

public class Prediction implements Serializable{
public String siglaA;
public String siglaB;
public float Resultado;
public int Casa;

public Prediction(String a ,String b, int c){
	this.siglaA = a ;
	this.siglaB = b;
	this.Casa = c;
}
}
