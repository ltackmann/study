library mixin_test;

import "package:unittest/unittest.dart";

part "mixins.dart";

main() {
  var maestro = new Maestro("Mozart","high", "violin");
  expect(maestro.instrument, equals("violin"));
  expect(maestro.aggressionLevel, equals("high"));
  
  // TODO extension methods
}