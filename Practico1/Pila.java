package Practico1;

public class Pila {

	private MySimpleLinkedList lista;
	
	public Pila() {
		lista = new MySimpleLinkedList();
	}
	
	public void push(Object o) {
		//agrega elemento
		lista.insertFront(o);
	}
	
	public Object pop() {
		//retorna el ultimo elemento de la pila y lo elimina
		return lista.extractFront();	
	}
	
	public Object top() {
		//devuelve el primer elemento
		return lista.get(0);
	}
	
	public MySimpleLinkedList reverse() {
		//invierte el orden de la pila
		MySimpleLinkedList aux= new MySimpleLinkedList();
		for (int i=0; i<lista.size();i++) {
			aux.insertFront(lista.extractFront());
		}
		return aux;	
	}
	
	
}
