package Practico1;

import java.util.Iterator;
import Practico1.MySimpleLinkedList.MyIterator;

public class Main {

	// Ejercicio 4
	public static void max(MySimpleLinkedList list) {
		Node tmp = list.getFirst();
		int pos = 0;
		Integer top = (Integer) tmp.getInfo();
		for (int i = 0; (i < list.size() && tmp != null); i++) {
			Integer x = (Integer) tmp.getInfo();
			if (x > top) {
				top = x;
				pos = i;
			}
			tmp = tmp.getNext();
		}
		System.out.println("mayor " + top);
		System.out.println("pos " + pos);
	}

	// Ejercicio 5.a
	public static MySimpleLinkedList getListOrder(MySimpleLinkedList l1, MySimpleLinkedList l2) {
		MySimpleLinkedList result = new MySimpleLinkedList();
		Iterator<Object> itr1 = l1.iterator();
		while (itr1.hasNext()) {
			int value = (int) itr1.next();
			Iterator<Object> itr2 = l2.iterator();
			while (itr2.hasNext()) {
				if ((int) itr2.next() == value) {
					result.insertOrder(value);
				}
			}
		}
		return result;
	}

	// Ejercicio 5.b
	public static MySimpleLinkedList getList(MySimpleLinkedList l1, MySimpleLinkedList l2) {
		MySimpleLinkedList result = new MySimpleLinkedList();
		MyIterator itr1 = l1.iterator();
		MyIterator itr2 = l2.iterator();
		int val1, val2;
		while (itr1.hasNext() && itr2.hasNext()) {
			val1 = (int) itr1.get();
			val2 = (int) itr2.get();
			if (val1 == val2) {
				result.insertLast(val1);
				itr1.next();
				itr2.next();
			} else if (val1 < val2)
				itr1.next();
			else
				itr2.next();
		}
		return result;
	}

	// Ejercicio 6
	public static MySimpleLinkedList getListDif(MySimpleLinkedList l1, MySimpleLinkedList l2) {
		MySimpleLinkedList result = new MySimpleLinkedList();
		Iterator<Object> itr = l1.iterator();
		while (itr.hasNext()) {
			int value = (int) itr.next();
			if (!l2.exist(value))
				result.insertFront(value);
		}
		return result;
	}

	public static void main(String[] args) {
		MySimpleLinkedList list1 = new MySimpleLinkedList();
		MySimpleLinkedList list2 = new MySimpleLinkedList();
		MySimpleLinkedList list3 = new MySimpleLinkedList();
		
		list1.insertFront(9);
		list1.insertFront(6);
		list1.insertFront(5);
		list1.insertFront(4);
		list1.insertFront(1);
		
		list2.insertFront(9);
		list2.insertFront(7);
		list2.insertFront(6);
		list2.insertFront(3);
		list2.insertFront(2);
		
		list1.print(list1.size());
		System.out.println();
		Iterator<Object> itr = list2.iterator();
		while (itr.hasNext()) {
			System.out.println(itr.next());
		}
		System.out.println();
		list3 = getListDif(list1, list2);
		list3.print(list3.size());
	}
}
