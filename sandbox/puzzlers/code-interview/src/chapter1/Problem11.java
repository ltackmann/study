package chapter1;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by IntelliJ IDEA.
 * User: lt
 * Date: 5/11/11
 * Time: 6:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class Problem11 {
    // implement a algorithm to determine if a string has all unique characters.
    public static boolean hasDuplicates(String s) {
        Set<Character> chars = new HashSet<Character>();
        for (char c : s.toCharArray()) {
            Character cObj = Character.valueOf(c);
            if (chars.contains(cObj)) {
                return true;
            }
            chars.add(cObj);
        }
        return false;
    }

    // what if you cannot use any additional data structures

    public static void main(String[] args) {
        assert(!hasDuplicates("abcd"));
        assert(hasDuplicates("abcda"));
    }
}
