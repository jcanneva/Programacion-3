package entidad;

import java.util.LinkedList;
import java.util.ListIterator;

public class Grafo {

	private LinkedList<Aeropuerto> aeropuertos;
	private LinkedList<Ruta> rutas;
	

	final static String VISITADO = "amarillo";
	final static String NO_VISITADO = "blanco";
	final static String TERMINADO = "negro";

	public Grafo() {
		this.aeropuertos = new LinkedList<Aeropuerto>();
		this.rutas = new LinkedList<Ruta>();
	}

	public LinkedList<Aeropuerto> getAeropuertos() {
		return new LinkedList<Aeropuerto>(this.aeropuertos);
	}

	public LinkedList<Ruta> getRutas() {
		return new LinkedList<Ruta>(rutas);
	}

	public void setRutas(LinkedList<Ruta> r) {
		this.rutas = new LinkedList<Ruta>(r);
	}

	public void setAeropuertos(LinkedList<Aeropuerto> aeropuertos) {
		this.aeropuertos = new LinkedList<Aeropuerto>(aeropuertos);
	}

	public void addRuta(Ruta origen) {
		this.rutas.add(origen);
		Aeropuerto o = getAeropuerto(origen.getOrigen().getNombre());
		if (o != null) {
			o.addRuta(origen);
			Ruta destino = new Ruta(origen.getDestino(), origen.getOrigen(), origen.getDistancia(),
					origen.getCabotaje());
			destino.setAerolineas(origen.getAerolineas());
			this.rutas.add(destino);
			Aeropuerto d = getAeropuerto(origen.getDestino().getNombre());
			d.addRuta(destino);
		}
	}

	public void addAeropuerto(Aeropuerto p) {
		this.aeropuertos.add(p);
	}

	public Aeropuerto getAeropuerto(String n) {
		ListIterator<Aeropuerto> itr = this.aeropuertos.listIterator();
		while (itr.hasNext()) {
			Aeropuerto a = itr.next();
			if (a.getNombre().equals(n))
				return a;
		}
		return null;
	}

	public Ruta getRuta(String origen, String destino) {
		ListIterator<Ruta> itr = this.rutas.listIterator();
		while (itr.hasNext()) {
			Ruta a = itr.next();
			if (a.getOrigen().getNombre().equals(origen) && a.getDestino().getNombre().equals(destino) && a.hayVuelo())
				return a;
		}
		return null;
	}

	public Ruta getRuta(String origen, String destino, String aerolinea) {
		ListIterator<Ruta> itr = this.rutas.listIterator();
		while (itr.hasNext()) {
			Ruta a = itr.next();
			if (a.getOrigen().getNombre().equals(origen) && a.getDestino().getNombre().equals(destino)
					&& a.getAerolineas().containsKey(aerolinea))
				return a;
		}
		return null;
	}

	public Aeropuerto getAeropuertoCercano(Aeropuerto origen) {
		double dist = Double.MAX_VALUE;
		Aeropuerto tmp = null;
		for (Ruta r : origen.getRutas()) {
			if (r.getDistancia() < dist && r.getDestino().getEstado().equals(NO_VISITADO)) {
				dist = r.getDistancia();
				tmp = r.getDestino();
			}
		}
		return tmp;
	}

}
