import 'package:angular2/core.dart';
import 'package:angular2/platform/browser.dart';

import 'notes_lib.dart';

@Component(
    selector: 'app',
    templateUrl: 'app_component.html')
class AppComponent {}

main() {
  bootstrap(AppComponent, [NotesService]);
}
