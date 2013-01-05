import 'dart:html';

import 'package:js/js.dart' as js;
import 'package:unittest/unittest.dart';

main() {
  // call javascript from Dart
  testDart2Js();
  
  // call dart from javascript
  testCallback();
  
  // keep object alive  
  testGlobalScope();
}

testDart2Js() {
  js.scoped(() {
    // call build in function
    final a = js.context.parseInt('10');
    expect(a, equals(10));
    
    // get proxy to JS object
    final b = new js.Proxy(js.context.Date);
    expect(b.getTime(), isNotNull);
    
    // call custom function (js.context is a proxy to the top level JS object)
    final c = js.context.increment(1);
    expect(c, equals(2));
  });
}

testGlobalScope() {
  var x;
  js.scoped(() {
    x = js.context.objectTest("hello"); 
    expect(x.getValue(), equals("hello"));
    // retain proxy object when scope is ended
    js.retain(x);
  });
  js.scoped(() {
    x.setValue("world");
    expect(x.getValue(), equals("world"));
    
    x.resetValue();
    expect(x.getValue(), equals("hello"));
    // release proxy object after use
    js.release(x);
  });
}

testCallback() {
  // register a function in javascript called testCallback that call's Dart code
  js.context.testCallback = new js.Callback.once(display);
}

