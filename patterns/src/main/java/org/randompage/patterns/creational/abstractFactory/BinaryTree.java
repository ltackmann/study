package org.randompage.patterns.creational.abstractFactory;

import java.util.NoSuchElementException;

/**
 * @author Lars Tackmann
 * 
 * DynamicSet implemented as a binary tree i.e. a tree where if x is a node and
 * y is a node in x's left subtree then the value of y less than or equal to
 * that of x. If y on the other hand is a node in x's right subtree then its
 * value if greater than or equal to x.
 */
public class BinaryTree<T extends Comparable<T>> implements DynamicSet<T> {
	private class TreeIterator implements OrderedIterator<T> {
		private TreeNode cur;
		private boolean atEnd;
		private boolean atStart;

		public TreeIterator() {
			cur = minimum(root);
		}

		public boolean hasPredecessor() {
			return (atStart ? false : true);
		}

		public boolean hasSucessor() {
			return (atEnd ? false : true);
		}

		public T predecessor() throws NoSuchElementException {
			if (!hasPredecessor())
				throw new NoSuchElementException();
			T res = cur.value;
			if (cur == minimum(root))
				atStart = true;
			else
				cur = getPredecessor(cur);
			return res;
		}

		public T successor() throws NoSuchElementException {
			if (!hasSucessor())
				throw new NoSuchElementException();
			T res = cur.value;
			if (cur == maximum(root))
				atEnd = true;
			else
				cur = getSuccessor(cur);
			return res;
		}
	}

	private class TreeNode {
		private T value;
		private TreeNode leftChild;
		private TreeNode rightChild;
		private TreeNode parent;

		private TreeNode(T value) {
			this.value = value;
		}
	}

	private TreeNode root;

	public BinaryTree() {
		root = null;
	}

	private int countNodes(TreeNode node) {
		if (node == null)
			return 0;
		return (countNodes(node.leftChild) + countNodes(node.rightChild) + 1);
	}

	// deletion is one of three cases:
	//
	// 1) node has no children - remove by setting pointer pointer at
	// parent to null
	//
	// 2) node has one child - remove by linking parent of node to be
	// deleted with its grand child
	//
	// 3) node has two children - remove by replacing node for deletion
	// with its successor (including subtree).
	//
	// The last case takes advantage of the fact that a node with two
	// children always has a successor with no left child (otherwise it would
	// not be a successor)) and a predecessor with no right child
	public boolean delete(T value) {
		// locate node for deletion
		TreeNode z = searchFor(root, value);
		if (z == null)
			return false;

		// determine node to splice out
		TreeNode y;
		if (z.leftChild == null || z.rightChild == null)
			y = z;
		else
			y = getSuccessor(z);

		// get child (if any) of node to splice out
		TreeNode x = (y.leftChild != null) ? y.leftChild : y.rightChild;

		// if any child set its parent to parent of node to be spliced out
		if (x != null)
			x.parent = y.parent;

		// splice out node
		if (y.parent == null) {
			// set new root in case where z was root with zero or one child
			root = x;
		} else {
			if (y == y.parent.leftChild) {
				// we are a left child
				y.parent.leftChild = x;
			} else {
				y.parent.rightChild = x;
			}
		}

		// copy data over in the case where node that was spliced differers from
		// node containing value to be deleted
		if (y != z)
			z.value = y.value;
		return true;
	}

	// get predecessor to node, returns null if non exists
	private TreeNode getPredecessor(TreeNode x) {
		if (x.leftChild != null) {
			// left tree is non-empty so get its rightmost node
			return maximum(x.leftChild);
		}
		// left subtree is empty so climb up the tree to find the predecessor.
		// If predecessor exists then it is lowest ancestor of x whose right
		// child is also an ancestor of x
		TreeNode y = x.parent;
		while (y != null && x == y.leftChild) {
			// go up tree from x until we encounter a node that is the right
			// child of its parent
			x = y;
			y = y.parent;
		}
		return y;
	}

	// get successor to node, returns null if non exists
	private TreeNode getSuccessor(TreeNode x) {
		if (x.rightChild != null) {
			// right tree is non-empty so get its leftmost node
			return minimum(x.rightChild);
		}
		// right subtree is empty so climb up the tree to find the successor.
		// if successor exists then it is the lowest ancestor of x whose left
		// child is also an ancestor of x
		TreeNode y = x.parent;
		while (y != null && x == y.rightChild) {
			// go up tree from x until we encounter a node that is the left
			// child of its parent
			x = y;
			y = y.parent;
		}
		return y;
	}

	public boolean insert(T value) {
		// traverse tree to find insert point
		TreeNode y = null;
		TreeNode x = root;
		while (x != null) {
			y = x;
			if (value.compareTo(x.value) < 0)
				x = x.leftChild;
			else
				x = x.rightChild;
		}
		// y is parent for our new node
		TreeNode z = new TreeNode(value);
		z.parent = y;
		// insert node
		if (y == null) {
			root = z; // empty tree
		} else if (z.value.compareTo(y.value) < 0) {
			y.leftChild = z; // insert to the left if smaller
		} else {
			y.rightChild = z; // insert to the right if larger/equal
		}
		return true;
	}

	public boolean isEmpty() {
		return (root == null);
	}

	public OrderedIterator<T> iterator() {
		return new TreeIterator();
	}

	public T maximum() {
		if (isEmpty())
			throw new NoSuchElementException();
		return maximum(root).value;
	}

	private TreeNode maximum(TreeNode node) {
		while (node.rightChild != null)
			node = node.rightChild;
		return node;
	}

	public T minimum() {
		if (isEmpty())
			throw new NoSuchElementException();
		return minimum(root).value;
	}

	private TreeNode minimum(TreeNode node) {
		while (node.leftChild != null)
			node = node.leftChild;
		return node;
	}

	public boolean search(T value) {
		return (null != searchFor(root, value));
	}

	// recursively search through tree by taking advantage of the binary tree
	// structure
	private TreeNode searchFor(TreeNode x, T value) {
		if (x == null || x.value == value) {
			return x;
		} else if (value.compareTo(x.value) < 0) {
			// smaller values can only be in left subtree
			return searchFor(x.leftChild, value);
		} else {
			// larger keys can only be located in right subtree
			return searchFor(x.rightChild, value);
		}
	}

	public int size() {
		return countNodes(root);
	}

	@Override
	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append("[");
		OrderedIterator<T> iterator = iterator();
		while (iterator.hasSucessor()) {
			buf.append(iterator.successor().toString());
			buf.append(",");
		}
		// remove last comma and add angle bracket instead
		String str = buf.toString();
		str = str.substring(0, (str.length() - 1));
		str += "]";
		return str;
	}
}
