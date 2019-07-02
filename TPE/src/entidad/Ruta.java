package entidad;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

public class Ruta {

	private LinkedList<Reserva> reservas;
	private Aeropuerto origen;
	private Aeropuerto destino;
	private double distancia;
	private boolean cabotaje;
	private HashMap<String, Integer> aerolineas;

	public Ruta(Aeropuerto origen, Aeropuerto destino, double d, boolean c) {
		this.setOrigen(origen);
		this.setDestino(destino);
		this.distancia = d;
		this.cabotaje = c;
		this.aerolineas = new HashMap<String, Integer>();
		this.reservas = new LinkedList<Reserva>();
	}
	
	public LinkedList<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(LinkedList<Reserva> reservas) {
		this.reservas = new LinkedList<Reserva>(reservas);
	}

	public void setAerolineas(HashMap<String, Integer> a) {
		this.aerolineas = new HashMap<String,Integer>(a);
	}

	public void addReserva(Reserva r) {
		this.reservas.add(r);
	}

	public void actualizar() {
		for (Reserva r : this.reservas) {
			Iterator<String> itr = aerolineas.keySet().iterator();
			while (itr.hasNext()) {
				String aerolinea = itr.next();
				if (r.getAerolinea().equals(aerolinea))
					aerolineas.put(aerolinea, aerolineas.get(aerolinea) - r.getReservas());
			}
		}
	}

	public boolean hayVueloSinAerolinea(String a) {
		Iterator<String> itr = aerolineas.keySet().iterator();
		while (itr.hasNext()) {
			String aerolinea = itr.next();
			if (aerolineas.get(aerolinea) > 0 && !aerolinea.equals(a))
				return true;
		}
		return false;
	}
	
	public boolean hayVuelo() {
		Iterator<String> itr = aerolineas.keySet().iterator();
		while (itr.hasNext()) {
			String aerolinea = itr.next();
			if (aerolineas.get(aerolinea) > 0 )
				return true;
		}
		return false;
	}

	public HashMap<String, Integer> getAerolineas() {
		return new HashMap<String, Integer>(this.aerolineas);
	}

	public void addAerolinea(String n, Integer i) {
		this.aerolineas.put(n, i);
	}

	public void setOrigen(Aeropuerto p) {
		this.origen = p;
	}

	public void setCabotaje(boolean c) {
		this.cabotaje = c;
	}

	public void setDestino(Aeropuerto p) {
		this.destino = p;
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
		return origen.getNombre()+" -- "+destino.getNombre() ;
	}
	
}
