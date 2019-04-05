package Practico2;

public class Main {

	public static void main(String[] args) {
		Integer seis = 6;
		TNode root= new TNode(seis);
		root.setLeft(null);
		root.setRigth(null);
		ABB abb= new ABB(root);
		Integer tres=3;
		Integer cuatro = 4;
		Integer cinco=5;
		Integer siete = 7;
		Integer nueve = 9;
		Integer ocho = 8;
		abb.insert(cuatro);
		abb.insert(ocho);
		abb.insert(siete);
		abb.insert(nueve);
		abb.insert(tres);
		abb.insert(cinco);
		System.out.println("order");
		abb.printInOrder();
		System.out.println();
		System.out.println("pre order");
		abb.printPreOrder();
		System.out.println();
		System.out.println("pos order");
		abb.printPosOrder();
		System.out.println();
		Integer once=11;
		System.out.println("tres " + abb.hasElement(tres));
		System.out.println("once " + abb.hasElement(once));
		System.out.println("Min "+abb.getMinElem());
		System.out.println("Max "+abb.getMaxElem());
		System.out.println("Altura "+abb.getHeight());
	}
}
