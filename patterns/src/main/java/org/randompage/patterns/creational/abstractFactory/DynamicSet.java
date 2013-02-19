package org.randompage.patterns.creational.abstractFactory;

import java.util.NoSuchElementException;

/**
 * @author Lars Tackmann
 * 
 * Ordered set that can grow and shrink dynamically over time
 */
public interface DynamicSet<T extends Comparable<T>> {
	/**
	 * @param value
	 * @return true id value was deleted
	 */
	boolean delete(T value);

	/**
	 * insert value
	 * 
	 * @param value
	 * @return true if value was added
	 */
	boolean insert(T value);

	/**
	 * @return true if set is empty
	 */
	boolean isEmpty();

	/**
	 * @return iterator for current set
	 */
	OrderedIterator<T> iterator();

	/**
	 * @return largest element
	 * @throws NoSuchElementException
	 *             if set is empty
	 */
	T maximum() throws NoSuchElementException;

	/**
	 * @return smallest element
	 * @throws NoSuchElementException
	 *             if set is empty
	 */
	T minimum() throws NoSuchElementException;

	/**
	 * @param value
	 * @return true if value exists
	 */
	boolean search(T value);

	/**
	 * @return size of set (number og elements)
	 */
	int size();
}
