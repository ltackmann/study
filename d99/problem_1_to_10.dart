import 'dart:collection';

import 'package:unittest/unittest.dart';

// http://aperiodic.net/phil/scala/s-99/
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
  
  test('P10: Run-length encoding of a list -', (){
    List encode(List list) => new List.from( pack(list).map((l) => [l.length, l.first]) );
    expect(
        encode(['a', 'a', 'a', 'a', 'b', 'c', 'c', 'a', 'a', 'd', 'e', 'e', 'e', 'e']).toString(),
        equals('[[4, a], [1, b], [2, c], [2, a], [1, d], [4, e]]'));
  });
}

