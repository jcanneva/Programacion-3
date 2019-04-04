package Practico2;

import Practico1.MySimpleLinkedList;

public class ArbolBinarioBusqueda {

	private TNode root;

	public ArbolBinarioBusqueda(TNode n) {
		this.root = n;
	}

	public Object getRoot() {
		// pasar el Object o el nodo?
		return this.root.getInfo();
	}

	public boolean hasElement(Object o) {
		return hasElement(this.root,o);
	}
	
	private boolean hasElement(TNode root, Object o) {
		// Si es null retorna false
		// si coincide retorno true
		// si es menor busca en el subárbol izquierdo y si es mayor en el derecho.
		
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

	public boolean isEmpty() {
		return this.root == null;
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
			//va a la izq
				if (root.getLeft()==null)
				//si es null lo agrega
					root.setLeft(new TNode(o));
				else
				//sino busca el lugar	
					insert(root.getLeft(),o);
			}
			else {
			//va a la der
				if (root.getRigth()==null)
					root.setRigth(new TNode(o));
				else	
					insert(root.getRigth(),o);	
			}
		}
	}

	public int getHigh() {
		return 0;
	}

	public boolean delete(Object o) {
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
		printPosOrder(root.getRigth());
		printPosOrder(root.getLeft());
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

	public Object getMaxElem(TNode root) {
		if (root.getRigth() == null) {
			getMaxElem(root.getRigth());
			return root.getInfo();
		}
		return null;
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
