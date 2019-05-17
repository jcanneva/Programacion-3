package tpe;

import java.util.LinkedList;
import java.util.ListIterator;

public class Grafo {

	private LinkedList<Aeropuerto> aeropuertos;
	private LinkedList<Ruta> rutas;
	private LinkedList<Reserva> reservas;
	private LinkedList<LinkedList<Ruta>> servicio2;

	final static String VISITADO = "amarillo";
	final static String NO_VISITADO = "blanco";
	final static String TERMINADO = "negro";

	public Grafo() {
		this.aeropuertos = new LinkedList<Aeropuerto>();
		this.rutas = new LinkedList<Ruta>();
		this.reservas = new LinkedList<Reserva>();
		this.servicio2 = new LinkedList<LinkedList<Ruta>>();
	}

	public LinkedList<Aeropuerto> getAeropuertos() {
		return new LinkedList<Aeropuerto>(this.aeropuertos);
	}

	public void clearServicio2() {
		this.servicio2.clear();
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

	public LinkedList<Reserva> getReservas() {
		return new LinkedList<Reserva>(reservas);
	}

	public void addReserva(Reserva r) {
		this.reservas.add(r);
		Ruta ruta = this.getRuta(r.getOrigen(), r.getDestino(), r.getAerolinea());
		if (ruta != null)
			ruta.addReserva(r);
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
			if (a.getOrigen().getNombre().equals(origen) && a.getDestino().getNombre().equals(destino)&&a.hayVuelo())
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

	public LinkedList<LinkedList<Ruta>> servicio2(String o, String d, String a) {
		Aeropuerto origen = this.getAeropuerto(o);
		Aeropuerto destino = this.getAeropuerto(d);
		LinkedList<LinkedList<Ruta>> servicio2 = new LinkedList<LinkedList<Ruta>>();
		if (origen!=null&&destino!=null) {
			servicio2 = this.DFS(origen, destino, a);
		}
		return servicio2;
	}

	public LinkedList<Ruta> servicio3(String pais1, String pais2) {
		LinkedList<Ruta> salida = new LinkedList<Ruta>();
		ListIterator<Ruta> it=this.getRutas().listIterator();
			while(it.hasNext()) {
				Ruta tmp= it.next();
				if(tmp.getOrigen().getPais().equals(pais1)&&tmp.getDestino().getPais().equals(pais2))
					salida.add(tmp);
			}
		
		return salida;
	}

	private LinkedList<LinkedList<Ruta>> DFS(Aeropuerto origen, Aeropuerto destino, String aerolinea) {
		for (Aeropuerto p : this.aeropuertos)
			p.setEstado(NO_VISITADO);
		LinkedList<Aeropuerto> ady = origen.getAdyacentes();
		LinkedList<Ruta> rutas = new LinkedList<Ruta>();
		origen.setEstado(VISITADO);
		for (Aeropuerto actual : ady) {
			Ruta ruta = this.getRuta(origen.getNombre(), actual.getNombre());
			if (ruta!=null&&ruta.hayVueloSinAerolinea(aerolinea)) {
				rutas.add(ruta);
				if (ruta.getDestino().equals(destino))
					this.servicio2.add(rutas);
				else if (actual.getEstado().equals(NO_VISITADO)) {
					DFS_explorar(null, origen, actual, destino, rutas, aerolinea);
				}
			}
			rutas = new LinkedList<Ruta>();
		}
		return new LinkedList<LinkedList<Ruta>>(this.servicio2);
	}

	private void DFS_explorar(Aeropuerto anterior, Aeropuerto origen, Aeropuerto actual, Aeropuerto destino,
			LinkedList<Ruta> rutas, String aerolinea) {
		if (!actual.equals(destino) && !actual.equals(origen) && !actual.equals(anterior)) {
			actual.setEstado(VISITADO);
			Ruta ruta = this.getRuta(actual.getNombre(), destino.getNombre());
			if (ruta != null && ruta.hayVueloSinAerolinea(aerolinea) && ruta.getDestino().equals(destino)) {
				rutas.add(ruta);
				LinkedList<Ruta> aux = new LinkedList<Ruta>(rutas);
				this.servicio2.add(aux);
				rutas.removeLast();
			}
			LinkedList<Aeropuerto> adyacentes = actual.getAdyacentes();
			for (Aeropuerto tmp : adyacentes) {
				if (!tmp.equals(origen) && !tmp.equals(anterior) && !tmp.equals(destino)) {
					Ruta ruta_tmp = this.getRuta(actual.getNombre(), tmp.getNombre());
					if (ruta_tmp!=null&&ruta_tmp.hayVueloSinAerolinea(aerolinea)) {
						rutas.add(ruta_tmp);
						if (tmp.getEstado().equals(NO_VISITADO)) 
							DFS_explorar(actual, origen, tmp, destino, rutas, aerolinea);
						else if (!rutas.isEmpty())
							rutas.remove(ruta_tmp);
					}
				}
			}
			if (!rutas.isEmpty())
				rutas.removeLast();
			actual.setEstado(NO_VISITADO);
		}
	}

}
