package Practico2;

import Practico1.MySimpleLinkedList;

public class BST {

	private TNode root;
	
	public BST(TNode n) {
		this.root = n;	
	}
	
	public boolean isEmpty() {
		return isNull(this.root);
	}
	
	private boolean isNull(TNode root) {
		return root==null;
	}
	
	private boolean match(TNode root, Object o) {
		return (root.getInfo().equals(o));
	}
	
	private int getValueInt(TNode root) {
		return (int)root.getInfo();
	}
	
	private int castInt(Object o) {
		return (int)o;
	}
	
	public void setRoot(TNode root) {
		this.root=root;
	}
	
	public Object getRoot() {
		if (isEmpty())
			return null;
		else
			return getValueInt(this.root);
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
			else if (getValueInt(root) > castInt(o))
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
			if (getValueInt(root)>castInt(o)) {
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
			else return getValueInt(root);
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
			else return getValueInt(root);
		}
		else return null;
	}
	
	public boolean delete(Object o) {
	
		if (hasElement(o))
			 return delete(o,this.root,null);
		else 
			return false;
	}
	
	private boolean delete(Object o, TNode root, TNode parent) {	
		if (match(root,o)) {
			if (isChildLess(root)) { //es hoja==sin hijos
				if (isNull(parent)) //sino tiene padres==raiz
					setRoot(null);
				else if (isLeft(root,parent))
						parent.setLeft(null);
					else parent.setRigth(null);
			}
			else  if (isFull(root)){ //dos hijos: reemplazar con el NMI del subarbol derecho
					if (isNull(parent)) {//caso raiz
						if (!isNull(root.getRigth().getLeft())) {
							TNode tmp=root;
							tmp=getPointer(tmp);
							if(!isNull(tmp.getLeft())) {
								TNode aux=tmp.getLeft();
								tmp.setLeft(null);
								aux.setLeft(root.getLeft());
								aux.setRigth(root.getRigth());
								setRoot(aux);
							}
							else {
								tmp.setLeft(root.getLeft());
								setRoot(tmp);
							}
						}
						else {
							root.getRigth().setLeft(root.getLeft());
							setRoot(root.getRigth());
						}
					}//si no es el caso raiz
					else {
						if (!isNull(root.getRigth().getLeft())) {
							TNode tmp=root;
							tmp=getPointer(tmp);
							if(!isNull(tmp.getLeft())) {
								TNode aux=tmp.getLeft();
								tmp.setLeft(null);
								if (!isLeft(root,parent)) {
									parent.setRigth(aux);
									aux.setRigth(root.getRigth());
									aux.setLeft(root.getLeft());
								}
								else {
									parent.setLeft(aux);
									aux.setLeft(root.getLeft());
									aux.setRigth(root.getRigth());
								}
							}
							else {
								if (!isLeft(root,parent)) {
									parent.setRigth(tmp);
									tmp.setLeft(root.getLeft());
								}
								else {
									parent.setLeft(tmp);
									tmp.setRigth(root.getRigth());
								}
							}
						}
						else {
							if (!isLeft(root,parent)) {
								parent.setRigth(root.getRigth());
								root.getRigth().setLeft(root.getLeft());
							}
							else {
								parent.setLeft(root.getLeft());
								root.getLeft().setRigth(root.getRigth());
							}
						}
					}
				}
				else { //un hijo : acomodar el puntero para ignorar el nodo borrado y alcanzar el hijo
					if (isNull(parent)) {//caso raiz
						if (existLeft(root))
							setRoot(root.getLeft());
						else 
							setRoot(root.getRigth());
					}
					else if (isLeft(root,parent)){
							if (existLeft(root))
								parent.setLeft(root.getLeft());
							else
								parent.setLeft(root.getRigth());
					}
						else {
							if (existLeft(root))
								parent.setRigth(root.getLeft());
							else
								parent.setRigth(root.getRigth());
							}
					}
			return true;
		}
		else if (getValueInt(root)>castInt(o)) 
				return delete(o,root.getLeft(),root);
		else return delete(o,root.getRigth(),root);
	}
	
	private TNode getPointer(TNode root) {
		//devuelve el puntero del padre del nodo mas izquierdo del subarbol derecho 
		if(!isNull(root.getRigth())) {
			root=root.getRigth();
			while(!isNull(root.getLeft().getLeft())) {
				root=root.getLeft();
			}
			return root;
		}
		else return root;
	}
	
	private boolean existLeft(TNode root) {
		return (!isNull(root.getLeft()));
	}
	
	private boolean isLeft(TNode root, TNode father) {
		return (existLeft(father)&& match(father.getLeft(),root.getInfo()));
	}
	
	private boolean isFull(TNode root) {
		return (!isNull(root.getLeft())&&!isNull(root.getRigth()));
	}
	
	private boolean isChildLess(TNode root) {
		return (isNull(root.getLeft())&&isNull(root.getRigth()));
	}

	public void printPreOrder() {
		printPreOrder(this.root);
	}

	private void printPreOrder(TNode root) {
		if (isNull(root))
			return;
		System.out.println(getValueInt(root));
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
		System.out.println(getValueInt(root));
	}

	public void printInOrder() {
		printInOrder(this.root);
	}

	private void printInOrder(TNode root) {
		if (isNull(root))
			return;
		printInOrder(root.getLeft());
		System.out.println(getValueInt(root));
		printInOrder(root.getRigth());
	}

	public MySimpleLinkedList getLongestBranch() {
		MySimpleLinkedList list=new MySimpleLinkedList();
		return getLongestBranch(this.root, list);
	}

	private MySimpleLinkedList getLongestBranch(TNode root, MySimpleLinkedList list ) {
		MySimpleLinkedList tmp= new MySimpleLinkedList();
		TNode  left=root;
		TNode rigth=root;
		while(left!=null){
			list.insertFront(left.getInfo());
			left=left.getLeft();
		}
		while (rigth!=null) {
			tmp.insertFront(rigth.getRigth());
			rigth= rigth.getRigth();
		}
		if (list.size()>= tmp.size())
				return list;
		else
			return tmp;
	}
	
	public MySimpleLinkedList getFrontera() {
		MySimpleLinkedList list = new  MySimpleLinkedList();
		this.getFrontera(this.root, list);
		return list;
	}
	
	private void getFrontera(TNode root, MySimpleLinkedList list) {
		//corta con el 
		if (isNull(root)){
			return ;
		}
		if (isChildLess(root)) {
			list.insertFront(root.getInfo());
			return;
		}
		if (!isNull(root.getLeft()))
			getFrontera(root.getLeft(),list);
		if (!isNull(root.getRigth()))
			getFrontera(root.getRigth(),list); 
	}
	
	public MySimpleLinkedList getElemAtLevel(int i) {
		MySimpleLinkedList list= new MySimpleLinkedList();
		int lvl=1;
		if (i<=this.getHeight())
			getElemAtLevel(this.root,i,list,lvl);
		return list;
	}
	
	private void getElemAtLevel(TNode root,int i, MySimpleLinkedList list, int lvl) {
		if (isNull(root)) {
			return;
		}
		if (lvl==i) {
			list.insertFront(root.getInfo());
			return;
		}
		 if (!isNull(root.getLeft())) {
			 lvl++;
			 getElemAtLevel(root.getLeft(), i, list, lvl);
			}
		 if (!isNull(root.getRigth())) {
			getElemAtLevel(root.getRigth(),i,list,lvl);
		 }
	}
}
