library mirrors_test_classes;

/**
 * Test class for using reflection
 */
class MyClass {
  static String publicStaticField = "public static field";
  static String _privateStaticField = "private static field";

  static String publicStaticMethod(String str, int integer) => "public static method";
  static String _privateStaticMethod() => "private static method";

  MyClass():
    publicField = "public field",
    _privateField = "private field",
    publicFinalField = "public final field",
    _privateFinalField = "private final field";

  @deprecated
  String publicMethod(String str, int integer) => "public method";
  String _privateMethod() => "private method";

  String get publicGetter => "public getter";
  String get _privateGetter => "private getter";

  set publicSetter(String value) => publicField = value;
  set _privateSetter(String value) => _privateField = value;

  String publicField;
  String _privateField;
  final String publicFinalField;
  final String _privateFinalField;
}

class MyGenericClass<T> {

}

String myFunction() => "mirrors_test_classes";
