package com.javapuzzlers.expressive.puzzle10;

public class Tweedledee {
    public static void main(String[] args) {
        // Put your declarations for x and i here
    	String x = "";
    	int i = 2;

        x = x + i;  // Must be LEGAL
        x += i;     // Must be ILLEGAL
    }
}
