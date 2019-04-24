package Practico3;

import java.util.ArrayList;

public class Grafo {

	ArrayList<Arco> arcos;
	ArrayList<Vertice> vertices;
	boolean dirigido;
	
	final static String VISITADO="amarillo";
	final static String NO_VISITADO="blanco";
	final static String TERMINADO="negro";
	
	public Grafo() {
		this.arcos=new ArrayList<Arco>();
		this.vertices=new ArrayList<Vertice>();
	}
	
	public void addVertice(Vertice v) {
		if (!this.vertices.contains(v))
			this.vertices.add(v);
	}
	
	public void DFS() {
		for (Vertice v : this.vertices) {
			v.setEstado(NO_VISITADO);
		} 
		for (Vertice v : this.vertices) {
			if(v.getEstado()==NO_VISITADO) 
				DFS_visitar(v);
		} 
	}

	private void DFS_visitar(Vertice v) {
		v.setEstado(VISITADO);
		ArrayList<Vertice> ady = v.getAdyacentes();
		for (Vertice tmp : ady) {
			if(tmp.getEstado()==NO_VISITADO) 
				DFS_visitar(tmp);
			//si quiero saber si hay ciclos
			//else if (tmp.getEstado()==VISITADO)
			//		return true;
		}
		v.setEstado(TERMINADO);
	}
	
	public void BFS() {
		
	}
	
}
