package org.randompage.patterns.creational.abstractFactory;

import java.util.NoSuchElementException;

/**
 * @author Lars Tackmann
 * 
 * Iterate a dynamic set in order
 */
public interface OrderedIterator<T extends Comparable<T>> {
	/**
	 * @return true if iteration has more smaller elements
	 */
	boolean hasPredecessor();

	/**
	 * @return true if iteration has more larger elements
	 */
	boolean hasSucessor();

	/**
	 * @return next smaller element
	 * @throws NoSuchElementException
	 *             if already at smallest element
	 */
	T predecessor() throws NoSuchElementException;

	/**
	 * @return next larger element
	 * @throws NoSuchElementException
	 *             if already at largest element
	 */
	T successor() throws NoSuchElementException;
}
