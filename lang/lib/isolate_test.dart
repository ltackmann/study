import "dart:isolate";
import 'package:test/test.dart';

/// Examples of using isolates (i.e. actors) for concurrent programming
main() {
  group("isolate", () {
    test("send/recieve", () {
      var receiever = new ReceivePort();
      var remote = Isolate.spawn(echo, receiever.sendPort);
      
      remote.then(expectAsync((Isolate isolate) {
        receiever.listen((response) {
          var msg = response[0] as String;
          var communicator = response[1] as SendPort;
          
          print("main received: $msg");
          expect(msg, equals("isolate started"));
          
          communicator.send("close");
        });
      }));
    });
    
    /*
    test("spawn multiple isolates", () {
      var isolate1 = Isolate.spawn(recieve, {"name":"isolate1"});
      var isolate2 = Isolate.spawn(recieve, {"name":"isolate2"});

      expect(isolate1.call({"tick":true}), completion(equals("isolate1 counter is 1")));
      expect(isolate2.call({"tick":true}), completion(equals("isolate2 counter is 1")));
    });
    
    test("isolate chat server", () {
      
    });
    */
  });
}

echo(SendPort communicator) {
  var receiver = new ReceivePort();
  communicator.send(["isolate started", receiver.sendPort]);
  receiver.listen((msg) {
    print("isolate received: $msg");
    if(msg == "close") {
      receiver.close();
    }
  });
}

recieve(SendPort replyTo) {
  var port = new ReceivePort();
  var name = "";
  var counter = 0;
  
  port.listen((message) {
    if(message["name"] != null) {
      name = message["name"];
    }
    if(message["tick"] != null) {
      counter++;
      var answer = "$name counter is $counter";
      replyTo.send(answer);
    }
  });
}



