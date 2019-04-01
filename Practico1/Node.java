package Practico1;

public class Node {
	private Object info;
	private Node next;
	
	public Node() {
		info = null;
		next = null;
	}
	
	public Node(Object o, Node n) {
		setInfo(o);
		setNext(n);
	}
	
	public void setInfo(Object o) {
		info = o;
	}
	
	public void setNext(Node n) {
			next = n;
	}
	
	public Object getInfo() {
		return info;
	}
	
	public Node getNext() {
		return next;
	}
}