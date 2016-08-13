library notes;

class Note {
  String name;
  String content;

  Note(this.name, this.content);
}

class NotesService {
  final _notes = [ new Note("Algebra", "x+y"), new Note("Calculus", "integrat(x^2)")];

  List<Note> getNotes() {
    return _notes;
  }

  Note lastUsedNote() => _notes.first;
}
