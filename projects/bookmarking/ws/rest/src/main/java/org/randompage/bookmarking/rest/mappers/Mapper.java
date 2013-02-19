package org.randompage.bookmarking.rest.mappers;

/**
 * @author Lars Tackmann
 */
public interface Mapper<A, B> {
    B read(A a);

    A write(B b);
}
