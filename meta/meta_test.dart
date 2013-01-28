import "package:meta/meta.dart";
import "package:unittest/unittest.dart";

/// Test http://news.dartlang.org/2012/06/proposal-to-add-metadata-to-dart.html
main() {
  test("build in meta data", () {
    expect((new Customer()).name, equals("customer"));
  });
  
  test("custom meta data", () {
    // TODO access security annotation with reflection
  });
}

@secured("CUSTOMER_ACCESS")
class Customer extends User {
  @override
  String get name => "customer";
}

class User {
  @deprecated
  int get age => 42;
  
  String get name => "user";
}

/**
 * TODO how to add arguments to meta data ?
 */
const secured = const _Secured();

class _Secured {
  const _Secured();
}

