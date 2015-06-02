import 'dart:js' as js;
import 'package:test/test.dart';

main() {
  test("call javascript from Dart", () {
    // call build in js function
    final a = js.context.callMethod("parseInt", ['10']);
    expect(a, equals(10));
    
    // get proxy to JS object
    final b = new js.JsObject(js.context['Date']);
    expect(b.callMethod('getTime', []), isNotNull);
    
    // call custom function (js.context is a proxy to the top level JS object)
    final c = js.context.callMethod("increment", [1]);
    expect(c, equals(2));
    
    // access a nested property
    final d = js.context['navigator']['platform'];
    print("platform ${d}");
  });
  
  test("call dart from javascript", () {
    var called = 0;
    
    // register a javascript function called dartCallback
    js.context['dartCallback'] = (int i) => called += i;
    
    // call javascript function that calls the dart callback
    js.context.callMethod("testCallback", []);
    expect(called, equals(1));
  });
  
  test("listen to event in dart", () {
    var event = null;
    myCallback(arg) {
      event = arg;
      print("custom event recieved");
    }
    js.context.callMethod('addEventListener', ["CustomEvent",myCallback,false]);
    js.context.callMethod("invokeCustomEvent", []);
    expect(event, isNotNull);
  });
}


