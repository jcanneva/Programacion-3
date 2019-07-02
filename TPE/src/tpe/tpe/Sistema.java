package tpe;


import java.util.LinkedList;
import java.util.ListIterator;

import entidad.Grafo;
import entidad.Ruta;
import entidad.Reserva;
import entidad.Aeropuerto;

public class Sistema {

	final static String VISITADO = "amarillo";
	final static String NO_VISITADO = "blanco";
	final static String TERMINADO = "negro";
	
	private LinkedList<Reserva> reservas;
	private LinkedList<LinkedList<Ruta>> servicio2;
	private LinkedList<Ruta> solucionBack;
	private LinkedList<Ruta> solucionGreedy;
	private Grafo grafo;
	
	public Sistema(Grafo grafo) {
	this.reservas = new LinkedList<Reserva>();
	this.servicio2 = new LinkedList<LinkedList<Ruta>>();
	this.solucionBack = new LinkedList<Ruta>();
	this.solucionGreedy = new LinkedList<Ruta>();
	this.setGrafo(grafo);
	}
	
	private void setGrafo(Grafo g) {
		this.grafo=g;
	}

	public LinkedList<Ruta> getSolucionGreedy() {
		return new LinkedList<Ruta>(this.solucionGreedy);
	}
	
	public void clearServicio2() {
		this.servicio2.clear();
	}

	public void clearGreedy() {
		this.solucionGreedy.clear();
	}

	public void clearBack() {
		this.solucionBack.clear();
	}
	
	public LinkedList<Reserva> getReservas() {
		return new LinkedList<Reserva>(reservas);
	}

	public void addReserva(Reserva r) {
		this.reservas.add(r);
		Ruta ruta = grafo.getRuta(r.getOrigen(), r.getDestino(), r.getAerolinea());
		if (ruta != null)
			ruta.addReserva(r);
	}

	public LinkedList<LinkedList<Ruta>> servicio2(String o, String d, String a) {
		Aeropuerto origen = grafo.getAeropuerto(o);
		Aeropuerto destino = grafo.getAeropuerto(d);
		LinkedList<LinkedList<Ruta>> servicio2 = new LinkedList<LinkedList<Ruta>>();
		if (origen != null && destino != null) {
			servicio2 = this.DFS(origen, destino, a);
		}
		return servicio2;
	}

	public LinkedList<Ruta> servicio3(String pais1, String pais2) {
		LinkedList<Ruta> salida = new LinkedList<Ruta>();
		ListIterator<Ruta> it = grafo.getRutas().listIterator();
		while (it.hasNext()) {
			Ruta tmp = it.next();
			if (tmp.getOrigen().getPais().equals(pais1) && tmp.getDestino().getPais().equals(pais2))
				salida.add(tmp);
		}
		return salida;
	}

	private LinkedList<LinkedList<Ruta>> DFS(Aeropuerto origen, Aeropuerto destino, String aerolinea) {
		for (Aeropuerto p : grafo.getAeropuertos())
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

	public LinkedList<Ruta> backtracking(String o) {
		Aeropuerto origen = grafo.getAeropuerto(o);
		if (origen!=null) {
		for (Aeropuerto a : grafo.getAeropuertos())
			a.setEstado(NO_VISITADO);
		LinkedList<Aeropuerto> u = new LinkedList<Aeropuerto>(grafo.getAeropuertos());
		origen.setEstado(VISITADO);
		u.remove(origen);
		LinkedList<Ruta> s = new LinkedList<Ruta>();
		back(u, origen, s, o);
		}
		return new LinkedList<Ruta>(this.solucionBack);
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

	private void back(LinkedList<Aeropuerto> u, Aeropuerto origen, LinkedList<Ruta> s, String o) {
		if (u.isEmpty()) {
			Aeropuerto destino = grafo.getAeropuerto(o);
			if (origen.getAdyacentes().contains(destino)) {
				Ruta r = grafo.getRuta(origen.getNombre(), destino.getNombre());
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
						back(u, origen, s, o);
					s.remove(r);
					origen.setEstado(NO_VISITADO);
					u.add(origen);
				}
			}
		}
	}

	public LinkedList<Ruta> greedy(String o) {
		Aeropuerto origen = grafo.getAeropuerto(o);
		LinkedList<Aeropuerto> universo = new LinkedList<Aeropuerto>(grafo.getAeropuertos());
		LinkedList<Ruta> solucion = new LinkedList<Ruta>();
		if (origen != null) {
			for (Aeropuerto a : universo)
				a.setEstado(NO_VISITADO);
			while (!universo.isEmpty() && origen != null) {
				origen.setEstado(VISITADO);
				Aeropuerto tmp = grafo.getAeropuertoCercano(origen);
				if (tmp != null) {
					Ruta r = grafo.getRuta(origen.getNombre(), tmp.getNombre());
					solucion.add(r);
				}
				universo.remove(origen);
				origen = tmp;
			}
			origen = grafo.getAeropuerto(o);
			if (origen.getAdyacentes().contains(solucion.getLast().getDestino()) && universo.isEmpty()) {
				Ruta r = grafo.getRuta(solucion.getLast().getDestino().getNombre(), origen.getNombre());
//				System.out.println(r.toString());
				solucion.add(r);
				return solucion;
			} else
				return null;
		} else
			return null;

	}

}
