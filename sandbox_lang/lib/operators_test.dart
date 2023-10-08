import 'package:test/test.dart';

main() {
  group("null aware operators", () {
    test("if null operator.", () {
      String dummy = null;
      var res  = dummy ?? "test";
      expect(res, equals("test"));

      dummy = "dummy";
      res  = dummy ?? "test";
      expect(res, equals("dummy"));
    });
  });
  // TODO bit operators
}