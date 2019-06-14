package tpe;

import java.util.Iterator;
import java.util.LinkedList;

public class Aeropuerto {

	private LinkedList<Ruta> rutas;
	private String estado;
	private String nombre;
	private String pais;
	private String ciudad;
	private static final String NO_VISITADO="blanco";
	private static final String SALIDA="1)Nombre: ";
	
	public Aeropuerto(String nombre, String pais, String ciudad) {
		this.estado = NO_VISITADO;
		this.rutas=new LinkedList<Ruta>();
		this.nombre=nombre;
		this.pais=pais;
		this.ciudad=ciudad;
	}
	
	public LinkedList<Ruta> getRutas(){
		return new LinkedList<Ruta>(this.rutas);
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

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public LinkedList<Aeropuerto> getAdyacentes(){
		LinkedList<Aeropuerto> adyacentes = new LinkedList<Aeropuerto>();
		Iterator<Ruta> itr = this.rutas.iterator();
		while (itr.hasNext()) 
			adyacentes.add(itr.next().getDestino());
		return adyacentes;
	}
	
	public String imprimir() {
		return SALIDA + nombre ;
	}
}
