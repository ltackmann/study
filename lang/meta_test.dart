library test.metadata_test;

import 'dart:mirrors';
import 'package:unittest/unittest.dart';

class Awesome {
  final String msg;
  const Awesome(this.msg);
  
  String toString() => msg;
}

@Awesome('it works!')
class Cool {
  
}

void main() {
  test("meta test", (){
    var classMirror = reflectClass(Cool);
    var metadata = classMirror.metadata;
    var obj = metadata.first.reflectee;
    expect(obj.toString(), equals("it works!"));
  });
}
