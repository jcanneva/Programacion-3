package tpe;

import java.util.LinkedList;
import java.util.ListIterator;

public class Grafo {

	private LinkedList<Aeropuerto> aeropuertos;
	private LinkedList<Ruta> rutas;
	private LinkedList<Reserva> reservas;
	private LinkedList<LinkedList<Ruta>> servicio2;
	private LinkedList<Ruta> solucionBack;

	final static String VISITADO = "amarillo";
	final static String NO_VISITADO = "blanco";
	final static String TERMINADO = "negro";

	public Grafo() {
		this.aeropuertos = new LinkedList<Aeropuerto>();
		this.rutas = new LinkedList<Ruta>();
		this.reservas = new LinkedList<Reserva>();
		this.servicio2 = new LinkedList<LinkedList<Ruta>>();
		this.solucionBack = new LinkedList<Ruta>();
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

	public LinkedList<LinkedList<Ruta>> servicio2(String o, String d, String a) {
		Aeropuerto origen = this.getAeropuerto(o);
		Aeropuerto destino = this.getAeropuerto(d);
		LinkedList<LinkedList<Ruta>> servicio2 = new LinkedList<LinkedList<Ruta>>();
		if (origen != null && destino != null) {
			servicio2 = this.DFS(origen, destino, a);
		}
		return servicio2;
	}

	public LinkedList<Ruta> servicio3(String pais1, String pais2) {
		LinkedList<Ruta> salida = new LinkedList<Ruta>();
		ListIterator<Ruta> it = this.getRutas().listIterator();
		while (it.hasNext()) {
			Ruta tmp = it.next();
			if (tmp.getOrigen().getPais().equals(pais1) && tmp.getDestino().getPais().equals(pais2))
				salida.add(tmp);
		}
		return salida;
	}

	private LinkedList<LinkedList<Ruta>> DFS(Aeropuerto origen, Aeropuerto destino, String aerolinea) {
		for (Aeropuerto p : this.aeropuertos)
			p.setEstado(NO_VISITADO);
		LinkedList<Ruta> rutas = new LinkedList<Ruta>();
		origen.setEstado(VISITADO);
		DFS_explorar(origen, destino, rutas, aerolinea);
		return new LinkedList<LinkedList<Ruta>>(this.servicio2);
	}

	private void DFS_explorar(Aeropuerto actual, Aeropuerto destino, LinkedList<Ruta> rutas, String aerolinea) {
		if (actual.equals(destino)) {
			LinkedList<Ruta> tmp = new LinkedList<Ruta>(rutas);
			this.servicio2.add(tmp);
		} else {
			for (Ruta r : actual.getRutas()) {
				if (r.getDestino().getEstado().equals(NO_VISITADO)) {
					actual = r.getDestino();
					actual.setEstado(VISITADO);
					rutas.add(r);
					DFS_explorar(actual, destino, rutas, aerolinea);
					rutas.remove(r);
					actual.setEstado(NO_VISITADO);
				}
			}
		}
	}

	public LinkedList<Ruta> Backtracking(String o) {
		Aeropuerto origen = this.getAeropuerto(o);
		LinkedList<Aeropuerto> u = new LinkedList<Aeropuerto>(this.aeropuertos);
		origen.setEstado(VISITADO);
		u.remove(origen);
		LinkedList<Ruta> s = new LinkedList<Ruta>();
		Back(u, origen, s, o);
		return this.solucionBack;
	}

	private boolean esMenor(LinkedList<Ruta> s) {
		if (!this.solucionBack.isEmpty()) {
			double tmp = 0;
			double aux = 0;
			ListIterator<Ruta> itr = this.solucionBack.listIterator();
			ListIterator<Ruta> itr2 = s.listIterator();
			while (itr.hasNext())
				tmp += itr.next().getDistancia();
			while (itr2.hasNext())
				aux += itr2.next().getDistancia();
			if (tmp > aux)
				return true;
			else
				return false;
		} else
			return true;
	}

	private void Back(LinkedList<Aeropuerto> u, Aeropuerto origen, LinkedList<Ruta> s, String o) {
		if (u.isEmpty()) {
			Aeropuerto destino=this.getAeropuerto(o);
			if (origen.getAdyacentes().contains(destino)) {
				Ruta r = this.getRuta(origen.getNombre(), destino.getNombre());
				s.add(r);
				if (esMenor(s)) {
					LinkedList<Ruta> aux = new LinkedList<Ruta>(s);
					this.solucionBack = aux;
				}
				s.remove(r);
			}
		} else {
			for (Ruta r : origen.getRutas()) {
				if (r.getDestino().getEstado().equals(NO_VISITADO)) {
					origen = r.getDestino();
					origen.setEstado(VISITADO);
					u.remove(origen);
					s.add(r);
					if (esMenor(s))
						Back(u, origen, s, o);
					s.remove(r);
					origen.setEstado(NO_VISITADO);
					u.add(origen);
				}
			}
		}
	}

	public void Greedy(String origen) {

	}

}
