import "dart:mirrors";
import 'package:test/test.dart';

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
      im.type.declarations.values.where((DeclarationMirror decl) => decl is MethodMirror).forEach((method) {
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
      var newIm = cm.newInstance(new Symbol(''),[]);
      var instance = newIm.reflectee;
      expect(instance.publicField, equals("public field"));
    });

    test("emit instance from class", () {
      // TODO emit instance from type (the above example uses an instance to emit another instance)
    });
  });

  group("mirrors on classes -", () {
    test("simple type name", () {
      var obj = new MyClass();
      expect(obj.runtimeType.toString(), equals("MyClass"));
    });

    test('qualified type name', () {
      var type = MyClass;
      var cm = reflectClass(MyClass);
      expect(cm.qualifiedName, equals(new Symbol("mirrors_test_classes.MyClass")));
    });
  });

  group("mirrors on libraries -", (){
    test("reflect libraries", () {
      var mirrorSystem = currentMirrorSystem();
      expect(mirrorSystem.libraries, isNot(isEmpty));

      mirrorSystem.libraries.forEach((k,v)=> print(k));
      var libUri = mirrorSystem.libraries.keys.firstWhere((uri) => uri.toString().contains("mirrors_test_classes"));
      LibraryMirror libMirror = mirrorSystem.libraries[libUri];
      expect(libMirror, isNotNull);

      // classes
      var classDeclaration = libMirror.declarations[new Symbol("MyClass")];
      expect(classDeclaration, isNotNull);
      expect(classDeclaration, new isInstanceOf<ClassMirror>());

      // method and functions
      var functionDeclaration = libMirror.declarations[new Symbol("myFunction")];
      expect(functionDeclaration, isNotNull);
      expect(functionDeclaration, new isInstanceOf<MethodMirror>());
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
