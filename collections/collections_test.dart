import "package:unittest/unittest.dart";

main() {
  group("lists -", () {
    test("reduce", () {
      var concat = (String i,String j) => i.concat(j);
      
      expect([].reduce("0", concat), "0"); 
      expect(["1"].reduce("0", concat), "01"); 
      expect(["1","2"].reduce("0", concat), "012"); 
    });
    
    test("add", () {
      var list = [2];
      
      expect(list..add(3), orderedEquals([2,3]));
    });
  });
}

