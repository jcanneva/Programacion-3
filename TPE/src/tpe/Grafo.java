package tpe;

import java.util.LinkedList;
import java.util.ListIterator;

public class Grafo {

	LinkedList<Aeropuerto> aeropuertos;
	LinkedList<Ruta> rutas;
	LinkedList<Reserva> reservas;
	
	final static String VISITADO="amarillo";
	final static String NO_VISITADO="blanco";
	final static String TERMINADO="negro";
	
	public Grafo() {
		this.aeropuertos=new LinkedList<Aeropuerto>();
		this.rutas=new LinkedList<Ruta>();
		this.reservas=new LinkedList<Reserva>();
	}
	
	public LinkedList<Aeropuerto> getAeropuertos() {
		return new LinkedList<Aeropuerto>(this.aeropuertos);
	}
	
	public LinkedList<Ruta> getRutas() {
		return new LinkedList<Ruta>(rutas);
	}

	public void setRutas(LinkedList<Ruta> rutas) {
		this.rutas = rutas;
	}

	public void setAeropuertos(LinkedList<Aeropuerto> aeropuertos) {
		this.aeropuertos = aeropuertos;
	}

	public LinkedList<Reserva> getReservas() {
		return new LinkedList<Reserva>(reservas);
	}
	
	public void addReserva(Reserva r) {
		this.reservas.add(r);
		Ruta ruta = this.getRuta(r.getOrigen(), r.getDestino(), r.getAerolinea());
		if(ruta!=null)
			ruta.addReserva(r);
	}

	public void addRuta(Ruta origen) {
		this.rutas.add(origen);
		Aeropuerto o = getAeropuerto(origen.getOrigen().getNombre());
		if (o!=null) {
			o.addRuta(origen);
			Ruta destino = new Ruta(origen.getDestino(),origen.getOrigen(),origen.getDistancia(),origen.getCabotaje());
			destino.setAerolineas(origen.getAerolineas());
			this.rutas.add(destino);
			Aeropuerto d = getAeropuerto(origen.getDestino().getNombre());
			d.addRuta(destino);
		}
	}

	public void addAeropuerto(Aeropuerto p) {
		if (!this.aeropuertos.contains(p))
			this.aeropuertos.add(p);
	}
	
	public Aeropuerto getAeropuerto(String n) {
		ListIterator<Aeropuerto> itr = this.aeropuertos.listIterator();
		while(itr.hasNext()) {
			Aeropuerto a =itr.next();
			if(a.getNombre().equals(n))
				return a;		
		}
		return null;
	}
	
	public Ruta getRuta(String origen, String destino ) {
		ListIterator<Ruta> itr = this.rutas.listIterator();
		while(itr.hasNext()) {
			Ruta a =itr.next();
			if(a.getOrigen().getNombre().equals(origen)&&a.getDestino().getNombre().equals(destino))
				return a;		
		}
		return null;
	}
	
	public Ruta getRuta(String origen, String destino, String aerolinea) {
		ListIterator<Ruta> itr = this.rutas.listIterator();
		while(itr.hasNext()) {
			Ruta a =itr.next();
			if(a.getOrigen().getNombre().equals(origen)&&a.getDestino().getNombre().equals(destino)
					&& a.getAerolineas().containsKey(aerolinea))
				return a;		
		}
		return null;
	}
	
	public Reserva getReserva(Ruta r) {
		ListIterator<Reserva> itr =this.reservas.listIterator();
		while (itr.hasNext()) {
			Reserva re = itr.next();
			if(re.getOrigen().equals(r.getOrigen().getNombre())&&re.getDestino().equals(r.getDestino().getNombre())
					&&r.getAerolineas().containsKey(re.getAerolinea()))
				return re;
		}
		return null;
	}
	
	public LinkedList<Ruta> DFS_recorrer(Aeropuerto origen, Aeropuerto destino) {
		for (Aeropuerto p : this.aeropuertos) 
			p.setEstado(NO_VISITADO);
		LinkedList<Ruta> rutas = new LinkedList<Ruta>();
		return (DFS_visitar(origen, destino,rutas));
		} 
	
	private LinkedList<Ruta> DFS_visitar( Aeropuerto origen, Aeropuerto destino, LinkedList<Ruta> rutas) {
		origen.setEstado(VISITADO);
		LinkedList<Aeropuerto> ady = origen.getAdyacentes();
		Ruta ruta_actual = this.getRuta(origen.getNombre(), destino.getNombre());
		if (ady.contains(destino)&&!rutas.contains(ruta_actual)) {
			rutas.add(ruta_actual);
		}
		for (Aeropuerto tmp : ady) {
			if(tmp.getEstado()==NO_VISITADO) { 
				DFS_visitar(tmp,destino,rutas);
				Ruta ruta = this.getRuta(origen.getNombre(),tmp.getNombre());
				if (ruta!=null&&!rutas.contains(ruta)&&!origen.equals(destino))
					rutas.add(ruta);
			}
		}
		origen.setEstado(NO_VISITADO);
		return rutas;
	}
	
}
