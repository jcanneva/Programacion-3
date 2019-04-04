package Practico2;

public class Main {

	public static void main(String[] args) {
		Integer siete = 7;
		TNode root= new TNode(siete);
		root.setLeft(null);
		root.setRigth(null);
		ArbolBinarioBusqueda abb= new ArbolBinarioBusqueda(root);
		Integer cuatro = 4;
		Integer nueve = 9;
		Integer dos = 2;
		Integer once=11;
		Integer cinco=5;
		abb.insert(siete);
		abb.insert(nueve);
		abb.insert(dos);
		abb.insert(once);
		abb.insert(cuatro);
		abb.insert(cinco);
		abb.printInOrder();
		System.out.println();
		Integer tres=3;
		System.out.println("tres " + abb.hasElement(tres));
		
		
		
	}
}
