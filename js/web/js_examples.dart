import 'dart:html';
import 'package:js/js.dart' as js;

main() {
  // call javascript from Dart
  js.scoped(() {
    // call build in function
    //js.context.alert('Hello from Dart via JavaScript.');
    
    // call custom function
    var res = js.context.returnTest("Custom Function");
    window.alert(res);
  });
  
  // TODO call dart from javascript
  
  // TODO keep object alive  
}

