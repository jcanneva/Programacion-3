package Practico3;

import java.util.ArrayList;

public class Grafo {

	ArrayList<Arco> arcos;
	ArrayList<Vertice> vertices;
	boolean dirigido;
	
	final static String VISITADO="amarillo";
	final static String NO_VISITADO="blanco";
	final static String YA_VISITADO="negro";
	
	public Grafo() {
		this.arcos=new ArrayList<Arco>();
		this.vertices=new ArrayList<Vertice>();
	}
	
	public void addVertice(Vertice v) {
		if (!this.vertices.contains(v))
			this.vertices.add(v);
	}
	
}
