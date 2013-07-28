import 'dart:collection';

import 'package:unittest/unittest.dart';

/** Dart implementation of the [99 scala problems](http://aperiodic.net/phil/scala/s-99/) */
main() {
  test('P01: Find the last element of a list', () {
    dynamic last(List list) => list.last;
    expect(last([1,1,2,3,5,8]), equals(8));
  });
  
  test('P02: Find the last but one element of a list', () {
    dynamic penultimate(List list) {
      final length = list.length;
      return (length < 2) ? throw 'to short list' : list[length - 2];
    }
    expect(penultimate([1,1,2,3,5,8]), equals(5));
  });
  
  test('P03: Find the Kth element of a list', () {
    dynamic kth(int index, List list) => list[index]; 
    expect(kth(2, [1,1,2,3,5,8]), equals(2));
  });
  
  test('P04: Find the number of elements of a list', (){
    int length(List list) => list.length;
    expect(length([1,1,2,3,5,8]), equals(6));
  });
  
  test('P05: Reverse a list', () {
    List reverse(List list) => new List.from(list.reversed);
    expect(reverse([1, 1, 2, 3, 5, 8]), orderedEquals([8, 5, 3, 2, 1, 1]));
  });
  
  test('P06: Find out whether a list is a palindrome', () { 
    bool isPalindrome(List list) => list.toString() == new List.from(list.reversed).toString();
    expect(isPalindrome([1, 2, 3, 2, 1]), isTrue);
  });
  
  test('P07: Flatten a nested list structure', () {
    List flatten(List list) => list.fold([], (p,n) => (n is List) ? (p..addAll(flatten(n))) : (p..add(n)));
    expect(flatten([[1, 1], 2, [3, [5, 8]]]), orderedEquals([1, 1, 2, 3, 5, 8]));
  });
  
  test('P08: Eliminate consecutive duplicates of list elements', () {
    List compress(List list) => list.fold([], (p,n) => (p.isEmpty || p.last != n) ? (p..add(n)) : p);
    expect(
        compress(['a', 'a', 'a', 'a', 'b', 'c', 'c', 'a', 'a', 'd', 'e', 'e', 'e', 'e']), 
        orderedEquals(['a', 'b', 'c', 'a', 'd', 'e']));
  });
  
  List<List> pack(List list) => list.fold([], (p,n) => (p.isEmpty || p.last.last != n) ? (p..add([n])) : (p..last.add(n)));
  test('P09: Pack consecutive duplicates of list elements into sublists', () {
    expect(
        pack(['a', 'a', 'a', 'a', 'b', 'c', 'c', 'a', 'a', 'd', 'e', 'e', 'e', 'e']).toString(), 
        equals('[[a, a, a, a], [b], [c, c], [a, a], [d], [e, e, e, e]]')); 
  });
  
  test('P10: Run-length encoding of a list', (){
    List encode(List list) => new List.from( pack(list).map((l) => [l.length, l.first]) );
    expect(
        encode(['a', 'a', 'a', 'a', 'b', 'c', 'c', 'a', 'a', 'd', 'e', 'e', 'e', 'e']).toString(),
        equals('[[4, a], [1, b], [2, c], [2, a], [1, d], [4, e]]'));
  });
  
  test('P11: Run-length encoding of consecutive duplicates', () {
    List encodeModified(List list) => new List.from( pack(list).map((l) => (l.length > 1) ? [l.length, l.first] : l.first) );
    expect(
        encodeModified(['a', 'a', 'a', 'a', 'b', 'c', 'c', 'a', 'a', 'd', 'e', 'e', 'e', 'e']).toString(),
        equals('[[4, a], b, [2, c], [2, a], d, [4, e]]'));
  });
  
  test('P12: Decode a run-length encoded list', () {
    List decode(List<List> list) => list.map((l) => new List.filled(l.first, l.last)).fold([], (p, n) => p..addAll(n));
    expect(
        decode([[4, 'a'], [1, 'b'], [2, 'c'], [2, 'a'], [1, 'd'], [4, 'e']]),
        orderedEquals(['a', 'a', 'a', 'a', 'b', 'c', 'c', 'a', 'a', 'd', 'e', 'e', 'e', 'e']));
  });
  
  test('P13: Run-length encoding of a list (direct solution)', () {
    /*
    Implement the so-called run-length encoding data compression method directly. I.e. don't use other methods you've written (like P09's pack); do all the work directly.
    Example:
    scala> encodeDirect(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))
    res0: List[(Int, Symbol)] = List((4,'a), (1,'b), (2,'c), (2,'a), (1,'d), (4,'e))
     */
  });
  
  test('P14: Duplicate the elements of a list', () {
    List duplicate(List list) => list.fold([], (p,n) => p.isEmpty ? (p..add(n)) : (p..addAll([p.last, n])))..add(list.last);
    expect(duplicate(['a', 'b', 'c', 'c', 'd']), orderedEquals(['a', 'a', 'b', 'b', 'c', 'c', 'c', 'c', 'd', 'd']));
  });
  
  test('P15: Duplicate the elements of a list a given number of times', () {
    /*
    scala> duplicateN(3, List('a, 'b, 'c, 'c, 'd))
    res0: List[Symbol] = List('a, 'a, 'a, 'b, 'b, 'b, 'c, 'c, 'c, 'c, 'c, 'c, 'd, 'd, 'd)
     */
  });
  
  test('P16: Drop every Nth element from a list', () {
    /*
    scala> drop(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))
    res0: List[Symbol] = List('a, 'b, 'd, 'e, 'g, 'h, 'j, 'k)
     */
  });
  
  test('P17: Split a list into two parts', () {
    /*
    The length of the first part is given. Use a Tuple for your result.
    scala> split(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))
    res0: (List[Symbol], List[Symbol]) = (List('a, 'b, 'c),List('d, 'e, 'f, 'g, 'h, 'i, 'j, 'k))
     */
  });
  
  test('P18: Extract a slice from a list', () {
    /*
      Given two indices, I and K, the slice is the list containing the elements from and including the Ith element up to but not including the Kth element of the original list. Start counting the elements with 0.
      Example:
      
      scala> slice(3, 7, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))
      res0: List[Symbol] = List('d, 'e, 'f, 'g)
     */
  });
  
  test('P19: Rotate a list N places to the left', () {
    /*
    scala> rotate(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))
    res0: List[Symbol] = List('d, 'e, 'f, 'g, 'h, 'i, 'j, 'k, 'a, 'b, 'c)
    
    scala> rotate(-2, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))
    res1: List[Symbol] = List('j, 'k, 'a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i)
     */
  });
  
  test('P20: Remove the Kth element from a list', () {
    /*
      Return the list and the removed element in a Tuple. Elements are numbered from 0.
      scala> removeAt(1, List('a, 'b, 'c, 'd))
      res0: (List[Symbol], Symbol) = (List('a, 'c, 'd),'b)
     */
  });
  
  test('P21: Insert an element at a given position into a list', () {
    /*
      scala> insertAt('new, 1, List('a, 'b, 'c, 'd))
      res0: List[Symbol] = List('a, 'new, 'b, 'c, 'd)
     */
  });
  
  test('P22: Create a list containing all integers within a given range', () {
    /*
      scala> range(4, 9)
      res0: List[Int] = List(4, 5, 6, 7, 8, 9)
     */
  });
  
  test('P23: Extract a given number of randomly selected elements from a list', () {
    /*
      scala> randomSelect(3, List('a, 'b, 'c, 'd, 'f, 'g, 'h))
      res0: List[Symbol] = List('e, 'd, 'a)
      Hint: Use the solution to problem P20
     */
  });
  
  test('P24: Lotto: Draw N different random numbers from the set 1..M', () {
    /*
      scala> lotto(6, 49)
      res0: List[Int] = List(23, 1, 17, 33, 21, 37)
     */
  });
  
  test('P25: Generate a random permutation of the elements of a list', () {
    /*
      Hint: Use the solution of problem P23.
      Example:
      
      scala> randomPermute(List('a, 'b, 'c, 'd, 'e, 'f))
      res0: List[Symbol] = List('b, 'a, 'd, 'c, 'e, 'f)
     */
  });
  
  test('P26: Generate the combinations of K distinct objects chosen from the N elements of a list', () {
    /*
      In how many ways can a committee of 3 be chosen from a group of 12 people? We all know that there are C(12,3) = 220 possibilities (C(N,K) denotes the well-known binomial coefficient). For pure mathematicians, this result may be great. But we want to really generate all the possibilities.
      Example:
      
      scala> combinations(3, List('a, 'b, 'c, 'd, 'e, 'f))
      res0: List[List[Symbol]] = List(List('a, 'b, 'c), List('a, 'b, 'd), List('a, 'b, 'e), ...
     */
  });
  
  test('P27: Group the elements of a set into disjoint subsets', () {
    /*
      a) In how many ways can a group of 9 people work in 3 disjoint subgroups of 2, 3 and 4 persons? Write a function that generates all the possibilities.
      Example:
      
      scala> group3(List("Aldo", "Beat", "Carla", "David", "Evi", "Flip", "Gary", "Hugo", "Ida"))
      res0: List[List[List[String]]] = List(List(List(Aldo, Beat), List(Carla, David, Evi), List(Flip, Gary, Hugo, Ida)), ...
      b) Generalize the above predicate in a way that we can specify a list of group sizes and the predicate will return a list of groups.
      
      Example:
      
      scala> group(List(2, 2, 5), List("Aldo", "Beat", "Carla", "David", "Evi", "Flip", "Gary", "Hugo", "Ida"))
      res0: List[List[List[String]]] = List(List(List(Aldo, Beat), List(Carla, David), List(Evi, Flip, Gary, Hugo, Ida)), ...
      Note that we do not want permutations of the group members; i.e. ((Aldo, Beat), ...) is the same solution as ((Beat, Aldo), ...). However, we make a difference between ((Aldo, Beat), (Carla, David), ...) and ((Carla, David), (Aldo, Beat), ...).
      
      You may find more about this combinatorial problem in a good book on discrete mathematics under the term "multinomial coefficients".
     */
  });
  
  test('P28: Sorting a list of lists according to length of sublists', () {
    /*
      a) We suppose that a list contains elements that are lists themselves. The objective is to sort the elements of the list according to their length. E.g. short lists first, longer lists later, or vice versa.
      Example:
      
      scala> lsort(List(List('a, 'b, 'c), List('d, 'e), List('f, 'g, 'h), List('d, 'e), List('i, 'j, 'k, 'l), List('m, 'n), List('o)))
      res0: List[List[Symbol]] = List(List('o), List('d, 'e), List('d, 'e), List('m, 'n), List('a, 'b, 'c), List('f, 'g, 'h), List('i, 'j, 'k, 'l))
      b) Again, we suppose that a list contains elements that are lists themselves. But this time the objective is to sort the elements according to their length frequency; i.e. in the default, sorting is done ascendingly, lists with rare lengths are placed, others with a more frequent length come later.
      
      Example:
      
      scala> lsortFreq(List(List('a, 'b, 'c), List('d, 'e), List('f, 'g, 'h), List('d, 'e), List('i, 'j, 'k, 'l), List('m, 'n), List('o)))
      res1: List[List[Symbol]] = List(List('i, 'j, 'k, 'l), List('o), List('a, 'b, 'c), List('f, 'g, 'h), List('d, 'e), List('d, 'e), List('m, 'n))
      Note that in the above example, the first two lists in the result have length 4 and 1 and both lengths appear just once. The third and fourth lists have length 3 and there are two list of this length. Finally, the last three lists have length 2. This is the most frequent length.
     */
  });
}

