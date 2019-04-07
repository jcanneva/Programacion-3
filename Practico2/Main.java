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
		BST bst= new BST(root);
		
		bst.insert(cuatro);
		bst.insert(nueve);
		bst.insert(diez);
		bst.insert(ocho);
		bst.insert(siete);
		bst.insert(tres);
		bst.insert(dos);
		bst.insert(uno);
		bst.insert(cinco);
		bst.insert(once);

		
		System.out.println("order");
		bst.printInOrder();
		System.out.println();
		System.out.println("pre order");
		bst.printPreOrder();
		System.out.println();
		System.out.println("pos order");
		bst.printPosOrder();
		System.out.println();
		System.out.println("tres " + bst.hasElement(tres));
		System.out.println("once " + bst.hasElement(once));
		System.out.println("Min "+bst.getMinElem());
		System.out.println("Max "+bst.getMaxElem());
		System.out.println("Altura "+bst.getHeight());
		System.out.println();
		System.out.println("elimina "+ bst.delete(diez));
		System.out.println();
		bst.printInOrder();
	}
}
