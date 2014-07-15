package com.javapuzzlers.expressive.puzzle3;

public class LongDivision {
    public static long divide() {
        final long MICROS_PER_DAY = 24 * 60 * 60 * 1000 * 1000;
        final long MILLIS_PER_DAY = 24 * 60 * 60 * 1000;

        return (MICROS_PER_DAY / MILLIS_PER_DAY);
    }
    
    public static long fixedDivide() {
        final long MICROS_PER_DAY = 24L * 60 * 60 * 1000 * 1000;
        final long MILLIS_PER_DAY = 24L * 60 * 60 * 1000;

        return (MICROS_PER_DAY / MILLIS_PER_DAY);
    }
}
