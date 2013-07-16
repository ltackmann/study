import 'dart:html';

import 'package:js/js.dart' as js;
import 'package:unittest/unittest.dart';

main() {
  test("call javascript from Dart", () {
    // call build in js function
    final a = js.context.parseInt('10');
    expect(a, equals(10));
    
    // get proxy to JS object
    final b = new js.Proxy(js.context.Date);
    expect(b.getTime(), isNotNull);
    
    // call custom function (js.context is a proxy to the top level JS object)
    final c = js.context.increment(1);
    expect(c, equals(2));
    
    // access a nested property
    final d = js.context.navigator.platform;
    print("platform ${d}");
  });
  
  test("call dart from javascript", () {
    var called = 0;
    var callback = (int i) => called += i;
    
    // register a javascript function called testCallbackOnce that can call Dart once
    js.context.testCallbackOnce = new js.Callback.once(callback);
    
    // call the javascript function and test that the callback worked
    js.context.testCallback();
    expect(called, equals(1));
    
    // test we can only call the function once
    bool catched = false;
    try {
      js.context.testCallbackOnce();
    } catch(e) {
      catched = true;
    }
    expect(catched, isTrue);
    expect(called, equals(1));
    
    // TODO arguments and callback multiple times
  });
  
  test("keep objects alive in a long running JS communication ", () {
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
  });
  
  test("listen to event in dart", () {
    callback(arg) {
      print("custom event recieved in Dart");
    }
    js.context.document.addEventListener("CustomEvent",callback,false);
    js.context.invokeCustomEvent();
  });
}


