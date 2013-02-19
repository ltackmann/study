package org.randompage.patterns.tests.creational;

import static junit.framework.Assert.*;
import org.junit.Test;
import org.randompage.patterns.creational.abstractFactory.BinaryTree;
import org.randompage.patterns.creational.abstractFactory.DoubleLinkedList;
import org.randompage.patterns.creational.abstractFactory.OrderedIterator;
import org.randompage.patterns.creational.abstractFactory.DynamicSet;

import java.util.NoSuchElementException;

/**
 * @author Lars Tackmann
 *         Test abstract factory implementation
 */
public class AbstractFactoryTest {
    /**
     * Test othat demonstrates that two different concrete factory implementations can be tested using abstraction
     */
    @Test()
    public void abstractFactoryTest() {
        Integer[] ints = {-3, 56, 3, -22, -3, 95, 1, 0, 95};
        String[] strings = {"", "a", "short", "but", "interesting", "array"};
        // test doubled linked list
        DynamicSet<Integer> intListSet = new DoubleLinkedList<Integer>();
        setTest(intListSet, ints);
        DynamicSet<String> stringListSet = new DoubleLinkedList<String>();
        setTest(stringListSet, strings);
        // test binary search tree
        DynamicSet<Integer> intTreeSet = new BinaryTree<Integer>();
        setTest(intTreeSet, ints);
        DynamicSet<String> stringTreeSet = new BinaryTree<String>();
        setTest(stringTreeSet, strings);
    }

    private <T extends Comparable<T>> void setTest(DynamicSet<T> set, T[] array) {
        // initial state
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());

        // test operations that has no point on empty set
        boolean caughtException = false;
        T res = null;
        try {
            res = set.maximum();
        } catch (NoSuchElementException nsee) {
            caughtException = true;
        }
        assertNull(res);
        assertTrue(caughtException);
        caughtException = false;
        try {
            res = set.minimum();
        } catch (NoSuchElementException nsee) {
            caughtException = true;
        }
        assertNull(res);
        assertTrue(caughtException);

        // test inserts and order
        assertTrue(array.length > 0);
        for (int i = 0; i < array.length; i++) {
            T elm = array[i];
            assertTrue(set.insert(elm));
            assertTrue(set.search(elm));
            assertEquals((i + 1), set.size());
            // iterator test
            iterationTest(set);
        }

        // test deletion and order
        for (int j = (array.length - 1); j >= 0; j--) {
            T elm = array[j];
            assertEquals((j + 1), set.size());
            // delete element and assert it is gone
            assertTrue(set.delete(elm));
            assertEquals(j, set.size());
            // iterator test
            if (!set.isEmpty())
                iterationTest(set);
        }

        // final state
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
    }

    private <T extends Comparable<T>> void iterationTest(DynamicSet<T> set) {
        OrderedIterator<T> iterator = set.iterator();
        T elm = null;
        // iterate forward
        while (iterator.hasSucessor()) {
            T oldElm = elm;
            elm = iterator.successor();
            if (oldElm == null)
                continue; // no point in testing order at first element
            assertNotNull(oldElm);
            assertNotNull(elm);
            assertTrue("oldElm: " + oldElm.toString() + " elm: "
                    + elm.toString(), elm.compareTo(oldElm) >= 0);
        }
        assertEquals(elm, set.maximum());
        // we are at the end so we should get an exception if we try to go
        // further forward
        boolean caughtException = false;
        try {
            iterator.successor();
        } catch (NoSuchElementException nsee) {
            caughtException = true;
        }
        assertTrue(caughtException);
        // iterate backward
        while (iterator.hasPredecessor()) {
            T oldElm = elm;
            assertNotNull(oldElm);
            elm = iterator.predecessor();
            assertNotNull(elm);
            assertTrue("oldElm: " + oldElm.toString() + " elm: "
                    + elm.toString(), elm.compareTo(oldElm) <= 0);
        }
        assertEquals(elm, set.minimum());
        // we are at the beginning so we should get an exception if we try to go
        // further back
        caughtException = false;
        try {
            iterator.predecessor();
        } catch (NoSuchElementException nsee) {
            caughtException = true;
        }
        assertTrue(caughtException);
    }
}
