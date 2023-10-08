package javapuzzlers.expressive.puzzle9;

public class Tweedledum {
    public static void main(String[] args) {
        // Put your declarations for x and i here
    	int x = 0;
    	int i = 2;

        x += i;     // Must be LEGAL
        x = x + i;  // Must be ILLEGAL
    }
}
