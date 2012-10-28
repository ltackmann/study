class FileIsolate extends Isolate {
  main() {
    port.receive((path, replyTo) {
      File file = new File(path);
      
      String content = null;
      StringInputStream stringStream = new StringInputStream(file.openInputStream());
      stringStream.dataHandler = () {
        content = stringStream.read();
      };
      stringStream.closeHandler = () {
        replyTo.send(content);
      };
    });
  }
}

