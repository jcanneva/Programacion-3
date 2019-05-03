package tpe;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.ListIterator;

public class Ruta {

	LinkedList<Reserva> reservas;
	Aeropuerto origen; 
	Aeropuerto destino; 
	double distancia;
	boolean cabotaje;
	HashMap<String,Integer> aerolineas;
	
	public Ruta (Aeropuerto origen, Aeropuerto destino, double d, boolean c) {
		this.setOrigen(origen);
		this.setDestino(destino);
		this.distancia=d;
		this.cabotaje=c;
		this.aerolineas=new HashMap<String, Integer>();
		this.reservas = new LinkedList<Reserva>();
		}
	
	public void setAerolineas(HashMap<String,Integer> a) {
		this.aerolineas=a;
	}
	
	public void addReserva(Reserva r) {
		this.reservas.add(r);
	}
	
	public Reserva getReserva(String aerolinea) {
		ListIterator<Reserva> itr =this.reservas.listIterator();
		while (itr.hasNext()) {
			Reserva re = itr.next();
			if(re.getAerolinea().equals(aerolinea))
				return re;
		}
		return null;
	}
	
	public HashMap<String,Integer> getAerolineas() {
		return new HashMap<String,Integer>(this.aerolineas);
	}
	
	public void addAerolinea(String n, Integer i) {
		this.aerolineas.put(n, i);
	}

	public void setOrigen(Aeropuerto p) {
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
	
	public Aeropuerto getOrigen() {
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
		return "1)Origen: " + origen.getNombre() + " 2)Destino: "+destino.getNombre()+  " 3)Distancia: " + distancia + " 4)Cabotaje: " + cabotaje+
				" 5)Aerolineas: "+aerolineas;
	}
}
