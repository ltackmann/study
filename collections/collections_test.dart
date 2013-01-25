import "package:unittest/unittest.dart";

main() {
  group("lists -", () {
    test("fold left", () {
      var concat = (String i,String j) => i.concat(j);
      
      expect([].reduce("0", concat), "0"); 
      expect(["1"].reduce("0", concat), "01"); 
      expect(["1","2"].reduce("0", concat), "012"); 
    });
  });
}

