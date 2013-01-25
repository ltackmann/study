import "dart:isolate";
import "dart:io";

// TODO async testing with unittest lib
// TODO http://api.dartlang.org/docs/releases/latest/dart_isolate.html
class FileIsolate extends Isolate {
  main() {
    port.receive((path, replyTo) {
      File file = new File(path);
      
      String content = null;
      StringInputStream stringStream = new StringInputStream(file.openInputStream());
      stringStream.onData = () {
        content = stringStream.read();
      };
      stringStream.onClosed = () {
        replyTo.send(content);
      };
    });
  }
}

