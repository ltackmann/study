import "package:unittest/unittest.dart";

/// Examples of various usage of Dart collections
main() {
  group("collection methods -", () {
    test("add", () {
      var list = [2];
      expect(list..add(3), orderedEquals([2,3]), reason:"must add to the end of collection");
    });
    
    test("chain", () {
      var list = [1,2,3,4,5,6,7,8,9];
      var squaredEven = list.where((a) => a % 2 == 0).toList().map((x) => x*x);
      expect(squaredEven, orderedEquals([4, 16, 36, 64]));
    });
    
    test("filter", () {
      var comp = (a) => a == 2;
      expect([1].firstWhere(comp, orElse:() => null), isNull);
      expect([1,2].firstWhere(comp, orElse:() => null), equals(2));
      expect([1,3,4].firstWhere(comp, orElse:() => null), isNull);
      expect([1,2,3,2,1].firstWhere(comp, orElse:() => null), equals(2));
    });
    
    test("reduce", () {
      var concat = (String i,String j) => i + j;
      expect([].fold("0", concat), "0"); 
      expect(["1"].fold("0", concat), "01"); 
      expect(["1","2"].fold("0", concat), "012"); 
    });
    
    test("sort and reverse", () {
      var myList = ["a", "abcd", "ab", "abc"];
      expect((myList..sort()).reversed, orderedEquals(["abcd", "abc", "ab", "a"]));
    });
  });
}

