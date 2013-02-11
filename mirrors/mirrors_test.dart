import "dart:mirrors";
import "package:unittest/unittest.dart";

import "mirrors_test_classes.dart";

main() {
  group("mirrors -", () {
    test("type name", () {
      var obj = new MyClass();
      expect(obj.runtimeType.toString(), equals("MyClass"));
    });
    
    test("generic type name", () {
      var obj = new MyGenericClass<MyClass>();
      expect(obj.runtimeType.toString(), equals("MyGenericClass<MyClass>"));
    });
    
    // TODO instatiate method from Class.type can I do Class<T>
    //test("generic parameter type name", () {
    //  var instanceMirror = reflect(obj);
    //  print(instanceMirror.type.typeArguments.forEach((k,v) => print("type key $k value $v")));
    //});
    
    solo_test("reflect methods", () {
      var obj = new MyClass();
      var instanceMirror = reflect(obj);
      
      int passed = 0;
      instanceMirror.type.methods.values.forEach((MethodMirror method) {
        if(method.simpleName == "_privateMethod") {
          _assertMethod(method, isPrivate:true, isStatic:false, returnType:"String", argTypes:[]); 
          // TODO invoke methods
          passed++;
        }
        if(method.simpleName == "_privateStaticMethod") {
          _assertMethod(method, isPrivate:true, isStatic:true, returnType:"String", argTypes:[]); 
          passed++;
        }
        if(method.simpleName == "publicMethod") {
          _assertMethod(method, isPrivate:false, isStatic:false, returnType:"String", argTypes:["String", "int"]); 
          passed++;    
        }
        if(method.simpleName == "publicStaticMethod") {
          _assertMethod(method, isPrivate:false, isStatic:true, returnType:"String", argTypes:["String", "int"]); 
          passed++;
        }
      });
      expect(passed, equals(4));
    });
    
    
    test("reflect fields", () {
      
    });
    
    // TODO class methods/fields (and access level (public, final, private)) http://api.dartlang.org/docs/releases/latest/dart_mirrors/MethodMirror.html 
    // TODO method parameter types/numbers
    
    // TODO access meta data
    // TODO class Type hireacy (super classes and generic arguments (get T's type from Class<T> at runtime))
  });
  
  // TODO EMIT http://phylotic.blogspot.dk/2012/08/working-with-mirrors-in-dart-brief.html
  
  // TODO http://stackoverflow.com/questions/13205176/how-can-i-use-reflection-mirrors-to-access-the-method-names-in-a-dart-class

  // if possible
  // TODO find all versions of a loaded type 
  // TODO name of calling class (perhaps using getName(type)
}

_assertMethod(MethodMirror method, {bool isPrivate: null, bool isStatic:null, String returnType:null, List<String> argTypes: null}) {
  expect(method.isPrivate, isPrivate);
  expect(method.isStatic, isStatic);
  expect(method.returnType.simpleName, equals(returnType));
  var actualArgTypes = method.parameters.map((ParameterMirror pm) => pm.type.simpleName);
  expect(actualArgTypes, orderedEquals(argTypes));
}

