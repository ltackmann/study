library test.metadata_test;

import 'dart:mirrors';
import 'package:test/test.dart';

class Message {
  const Message(this.msg);

  final String msg;
}

@Message('class')
class Person {
  @Message('constructor')
  Person(this.name);

  @Message('field')
  String name;
}

void main() {
  test("meta test", (){
    var classMirror = reflectClass(Person);

    // class
    assertMetadata(classMirror.metadata, "class");

    // constructor
    expect(constructors(classMirror).length, equals(1));
    constructors(classMirror).forEach((m) {
      assertMetadata(m.metadata, "constructor");
    });

    // field
    expect(fields(classMirror).length, equals(1));
    fields(classMirror).forEach((m) {
      assertMetadata(m.metadata, "field");
    });
  });
}

assertMetadata(Iterable<InstanceMirror> metadata, String expected) {
  expect(metadata.length, equals(1));
  expect(metadata.first.reflectee is Message, isTrue);
  expect(metadata.first.reflectee.msg, equals(expected));
}

List<DeclarationMirror> constructors(ClassMirror classMirror) {
  return classMirror.declarations.values.where((DeclarationMirror decl) => decl is MethodMirror && (decl).isConstructor).toList();
}

List<DeclarationMirror> fields(ClassMirror classMirror) {
  return classMirror.declarations.values.where((DeclarationMirror decl) => decl is VariableMirror).toList();
}
