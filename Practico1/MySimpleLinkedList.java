package Practico1;

import java.util.Iterator;

public class MySimpleLinkedList implements Iterable<Object> {

	private Node first;
	private int cant;

	public MySimpleLinkedList() {
		first = null;
		cant = 0;
	}

	protected class MyIterator implements Iterator<Object> {

		private Node tmp;

		public MyIterator(Node f) {
			this.tmp = f;
		}

		@Override
		public boolean hasNext() {
			return (this.tmp != null);
		}

		@Override
		public Object next() {
			Node aux = tmp;
			tmp = tmp.getNext();
			return aux.getInfo();
		}

		public Object get() {
			return tmp.getInfo();
		}
	}

	public Node getFirst(){
		return new Node(this.first.getInfo(),this.first.getNext());
	}
	
	public boolean exist(int aux, MySimpleLinkedList list) {
		Iterator<Object> itr = list.iterator();
		while (itr.hasNext()) {
			if ((int) itr.next() == aux)
				return true;
		}
		return false;
	}
	
	@Override
	public MyIterator iterator() {
		return new MyIterator(first);
	}

	public void insertFront(Object o) {
		Node tmp = new Node(o, null);
		tmp.setNext(first);
		first = tmp;
		cant++;
	}

	public Object extractFront() {
		Node tmp = first;
		first = first.getNext();
		return tmp.getInfo();
	}

	public void print(int n) {
		if (!isEmpty() && (n <= this.size())) {
			Node tmp = first;
			for (int i = 0; i < n; i++) {
				if (tmp != null) {
					System.out.println(tmp.getInfo());
					tmp = tmp.getNext();
				}
			}
		}
	}

	public void insertLast(Object o) {
		Node last = new Node(o, null);
		if (this.isEmpty()) {
			this.first=last;
			this.cant++;
		} else {
			Node tmp = this.first;
			while (tmp.getNext() != null) {
				tmp.getNext();
			}
			tmp.setNext(last);
			this.cant++;
		}
	}

	public boolean isEmpty() {
		return (this.size() == 0);
	}

	public int size() {
		return this.cant;
	}

	public Object get(int index) {
		Node tmp = this.first;
		Object j = null;
		for (int i = 0; i < index; i++)
			tmp = tmp.getNext();
		j = tmp.getInfo();
		return j;
	}

	public void insertOrder(Object o) {
		if (first == null)
			this.insertFront(o);
		else if ((int) this.first.getInfo() > (int) o)
			this.insertFront(o);
		else {
			Node index = first;
			while ((index.getNext() != null) && (index.getInfo() != o) && (index.getNext().getInfo() != o)) {
				if ((int) index.getNext().getInfo() > (int) o) {
					Node tmp = new Node(o, index.getNext());
					index.setNext(tmp);
					this.cant++;
				}
				index = index.getNext();
			}
			if (index.getNext() == null && index.getInfo() != o) {
				Node tmp = new Node(o, null);
				index.setNext(tmp);
				this.cant++;
			}
		}
	}
}
