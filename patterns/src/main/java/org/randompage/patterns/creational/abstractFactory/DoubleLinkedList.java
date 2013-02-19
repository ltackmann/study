package org.randompage.patterns.creational.abstractFactory;

import java.util.NoSuchElementException;

/**
 * @author Lars Tackmann
 * 
 * DynamicSet implemented as a doubled Linked list
 */
public class DoubleLinkedList<T extends Comparable<T>> implements DynamicSet<T> {
	private class ListIterator implements OrderedIterator<T> {
		private Node cur;

		public ListIterator() {
			cur = head;
		}

		public boolean hasPredecessor() {
			return (cur.previous != head);
		}

		public boolean hasSucessor() {
			return (cur.next != head);
		}

		public T predecessor() throws NoSuchElementException {
			if (!hasPredecessor())
				throw new NoSuchElementException();
			cur = cur.previous;
			return cur.value;
		}

		public T successor() throws NoSuchElementException {
			if (!hasSucessor())
				throw new NoSuchElementException();
			cur = cur.next;
			return cur.value;
		}
	}

	private class Node {
		private Node previous;
		private Node next;
		private T value;

		public Node(T value) {
			this.value = value;
		}
	}

	private int count;

	private Node head;

	public DoubleLinkedList() {
		count = 0;
		// insert dummy head node
		head = new Node(null);
		head.previous = head;
		head.next = head;
	}

	public boolean delete(T value) {
		Node cur = searchFor(value);
		if (cur == head)
			return false;
		// delete the node to which our cur points
		(cur.previous).next = cur.next;
		(cur.next).previous = cur.previous;
		// decrement count
		count--;
		return true;
	}

	public boolean insert(T value) {
		Node newNode = new Node(value);
		// search through list for first node with larger value
		Node cur = head.next;
		while (cur != head && cur.value.compareTo(value) < 0)
			cur = cur.next;
		// insert node prior to the node pointed at by cur.next
		newNode.next = cur;
		newNode.previous = cur.previous;
		cur.previous = newNode;
		newNode.previous.next = newNode;
		// increment count
		count++;
		return true;
	}

	public boolean isEmpty() {
		// if head points to itself then we have a ampty list
		return (head.previous == head && head.next == head);
	}

	public OrderedIterator<T> iterator() {
		return new ListIterator();
	}

	public T maximum() {
		if (isEmpty())
			throw new NoSuchElementException();
		// head always has its previous set to the last (largest) element
		return head.previous.value;
	}

	public T minimum() {
		if (isEmpty())
			throw new NoSuchElementException();
		// head always has its next set to the first (smallest) element
		return head.next.value;
	}

	public boolean search(T value) {
		return (head != searchFor(value));
	}

	// return node that contains value or head if not there
	private Node searchFor(T value) {
		Node cur = head.next;
		while (cur != head && cur.value != value)
			cur = cur.next;
		return cur;
	}

	public int size() {
		return count;
	}
}
