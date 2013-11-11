import 'package:polymer/polymer.dart';

@CustomTag('ui-input-demo')
class UiInputDemo extends PolymerElement {
  UiInputDemo.created(): super.created();
  
  @observable Login login;
}

class Login {
  String email, password;
}