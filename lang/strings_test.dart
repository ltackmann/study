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
  
  group("Regex -", () {
    var typeName = "MyGenericClass<MyClass>";
    var regex = new RegExp(r"(.*)<(.*)>");
    expect(regex.hasMatch(typeName), isTrue);
    
    var matches = regex.allMatches(typeName);
    expect(matches.length, equals(1));
    expect(matches.first.groupCount, equals(2));
    expect(matches.first.group(0), "MyGenericClass<MyClass>");
    expect(matches.first.group(1), "MyGenericClass");
    expect(matches.first.group(2), "MyClass");
  });
}

