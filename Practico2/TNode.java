package Practico2;

public class TNode {

	private TNode left,rigth;
	private Object info;
	
	public TNode(Object o) {
		setInfo(o);
	}
	
	public void setInfo(Object o) {
		this.info=o;
		this.rigth=null;
		this.left=null;
	}
	
	public void setRigth(TNode n) {
		this.rigth=n;
	}
	
	public void setLeft(TNode n) {
		this.left=n;
	}
	
	public Object getInfo() {
		return info;
	}
	
	public TNode getLeft() {
		return this.left;
	}
	
	public TNode getRigth() {
		return this.rigth;
	}
}
