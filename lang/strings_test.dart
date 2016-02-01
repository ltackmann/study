import "package:test/test.dart";

main() {
  group("strings -", () {
    test("Define a string containing special characters", () {
      var special = r"""\#{'}${"}/""";
      expect(special, equals(special.toString()), reason: "assert that string can be created with special chars in it");
    });
  });

  group("regex -", () {
    test("group matching", () {
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

    test("replace", () {
      var r = new RegExp('[^0-9]');
      expect("sdfsdf3234f34f".replaceAll(r, ''), equals("323434"));
    });

    //TODO "str".splitMapJoin(Pattern pattern, {String onMatch(Match match), String onNonMatch(String nonMatch)})
  });
}
