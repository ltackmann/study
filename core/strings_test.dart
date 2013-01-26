import "package:unittest/unittest.dart";

main() {
  group("Strings printing -", () {
    test("Output a string to the console", () {
      print("Hello World!");
    });
  });
  
  group("Strings declaration -", () {
    test("Define a string containing special characters", () {
      var special = r"""\#{'}${"}/""";
    });
  });
}

