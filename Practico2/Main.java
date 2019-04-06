package Practico2;

public class Main {

	public static void main(String[] args) {
		Integer uno=1;
		Integer dos=2;
		Integer tres=3;
		Integer cuatro = 4;
		Integer cinco=5;
		Integer seis = 6;
		Integer siete = 7;
		Integer ocho = 8;
		Integer nueve = 9;
		Integer diez=10;
		Integer once=11;

		TNode root= new TNode(seis);
		ABB abb= new ABB(root);
		
		abb.insert(cuatro);
		abb.insert(ocho);
		abb.insert(siete);
		abb.insert(nueve);
		abb.insert(tres);
		abb.insert(dos);
		abb.insert(uno);
		abb.insert(cinco);
		abb.insert(diez);
		
		System.out.println("order");
		abb.printInOrder();
		System.out.println();
		System.out.println("pre order");
		abb.printPreOrder();
		System.out.println();
		System.out.println("pos order");
		abb.printPosOrder();
		System.out.println();
		System.out.println("tres " + abb.hasElement(tres));
		System.out.println("once " + abb.hasElement(once));
		System.out.println("Min "+abb.getMinElem());
		System.out.println("Max "+abb.getMaxElem());
		System.out.println("Altura "+abb.getHeight());
		System.out.println();
		System.out.println("elimina "+ abb.delete(dos));
		System.out.println();
		abb.printInOrder();
	}
}
