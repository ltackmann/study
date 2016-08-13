import 'package:angular2/core.dart';
import 'notes_lib.dart';

@Component(
    selector: 'notes-menu',
    templateUrl: 'notes_menu_component.html',
    directives: const [NotesService])
class NotesMenuComponent {
  List<Note> notes;
  Note currentNote;

  NotesMenuComponent(NotesService notesService) {
    notes = notesService.getNotes();
    currentNote = notesService.lastUsedNote();
  }

  selectNote(Note note) {
    currentNote = note;
  }
}
