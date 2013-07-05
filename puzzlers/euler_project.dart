import 'package:unittest/unittest.dart';

/**
 * Dart solutions to [project euler](http://projecteuler.net)
 */
main() {
  /** [Problem 1](http://projecteuler.net/problem=1) */
  test('Find the sum of all the multiples of 3 or 5 below 1000.', () {
    num sumOf(int x) => (x+1) * (x / 2);
    
    var sum = 3*sumOf(999 ~/ 3) + 5*sumOf(999 ~/ 5) - 15*sumOf(999 ~/ 15);
    expect(sum, equals(233168));    
  });
}

