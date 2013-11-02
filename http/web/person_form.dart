import 'package:polymer/polymer.dart';
import 'models.dart';
import 'dart:html';

@CustomTag('person-form')
class PersonForm extends PolymerElement with ObservableMixin {
  final Person person = new Person();
  
  // When polymer bug is fixed, action will be set from HTML
  @observable String action = 'http://localhost:8888/submit';
  
  submit(Event e, var detail, Node target) {
    e.preventDefault();
    
    FormElement form = target as FormElement;
    
    HttpRequest.request(action,
          method: form.method,
          sendData: new FormData(form))
        .then((HttpRequest req) {
          shadowRoot.query('#message').text = req.responseText;
        })
        .catchError((e) => print(e));
  }
}