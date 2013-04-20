import "dart:mirrors";
import "package:unittest/unittest.dart";

import "mirrors_test_classes.dart";

main() {
  group("mirrors on instances -", () {
    test("qualified type name", () {
      var obj = new MyClass();
      var im = reflect(obj);
      expect(im.type.qualifiedName, equals(new Symbol("mirrors_test_classes.MyClass")));
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
        // TODO invoke methods http://phylotic.blogspot.dk/2012/08/working-with-mirrors-in-dart-brief.html
        if(MirrorSystem.getName(method.simpleName) == "_privateMethod") {
          _assertMethod(method, isPrivate:true, isStatic:false, returnType:"String", argTypes:[]); 
          passed++;
        }
        if(MirrorSystem.getName(method.simpleName) == "_privateStaticMethod") {
          _assertMethod(method, isPrivate:true, isStatic:true, returnType:"String", argTypes:[]); 
          passed++;
        }
        if(method.simpleName == new Symbol("publicMethod")) {
          _assertMethod(method, isPrivate:false, isStatic:false, returnType:"String", argTypes:["String", "int"]); 
          passed++;    
        }
        if(method.simpleName == new Symbol("publicStaticMethod")) {
          _assertMethod(method, isPrivate:false, isStatic:true, returnType:"String", argTypes:["String", "int"]); 
          passed++;
        }
      });
      expect(passed, equals(4));
    });
    
    test("reflect fields", () {
      // TODO
    });
    
    test("emit instance from prototype", () {
      var im = reflect(new MyClass());
      var cm = im.type;
      cm.newInstanceAsync(new Symbol(''),[]).then(expectAsync1((InstanceMirror newIm) {
        var instance = newIm.reflectee;
        expect(instance.publicField, equals("public field"));
      })); 
      // TODO emit instance from type (the above example uses an instance to emit another instance)
    });
    
    test("emit instance from class", () {
    });
  });
  
  group("mirrors on classes -", () {
    test("simple type name", () {
      var obj = new MyClass();
      expect(obj.runtimeType.toString(), equals("MyClass"));
    });
    
    test('qualified type name', () {
      var type = MyClass;
      var name = new Symbol(type.toString());
      // TODO reflectClass(MyClass);
      var cm = currentMirrorSystem().libraries.values
          .where((lib) => lib.classes.containsKey(name))
          .map((lib) => lib.classes[name])
          .first;
      expect(cm.qualifiedName, equals(new Symbol("mirrors_test_classes.MyClass")));
    });
  });
  
  group("mirrors on libraries -", (){
    test("reflect libraries", () {
      var mirrorSystem = currentMirrorSystem();
      expect(mirrorSystem.libraries, isNot(isEmpty));
      
      var lib = mirrorSystem.libraries[new Symbol("mirrors_test_classes")];
      expect(lib, isNotNull);
      
      expect(lib.classes.length, equals(2));
      expect(lib.classes[new Symbol("MyClass")], isNotNull);
      expect(lib.functions.length, equals(1));
      expect(lib.functions[new Symbol("myFunction")], isNotNull);
    });
  });
}

_assertMethod(MethodMirror method, {bool isPrivate: null, bool isStatic:null, String returnType:null, List<String> argTypes: null}) {
  expect(method.isPrivate, isPrivate);
  expect(method.isStatic, isStatic);
  expect(method.returnType.simpleName, equals(new Symbol(returnType)));
  var actualArgTypes = method.parameters.map((ParameterMirror pm) => pm.type.simpleName);
  expect( actualArgTypes, orderedEquals(argTypes.map((a) => new Symbol(a))) );
}

