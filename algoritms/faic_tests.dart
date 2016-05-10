
import 'faic_natural.dart';
import 'faic_integer.dart';

import 'package:test/test.dart';

/**
 * Test number implementations
 */
main() {
  group("integers", () {
    var zero = new Integer.fromNatural(Natural.Zero);
    print("zero is $zero [0]");
    var one = new Integer.fromNatural(Natural.One);
    print("one is $one [01]");

    // asNatural
    print("asNatural of 0 is ${zero.asNatural}");
    print("asNatural of 1 is ${one.asNatural}");

    // negate
    print("negation of 0 is ${-zero}");
    var minusOne = -one;
    print("negation of 1 is ${minusOne}");

    // addition
    var two = one + one;
    print("addition of 1 + 1 is $two [010]");
    var three  = two + one;
    print("addition of 2 + 1 is $three [011]");
    var five = three + two;
    print("addition of 3 + 2 is $five [0101]");
    var twentyThree = five + five + five + five + three;
    print("addition of 5 + 5 + 5 + 5 + 3 is $twentyThree [010111]");

    // increment
    print("increment of 1 is ${one.increment}");
    print("increment of -1 is ${minusOne.increment}");

    // subtract
    print("subtraction 3 - 2 is ${three - two} [01]");
    print("subtraction 2 - 3 is ${two - three} [01]");

    // decrement
    print("decrement of 1 is ${one.decrement} [0]");
    print("decrement of zero is ${zero.decrement} [0]");

    // multiply
    print("multiplication of 2*3*5 is ${two * three * five} [011110]");

    // power
    print("power of 2^3 is ${two^three} [01000]");
    print("power of 3^2 is ${three^two} [01000]");

    // <
    print("comparison [-1 < 1] is ${minusOne < one}");
    print("comparison [1 < 1] is ${one < one}");
    print("comparison [2 < 3] is ${two < three}");
    print("comparison [2 < 1] is ${two < one}");
    print("comparison [2 < null] is ${two < null}");

    // >
    print("comparison [-1 > 1] is ${minusOne > one}");
    print("comparison [1 > 1] is ${one > one}");
    print("comparison [2 > 3] is ${two > three}");
    print("comparison [2 > 1] is ${two > one}");
    print("comparison [2 > null] is ${two > null}");

    // <=
    print("comparison [-1 <= 1] is ${minusOne <= one}");
    print("comparison [1 <= 1] is ${one <= one}");
    print("comparison [2 <= 3] is ${two <= three}");
    print("comparison [2 <= 1] is ${two <= one}");
    print("comparison [2 <= null] is ${two <= null}");

    // >=
    print("comparison [-1 >= 1] is ${minusOne >= one}");
    print("comparison [1 >= 1] is ${one >= one}");
    print("comparison [2 >= 3] is ${two >= three}");
    print("comparison [2 >= 1] is ${two >= one}");
    print("comparison [2 >= null] is ${two >= null}");

    //==
    print("comparison [-1 == 1] is ${minusOne == one}");
    print("comparison [1 == 1] is ${one == one}");
    print("comparison [2 == 3] is ${two == three}");
    print("comparison [2 == 1] is ${two == one}");
    print("comparison [2 == null] is ${two == null}");

    // compareTo
    print("comparison [1 compareTo 1] is ${one.compareTo(one)}");
    print("comparison [2 compareTo 3] is ${two.compareTo(three)}");
    print("comparison [2 compareTo 1] is ${two.compareTo(one)}");
    print("comparison [2 compareTo null] is ${two.compareTo(null)}");

    // hashCode
    print("hashCode [zero] is ${zero.hashCode}");
    print("hashCode [one] is ${one.hashCode}");

    // asInt
    print("asInteger [minusOne] is ${minusOne.asInt}");
    print("asInteger [two] is ${two.asInt}");
    print("asInteger [three] is ${three.asInt}");
    print("asInteger [five] is ${five.asInt}");

    // quotient
    var seven = five + two;
    var ten = five + five;
    var eleven = ten + one;
    print("quotient [11/7=1] is ${(eleven/seven).asInt}");
    print("quotient [-11/7=-1] is ${((-eleven)/seven).asInt}");
    print("quotient [11/-7=-1] is ${(eleven/(-seven)).asInt}");
    print("quotient [-11/-7=1] is ${((-eleven)/(-seven)).asInt}");
    print("quotient [3/10=0] is ${(three/ten).asInt}");
    print("quotient [3/-10=0] is ${(three/-ten).asInt}");
    print("quotient [-3/10=0] is ${(-three/ten).asInt}");
    print("quotient [-3/-10=0] is ${(-three/-ten).asInt}");
    print("quotient [10/3=3] is ${(ten/three).asInt}");
    print("quotient [-10/3=-3] is ${(-ten/three).asInt}");
    print("quotient [10/-3=-3] is ${(ten/-three).asInt}");
    print("quotient [-10/-3=3] is ${(-ten/-three).asInt}");
    print("quotient [5/3=1] is ${(five/three).asInt}");
    print("quotient [-5/3=-1] is ${(-five/three).asInt}");
    print("quotient [5/-3=-1] is ${(five/-three).asInt}");
    print("quotient [-5/-3=1] is ${(-five/-three).asInt}");

    // remainder
    print("remainder [11%7=4] is ${(eleven%seven).asInt}");
    print("remainder [-11%7=-4] is ${((-eleven)%seven).asInt}");
    print("remainder [11%-7=4] is ${(eleven%(-seven)).asInt}");
    print("remainder [-11%-7=-4] is ${((-eleven)%(-seven)).asInt}");
    print("remainder [3%10=3] is ${(three%ten).asInt}");
    print("remainder [3%-10=3] is ${(three%-ten).asInt}");
    print("remainder [-3%10=-3] is ${(-three%ten).asInt}");
    print("remainder [-3%-10=-3] is ${(-three%-ten).asInt}");
    print("remainder [10%3=1] is ${(ten%three).asInt}");
    print("remainder [-10%3=-1] is ${(-ten%three).asInt}");
    print("remainder [10%-3=1] is ${(ten%-three).asInt}");
    print("remainder [-10%-3=-1] is ${(-ten%-three).asInt}");
    print("remainder [5%3=2] is ${(five%three).asInt}");
    print("remainder [-5%3=-2] is ${(-five%three).asInt}");
    print("remainder [5%-3=2] is ${(five%-three).asInt}");
    print("remainder [-5%-3=-2] is ${(-five%-three).asInt}");
  }, skip:false);

  group("natural numbers", () {
    var zero = Natural.Zero;
    print("zero is $zero [0]");
    var one = Natural.One;
    print("one is $one [01]");

    // addition
    var two = one + one;
    print("addition of 1 + 1 is $two [010]");
    var three  = two + one;
    print("addition of 2 + 1 is $three [011]");
    var five = three + two;
    print("addition of 3 + 2 is $five [0101]");
    var twentyThree = five + five + five + five + three;
    print("addition of 5 + 5 + 5 + 5 + 3 is $twentyThree [010111]");

    // left shift
    print("left shift of $one is ${one << 1}");
    var eight = one << 3;
    print("left shift of $one 3 times is ${eight}");
    print("left shift of $two is ${two << 1}");
    print("left shift of $three is ${three << 1}");
    print("left shift of $five is ${five << 1}");
    print("left shift of $twentyThree is ${twentyThree << 1}");

    // right shift
    print("right shift of $one is ${one >> 1}");
    print("right shift of $eight 3 times is ${eight >> 3}");
    print("right shift of $two is ${two >> 1}");
    print("right shift of $three is ${three >> 1}");
    print("right shift of $five is ${five >> 1}");
    print("right shift of $twentyThree is ${twentyThree >> 1}");

    // increment
    print("increment of 1 is ${one.increment}");

    // multiply
    print("multiplication of 2*3*5 is ${two * three * five} [011110]");

    // power
    print("power of 2^3 is ${two^three} [01000]");
    print("power of 3^2 is ${three^two} [01001]");

    // subtract
    print("subtraction 3 - 2 is ${three - two} [01]");

    // decrement
    print("decrement of 1 is ${one.decrement} [0]");

    // <
    print("comparison [1 < 1] is ${one < one}");
    print("comparison [2 < 3] is ${two < three}");
    print("comparison [2 < 1] is ${two < one}");
    print("comparison [2 < null] is ${two < null}");

    // >
    print("comparison [1 > 1] is ${one > one}");
    print("comparison [2 > 3] is ${two > three}");
    print("comparison [2 > 1] is ${two > one}");
    print("comparison [2 > null] is ${two > null}");

    // <=
    print("comparison [1 <= 1] is ${one <= one}");
    print("comparison [2 <= 3] is ${two <= three}");
    print("comparison [2 <= 1] is ${two <= one}");
    print("comparison [2 <= null] is ${two <= null}");

    // >=
    print("comparison [1 >= 1] is ${one >= one}");
    print("comparison [2 >= 3] is ${two >= three}");
    print("comparison [2 >= 1] is ${two >= one}");
    print("comparison [2 >= null] is ${two >= null}");

    //==
    print("comparison [1 == 1] is ${one == one}");
    print("comparison [2 == 3] is ${two == three}");
    print("comparison [2 == 1] is ${two == one}");
    print("comparison [2 == null] is ${two == null}");

    // compareTo
    print("comparison [1 compareTo 1] is ${one.compareTo(one)}");
    print("comparison [2 compareTo 3] is ${two.compareTo(three)}");
    print("comparison [2 compareTo 1] is ${two.compareTo(one)}");
    print("comparison [2 compareTo null] is ${two.compareTo(null)}");

    // hashCode
    print("hashCode [zero] is ${zero.hashCode}");
    print("hashCode [one] is ${one.hashCode}");

    // asInt
    print("asInteger [two] is ${two.asInt}");
    print("asInteger [three] is ${three.asInt}");
    print("asInteger [five] is ${five.asInt}");

    // divison
    print("division [2/1 = 2] is ${two / one}");
    print("division [3/2 = 1] is ${three / two}");
    print("division [5/2 = 2] is ${five / two}");
    print("division [23/2 = 11] is ${twentyThree / two}");
    print("division [23/3 = 7] is ${twentyThree / three}");
    print("division [23/5 = 4] is ${twentyThree / five}");

    // remainder
    print("remainder [2%1 = 0] is ${two % one}");
    print("remainder [3%2 = 1] is ${three % two}");
    print("remainder [5%2 = 1] is ${five % two}");
    print("remainder [23%2 = 1] is ${twentyThree % two}");
    print("remainder [23%3 = 2] is ${twentyThree % three}");
    print("remainder [23%5 = 3] is ${twentyThree % five}");
  }, skip:true);
}
