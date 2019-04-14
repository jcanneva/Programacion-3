package Practico3;

public class Arco {

	Vertice origen; 
	Vertice destino; 
	
	public Arco (Vertice o, Vertice d) {
		this.setOrigen(o);
		this.setDestino(d);
	}
	
	public void setOrigen(Vertice v) {
		this.origen=v;
	}
	
	public void setDestino(Vertice v) {
		this.destino=v;
	}
	
	public Vertice getOrigen() {
		return this.origen;
	}
	
	public Vertice getDestino() {
		return this.destino;
	}
}
