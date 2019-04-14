package Practico2;

import java.util.Iterator;

import Practico1.MySimpleLinkedList;


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
		Integer doce=12;
		Integer trece=13;
		TNode root= new TNode(ocho);
		BST bst= new BST(root);
		
		bst.insert(tres);
		bst.insert(trece);
		bst.insert(once);
		bst.insert(nueve);
		bst.insert(seis);
		bst.insert(cinco);
		bst.insert(siete);
		bst.insert(cuatro);
		bst.insert(dos);
		bst.insert(uno);
		bst.insert(diez);
		bst.insert(doce);
		
		System.out.println("order");
		bst.printInOrder();
		System.out.println();
		System.out.println("pre order");
		bst.printPreOrder();
		System.out.println();
		System.out.println("pos order");
		bst.printPosOrder();
		System.out.println();
		System.out.println();
		System.out.println("tres " + bst.hasElement(tres));
		System.out.println("once " + bst.hasElement(diez));
		System.out.println("Min "+bst.getMinElem());
		System.out.println("Max "+bst.getMaxElem());
		System.out.println("Altura "+bst.getHeight());
		System.out.println();
		System.out.println("elimina "+ bst.delete(trece));
		System.out.println();
		bst.printPreOrder();
		MySimpleLinkedList list1=new MySimpleLinkedList(); 
		MySimpleLinkedList list2=new MySimpleLinkedList();
		MySimpleLinkedList list3=new MySimpleLinkedList();
		list1=bst.getFrontera();
		Iterator<Object> itr= list1.iterator();
		System.out.println();
		System.out.println();
		System.out.println("Get frontera");
		while(itr.hasNext()) {
			System.out.println(itr.next());
		}
		System.out.println();
		System.out.println("Get longest branch");
		list2=bst.getLongestBranch();
		itr= list2.iterator();
		while(itr.hasNext()) {
			System.out.println(itr.next());
		}
		list3=bst.getElemAtLevel(cuatro);
		System.out.println();
		System.out.println("Get element at lvl 4");
		itr= list3.iterator();
		while(itr.hasNext()) {
			System.out.println(itr.next());
		}
	}
}
