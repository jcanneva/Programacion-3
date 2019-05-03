package tpe;

import java.util.Iterator;
import java.util.LinkedList;

public class Aeropuerto {

	LinkedList<Ruta> rutas;
	String estado;
	String nombre;
	String pais;
	String ciudad;
	
	public Aeropuerto(String nombre, String pais, String ciudad) {
		this.setEstado("blanco");
		this.rutas=new LinkedList<Ruta>();
		this.nombre=nombre;
		this.pais=pais;
		this.ciudad=ciudad;
	}

	public void setEstado(String e) {
		this.estado=e;
	}

	public String getEstado() {
		return this.estado;
	}
	
	public void setNombre(String e) {
		this.estado=e;
	}

	public String getNombre() {
		return this.nombre;
	}
	
	public void addRuta(Ruta r) {
		this.rutas.add(r);
	}
	
	public LinkedList<Aeropuerto> getAdyacentes(){
		LinkedList<Aeropuerto> adyacentes = new LinkedList<Aeropuerto>();
		Iterator<Ruta> itr = this.rutas.iterator();
		while (itr.hasNext()) 
			adyacentes.add(itr.next().getDestino());
		return adyacentes;
	}
	
	public String toString() {
		return "1)Nombre: " + nombre + " 2)Pais: " + pais + " 3)Ciudad: " + ciudad;
	}
}
