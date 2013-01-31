import "package:unittest/unittest.dart";

main() {
  group("collections -", () {
    test("reduce", () {
      var concat = (String i,String j) => i.concat(j);
      expect([].reduce("0", concat), "0"); 
      expect(["1"].reduce("0", concat), "01"); 
      expect(["1","2"].reduce("0", concat), "012"); 
    });
    
    test("add", () {
      var list = [2];
      expect(list..add(3), orderedEquals([2,3]), reason:"must add to the end of the list");
    });
    
    test("filter", () {
      var comp = (a) => a == 2;
      expect([1].firstMatching(comp, orElse:() => null), isNull);
      expect([1,2].firstMatching(comp, orElse:() => null), equals(2));
      expect([1,3,4].firstMatching(comp, orElse:() => null), isNull);
      expect([1,2,3,2,1].firstMatching(comp, orElse:() => null), equals(2));
    });
  });
}

