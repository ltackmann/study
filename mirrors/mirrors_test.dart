import "dart:mirrors";
import "package:unittest/unittest.dart";

import "mirrors_test_classes.dart";

main() {
  group("runtimeType -", () {
    test("type name", () {
      var obj = new MyClass();
      expect(obj.runtimeType.toString(), equals("MyClass"));
    });
    
    test("generic type name", () {
      var obj = new MyGenericClass<MyClass>();
      expect(obj.runtimeType.toString(), equals("MyGenericClass<MyClass>"));
    });
    
    // TODO instatiate method from Class.type can I do Class<T>
  });
  
  group("class structure -", () {
    test("generic parameter type name", () {
      // TODO move to dart_store
      var typeExtractor = (Type type) {
        var typeName = type.toString();
        var regex = new RegExp(r"(.*)<(.*)>");
        expect(regex.hasMatch(typeName), isTrue);
        regex.allMatches(typeName).forEach((m) {
          print("count ${m.groupCount} group ${m.group(2)}");
        });
      };
      
      var obj = new MyGenericClass<MyClass>();
      typeExtractor(obj.runtimeType);
      
      //
      //var instanceMirror = reflect(obj);
      //print(instanceMirror.type.typeArguments.forEach((k,v) => print("type key $k value $v")));
      
      // typeArguments #
    });
    
    //  im.type.methods.values.forEach((MethodMirror method) => print(method.simpleName));
    // TODO class methods/fields (and access level (public, final, private)) 
    // TODO method parameter types/numbers
    // TODO access meta data
    // TODO class Type hireacy (super classes and generic arguments (get T's type from Class<T> at runtime))
  });
  
  // TODO http://stackoverflow.com/questions/13205176/how-can-i-use-reflection-mirrors-to-access-the-method-names-in-a-dart-class

  // if possible
  // TODO find all versions of a loaded type 
  // TODO name of calling class (perhaps using getName(type)
}

