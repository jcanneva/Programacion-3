package Practico2;

import Practico1.MySimpleLinkedList;

public class ABB {

	private TNode root;
	
	public ABB(TNode n) {
		this.root = n;
		
	}

	public boolean isEmpty() {
		return this.root == null;
	}
	
	public Object getRoot() {
		if (isEmpty())
			return null;
		else
			return this.root.getInfo();
	}

	public boolean hasElement(Object o) {
		return hasElement(this.root,o);
	}
	
	private boolean hasElement(TNode root, Object o) {
		// Si es null o llega a la hoja entonces no lo encontro y retorna false
		// si coincide retorno true
		// si es menor busca en el subarbol izquierdo y si es mayor en el derecho
		
		if (root == null) 
			return false;
		else {	
			if ((int) root.getInfo() == (int) o)
				return true;
			else if ((int) root.getInfo() > (int) o)
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
		if ((int)this.root.getInfo()!=(int)o) {	
			if ((int)root.getInfo()>(int)o) {
			//va a la izquierda
				if (root.getLeft()==null)
				//si es null lo agrega
					root.setLeft(new TNode(o));
				else
				//sino busca el lugar	
					insert(root.getLeft(),o);
			}
			else {
			//derecha same izquierda
				if (root.getRigth()==null)
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
		if (root==null) {
			return 0;
		}
		int left = getHeight(root.getLeft());
		int rigth = getHeight(root.getRigth());
		if (left>rigth)
			return left+1;
		else return rigth+1;
		//mas uno por la raiz
	}
	
	public Object getMaxElem() {
		return getMaxElem(this.root);
	}
	
	private Object getMaxElem(TNode root) {
			if (root.getRigth() != null) 
				return getMaxElem(root.getRigth());	
	
			else return root.getInfo();
	}
	
	public Object getMinElem() {
		return getMinElem(this.root);
	}
	
	private Object getMinElem(TNode root) {
		if (root.getLeft() != null) 
			return getMinElem(root.getLeft());	
		else return root.getInfo();
	}
	
	public boolean delete(Object o) {
		//	3 casos:
		//	1) El nodo es una hoja
	 	//	Borrar sin más trámite.
		//	2) El nodo tiene un hijo solamente
		// 	Acomodar el puntero para ignorar el nodo borrado y alcanzar el hijo.
		//	3) El nodo tiene sus 2 hijos
		//	Reemplazar con el NMI del subárbol derecho
		// 	Borrar el NMI del subárbol izquierdo
		return false;
	}
	
	public void printPreOrder() {
		printPreOrder(this.root);
	}

	private void printPreOrder(TNode root) {
		if (root == null)
			return;
		System.out.println((int) root.getInfo());
		printPreOrder(root.getLeft());
		printPreOrder(root.getRigth());
	}

	public void printPosOrder() {
		printPosOrder(this.root);
	}

	private void printPosOrder(TNode root) {
		if (root == null)
			return;
		printPosOrder(root.getLeft());
		printPosOrder(root.getRigth());
		System.out.println((int) root.getInfo());
	}

	public void printInOrder() {
		printInOrder(this.root);
	}

	private void printInOrder(TNode root) {
		if (root == null)
			return;
		printInOrder(root.getLeft());
		System.out.println((int) root.getInfo());
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
