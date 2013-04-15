import "dart:isolate";
import "dart:io";
import '../../dart_warrior/packages/unittest/unittest.dart';

// TODO http://api.dartlang.org/docs/releases/latest/dart_isolate.html

echo() {
  port.receive((msg, reply) {
    reply.send("i received $msg");
  });
}

main() {
  group("isolate", () {
    test("send/recieve", () {
      var sendPort = spawnFunction(echo);
      expect(sendPort.call("hello from test"), completion(equals("i received hello from test")));
    });
    
    test("shut isolate down", () {
    });
    
    test("spawn custom isolates", () {
      // TODO find some AKKA actor "getting started examples" and try porting them to dart
    });
  });
}

/*
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
*/

