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
    
    test("generic parameter type name", () {
      var obj = new MyGenericClass<MyClass>();
      var im = reflect(obj);
      expect(im.type.typeArguments.values.first, equals("MyClass"));
    });
    
    test("reflect methods", () {
      var obj = new MyClass();
      var im = reflect(obj);
      
      int passed = 0;
      im.type.methods.values.forEach((MethodMirror method) {
        if(method.simpleName == "_privateMethod") {
          _assertMethod(method, isPrivate:true, isStatic:false, returnType:"String", argTypes:[]); 
          // TODO invoke methods http://phylotic.blogspot.dk/2012/08/working-with-mirrors-in-dart-brief.html
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
    
    solo_test("emit instance", () {
      var im = reflect(new MyClass());
      var cm = im.type;
      cm.newInstance('',[]).then(expectAsync1((InstanceMirror newIm) {
        var instance = newIm.reflectee;
        expect(instance.publicField, equals("public field"));
      })); 
      // TODO emit instance from type (the above example uses an instance to emit another instance)
    });
    
    // TODO access meta data
    // TODO class type hireacy (super class) and constructors 
  });
  
  // TODO EMIT http://phylotic.blogspot.dk/2012/08/working-with-mirrors-in-dart-brief.html
  
  // if possible
  // TODO find all versions of a loaded type (perhaps by hooking into current isolate)
  // TODO name of calling class (perhaps using getName(type)
}

_assertMethod(MethodMirror method, {bool isPrivate: null, bool isStatic:null, String returnType:null, List<String> argTypes: null}) {
  expect(method.isPrivate, isPrivate);
  expect(method.isStatic, isStatic);
  expect(method.returnType.simpleName, equals(returnType));
  var actualArgTypes = method.parameters.map((ParameterMirror pm) => pm.type.simpleName);
  expect(actualArgTypes, orderedEquals(argTypes));
}

