package Practico2;

import Practico1.MySimpleLinkedList;

public class ABB {

	private TNode root;
	
	public ABB(TNode n) {
		this.root = n;	
	}
	
	public boolean isEmpty() {
		return isNull(this.root);
	}
	
	private boolean isNull(TNode root) {
		return root==null;
	}
	
	private boolean match(TNode root, Object o) {
		return (getValue(root) ==  castInt(o));
	}
	
	private int getValue(TNode root) {
		return (int)root.getInfo();
	}
	
	private int castInt(Object o) {
		return (int)o;
	}
	
	public Object getRoot() {
		if (isEmpty())
			return null;
		else
			return getValue(this.root);
	}

	public boolean hasElement(Object o) {
		return hasElement(this.root,o);
	}
	
	private boolean hasElement(TNode root, Object o) {
		// Si es null llego a la hoja, no lo encontro y retorna false
		// si coincide retorno true
		// si es menor busca en el subarbol izquierdo y si es mayor en el derecho
	
		if (isNull(root)) 
			return false;
		else {	
			if (match(root,o))
				return true;
			else if (getValue(root) > castInt(o))
				return hasElement(root.getLeft(), o);
			else
				return hasElement(root.getRigth(), o);
		}
	}

	public void insert(Object o) {
		if (isEmpty()) {
			this.root = new TNode(o);
		} else
			insert(this.root, o);
	}

	private void insert(TNode root, Object o) {
		if (!match(root,o)) {	
			if (getValue(root)>castInt(o)) {
			//va a la izquierda
				if (isNull(root.getLeft()))
				//si es null lo agrega
					root.setLeft(new TNode(o));
				else
				//sino busca el lugar	
					insert(root.getLeft(),o);
			}
			else {
			//derecha same izquierda
				if (isNull(root.getRigth()))
					root.setRigth(new TNode(o));
				else	
					insert(root.getRigth(),o);	
			}
		}
		else {
			//si el valor es igual se puede hacer una lista con los valores repetidos
		}
	}

	public int getHeight() {
		return getHeight(this.root);
	}
	
	private int getHeight(TNode root) {
		if (isNull(root)) {
			return 0;
		}
		int left = getHeight(root.getLeft());
		int rigth = getHeight(root.getRigth());
		if (left>rigth)
			return left+1;
		else return rigth+1;
	}
	
	public Object getMaxElem() {
		return getMaxElem(this.root);
	}
	
	private Object getMaxElem(TNode root) {
		if (!isNull(root)) {
			if (!isNull(root.getRigth())) 
				return getMaxElem(root.getRigth());	
			else return getValue(root);
		}
		else return null;
	}
	
	public Object getMinElem() {
		return getMinElem(this.root);
	}
	
	private Object getMinElem(TNode root) {
		if (!isNull(root)) {
			if (!isNull(root.getLeft())) 
				return getMinElem(root.getLeft());	
			else return getValue(root);
		}
		else return null;
	}
	
	public boolean delete(Object o) {
		return delete(o,this.root);
	}
	
	private boolean delete(Object o, TNode root) {
		
		if (hasElement(root,o)) {
			if (match(root,o)) {
				if (isHoja(root)) {
					root=null;
				}
				else  { 
					//es un nodo interno
					
				}
				return true;
			}
			else if (getValue(root)>castInt(o)) 
					return delete(o,root.getLeft());
			else 
				return delete(o,root.getRigth());	
		}	
			else return false;
		
		
		//Suprimir (x, A)
		//1. Buscar la clave X
		//2. Si X es una hoja, suprimir X
		//3. Si no // o sea está borrando un nodo interno!!
		//a) Reemplace con el NMI del subárbol derecho
		//b) Suprimir (NMI, subárbol derecho)
		//	3 casos:
		//	1) El nodo es una hoja
	 	//	Borrar sin más trámite.
		//	2) El nodo tiene un hijo solamente
		// 	Acomodar el puntero para ignorar el nodo borrado y alcanzar el hijo.
		//	3) El nodo tiene sus 2 hijos
		//	Reemplazar con el NMI del subárbol derecho
		// 	Borrar el NMI del subárbol derecho
		
	}
	
	private boolean isHoja(TNode root) {
		return isNull(root.getLeft())&&isNull(root.getRigth());
	}

	public void printPreOrder() {
		printPreOrder(this.root);
	}

	private void printPreOrder(TNode root) {
		if (isNull(root))
			return;
		System.out.println(getValue(root));
		printPreOrder(root.getLeft());
		printPreOrder(root.getRigth());
	}

	public void printPosOrder() {
		printPosOrder(this.root);
	}

	private void printPosOrder(TNode root) {
		if (isNull(root))
			return;
		printPosOrder(root.getLeft());
		printPosOrder(root.getRigth());
		System.out.println(getValue(root));
	}

	public void printInOrder() {
		printInOrder(this.root);
	}

	private void printInOrder(TNode root) {
		if (isNull(root))
			return;
		printInOrder(root.getLeft());
		System.out.println(getValue(root));
		printInOrder(root.getRigth());
	}

	public MySimpleLinkedList getLongestBranch() {
		return null;
	}

	public MySimpleLinkedList getFrontera() {
		return null;
	}

	public MySimpleLinkedList getElemAtLevel(int i) {
		return null;
	}
}
