package tpe;

import java.util.HashMap;

public class Ruta {

	String origen; 
	Aeropuerto destino; 
	double distancia;
	boolean cabotaje;
	HashMap<String,Integer> aerolineas;
	
	public Ruta (String origen, Aeropuerto destino, double d, boolean c) {
		this.setOrigen(origen);
		this.setDestino(destino);
		this.distancia=d;
		this.cabotaje=c;
		this.aerolineas=new HashMap<String, Integer>();
	}
	
	public void setAerolineas(HashMap<String,Integer> a) {
		this.aerolineas=a;
	}
	
	public HashMap<String,Integer> getAerolineas() {
		return new HashMap<String,Integer>(this.aerolineas);
	}
	
	public void addAerolinea(String n, Integer i) {
		this.aerolineas.put(n, i);
	}

	public void setOrigen(String p) {
		this.origen=p;
	}
	
	public void setCabotaje(boolean c) {
		this.cabotaje=c;
	}
	
	public void setDestino(Aeropuerto p) {
		this.destino=p;
	}
	
	public boolean getCabotaje() {
		return this.cabotaje;
	}
	
	public String getOrigen() {
		return this.origen;
	}
	
	public Aeropuerto getDestino() {
		return this.destino;
	}

	public double getDistancia() {
		return distancia;
	}

	public void setDistancia(double distancia) {
		this.distancia = distancia;
	}

	public String toString() {
		return "1)Origen: " + origen + " 2)Destino: "+destino.getNombre()+  " 3)Distancia: " + distancia + " 4)Cabotaje: " + cabotaje+
				" 5)Aerolineas: "+aerolineas;
	}
}
