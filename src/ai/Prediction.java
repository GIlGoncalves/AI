package ai;

import java.io.Serializable;
import java.util.GregorianCalendar;

import sun.util.calendar.LocalGregorianCalendar.Date;

public class Prediction implements Serializable{

private String siglaA;
private String siglaB;
private float Resultado;
private int Casa;
private float oc;
private float oe;
private float of;
private GregorianCalendar data;//nao ta bom


public Prediction(String siglaA, String siglaB, float resultado, int casa, float oc, float oe, float of) {
	
	this.siglaA = siglaA;
	this.siglaB = siglaB;
	Resultado = resultado;
	Casa = casa;
	this.oc = oc;
	this.oe = oe;
	this.of = of;
	data =  new GregorianCalendar();
}
public String getSiglaA() {
	return siglaA;
}
public void setSiglaA(String siglaA) {
	this.siglaA = siglaA;
}
public String getSiglaB() {
	return siglaB;
}
public void setSiglaB(String siglaB) {
	this.siglaB = siglaB;
}
public float getResultado() {
	return Resultado;
}
public void setResultado(float resultado) {
	Resultado = resultado;
}
public int getCasa() {
	return Casa;
}
public void setCasa(int casa) {
	Casa = casa;
}
public float getOc() {
	return oc;
}
public void setOc(float oc) {
	this.oc = oc;
}
public float getOe() {
	return oe;
}
public void setOe(float oe) {
	this.oe = oe;
}
public float getOf() {
	return of;
}
public void setOf(float of) {
	this.of = of;
}

}
