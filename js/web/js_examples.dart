import 'dart:html';

import 'package:js/js.dart' as js;
import 'package:unittest/unittest.dart';

main() {
  // call javascript from Dart
  //testDart2Js();
  
  // call dart from javascript
  // TODO understand callback
  
  // keep object alive  
  testGlobalScope();
}

testDart2Js() {
  js.scoped(() {
    // call build in function
    js.context.alert('Hello from Dart via JavaScript.');
    
    // call custom function
    var res = js.context.returnTest("Custom Function");
    window.alert(res);
  });
}

testGlobalScope() {
  var x;
  js.scoped(() {
    x = js.context.objectTest("hello"); 
    expect(x.getValue(), equals("hello"));
    js.retain(x);
  });
  js.scoped(() {
    x.setValue("world");
    expect(x.getValue(), equals("world"));
    x.resetValue();
    expect(x.getValue(), equals("hello"));
    js.release(x);
  });
}

