library test.metadata_test;

import 'dart:mirrors';
import 'package:unittest/unittest.dart';

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
    expect(classMirror.constructors.length, equals(1));
    classMirror.constructors.forEach((s,m) {
      assertMetadata(m.metadata, "constructor");
    });
    
    // field
    expect(classMirror.members.length, equals(1));
    classMirror.members.forEach((s,m) {
      assertMetadata(m.metadata, "field");
    });
  });
}

bool assertMetadata(List<InstanceMirror> metadata, String expected) {
  expect(metadata.length, equals(1));
  expect(metadata.first.reflectee is Message, isTrue);
  expect(metadata.first.reflectee.msg, equals(expected));
}
