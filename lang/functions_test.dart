import "package:unittest/unittest.dart";

/// Examples of various ways to define Dart functions
main() {
  group("function parameters -", () {
    test("optional named", () {
      expect(_namedFunction("test"), equals("test"));
      expect(_namedFunction("test", repeat:2), equals("testtest"));
    });
    
    test("optional positional", () {
      expect(_positionalFunction("test"), equals("test"));
      expect(_positionalFunction("test", 2), equals("testtest"));
    });
  });
}

String _namedFunction(String msg, {int repeat: null}) {
  String res = "";
  if(repeat == null) {
    repeat = 1;
  }
  while(repeat > 0) {
    res = "$res$msg";
    repeat--;
  }
  return res;
}

String _positionalFunction(String msg, [int repeat = null]) {
  String res = "";
  if(repeat == null) {
    repeat = 1;
  }
  while(repeat > 0) {
    res = "$res$msg";
    repeat--;
  }
  return res;
}

