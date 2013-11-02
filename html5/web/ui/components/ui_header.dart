import 'package:polymer/polymer.dart';

@CustomTag('ui-header')
class UiHeader extends PolymerElement {
  UiHeader.created(): super.created();
  
  // TODO login (inject login and language service)
  // TODO switch language in entire app when default is changed
  // TODO load language list and default from external 
  // TODO change page title when content changes
  
  @observable int selected = 1; 
  @observable String value = '';
  @observable String title = 'The title';
  
  final languages = ['Danish', 'English'];
}