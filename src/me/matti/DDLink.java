package me.matti;

import java.util.ArrayList;

public class DDLink {

	private static ArrayList<Booking> bDump = new ArrayList<Booking>();
	
	private class Node {
		
		private Booking data;
		private Node last;
		private Node next;
		
		public Node(Booking data) {
			this.data = data;
			this.last = null;
			this.next = null;
		}

		public void print() {
			this.data.print();
			if (this.next != null) {
				this.next.print();
			}
		}
		
		public boolean del(int id) {
			if (this.data.getId() == id) { // ========
				if (this.last != null) {
					if (this.next != null) {
						this.last.setNext(this.next);
						this.next.setLast(this.last);
						return true;
					}
					this.last.setNext(null);
					return true;
				}
				if (this.next != null) {
					this.next.setLast(null);
					return true;
				}
			}
			if (this.next != null) {
				return this.next.del(id);
			}
			return false;
		}
		
		public Booking find(int id) {
			if (this.data.getId() == id) {
				return this.data;
			}
			else if (this.next != null) {
				return this.getNext().find(id);
			}
			return null;
		}
		
		public boolean find(Train train, Route route) {
			if (data.getTrain() == train && data.getRoute() == route) {
				return true;
			}
			else if (next == null) {
				return false;
			}
			return next.find(train, route);
		}
		
		public void dump() {
			bDump.add(data);
			if (next == null) return;
			next.dump();
		}
		
		public boolean sort() {
			if (next == null) {
				return true;
			}
			else if (data.getId() > next.data.getId()) {
				Booking tmp = data;
				data = next.data;
				next.data = tmp;
				return false;
			}
			return next.sort();
		}
		
		public Node getLast() {
			return last;
		}
		
		public void setLast(Node last) {
			this.last = last;
		}

		public Node getNext() {
			return next;
		}

		public void setNext(Node next) {
			this.next = next;
		}

		public Booking getData() {
			return data;
		}

		public void setData(Booking data) {
			this.data = data;
		}
		
	}

	private Node head;
	private Node tail;
	
	public DDLink() {
		this.head = null;
		this.tail = null;
	}
	
	public boolean add(int id, Train train, Route route) {
		if (this.find(id) != null) return false;
		Node node = new Node(new Booking(id, train, route));
		if (this.head == null) {
			this.head = node;
			this.tail = this.head;
			return true;
		}
		node.setLast(this.tail);
		this.tail.setNext(node);
		this.tail = node;
		return true;
	}
	
	public boolean del(int id) {
		if (this.head == this.tail) {
			if (this.head.getData().getId() == id) {
				this.head = null;
				this.tail = null;
				return true;
			}
			return false;
		}
		else if (this.head.getData().getId() == id) {
			this.head = this.head.getNext();
			this.head.setLast(null);
			return true;
		}
		else if (this.tail.getData().getId() == id) {
			this.tail = this.tail.getLast();
			this.tail.setNext(null);
			return true;
		}
		return this.head.del(id);
	}
	
	public Booking find(int id) {
		if (this.head == null) {
			return null;
		}
		return this.head.find(id);
	}
	
	public boolean find(Train train, Route route) {
		if (this.head == null) {
			return false;
		}
		return head.find(train, route);
	}
	
	public boolean sort() {
		if (head == null) {
			return false;
		}
		if (!head.sort()) {
			sort();
		}
		return true;
	}
	
	public void print() {
		if (this.head == null) {
			System.out.println("Booking is empty");
			return;
		}
		this.head.print();
	}
	
	public ArrayList<Booking> dump() {
		if (this.head == null) {
			return null;
		}
		head.dump();
		return bDump;
	}
}
