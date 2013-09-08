import 'package:polymer/polymer.dart';

@CustomTag('toggle-widget')
class ToggleWidget extends PolymerElement with ObservableMixin {
  @observable String state = 'On';
  
  void toggle() {
    if (state == 'On') {
      state = 'Off';
    } else {
      state = 'On';
    }
  }
}