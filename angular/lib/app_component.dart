library angular_sandbox;

import 'package:angular2/core.dart';
import 'notes_menu_component.dart';

@Component(
  selector: 'my-app',
  templateUrl: 'app_component.html',
  directives: const [NotesMenuComponent])
class AppComponent {

}
