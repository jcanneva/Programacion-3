package Practico3;

import java.util.ArrayList;
import java.util.Iterator;

public class Vertice {

	ArrayList<Arco> arcos;
	String estado;
	
	public Vertice() {
		this.setEstado("blanco");
		this.arcos=new ArrayList<Arco>();
	}
	
	public void setEstado(String e) {
		this.estado=e;
	}

	public String getEstado() {
		return this.estado;
	}
	
	public void addArco(Vertice v, boolean dirigido) {
		Arco p = new Arco(this,v);
		this.arcos.add(p);
		if (!dirigido) {
			p.setOrigen(v);
			p.setDestino(this);
			v.arcos.add(p);
		}
	}
	
	public ArrayList<Vertice> getAdyacentes(){
		ArrayList<Vertice> adyacentes = new ArrayList<Vertice>();
		Iterator<Arco> itr = this.arcos.iterator();
		while (itr.hasNext()) 
			adyacentes.add(itr.next().getDestino());
		return adyacentes;
	}
	
}
