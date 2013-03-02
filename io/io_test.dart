import 'dart:io';
import 'package:unittest/unittest.dart';

// TODO http://news.dartlang.org/2013/02/io-library-now-uses-streams.html
main() {
  group("file test -", () {
    test("get current directory", () {
      var dir = new Directory.current();
      expect(dir.path, equals("/Users/lt/Projects/dart"));
    });
    
    test("read file sync", () {
      var file = new File('sandbox/io/pubspec.lock');  
      var content = file.readAsStringSync();
      expect(content, isNot(isEmpty));
    });
    
    test("read file async", () {
      var file = new File('sandbox/io/pubspec.lock');  
      var future = file.readAsString(); // returns immediately  
      future.then(expectAsync1((String content) {
        expect(content, isNot(isEmpty));
      }));
    });
    
    test("read file as stream", () {
      var stream = new File('/etc/passwd').openRead();
      stream.listen((List<int> data) => print(data.length));
      //(onData: );
    });
    
    test("create file", () {
      // assert by checking file exists
    });
    
    test("write to file", () {
      // assert by readin file
    });
  });
  
  group("process test -", () {
    test("call shell", () {
      var shellScript = '/bin/ls';
      Process.run(shellScript, []);
    });
    
    test("call shell and parse output", () {
      Process.start('ls', ['/']).then(expectAsync1((Process process) {
        var stringStream = new StringStream(process.stdout);
        stringStream.onData = () {
          String content = stringStream.read();
          print("process: $content");
        };
      }));
    });
  });
  
  group("socket test -", () {
    // TODO local, http and web sockets
  });
  
  group("serialization test -", () {
    // TODO marshall and unmarskall a class with nested objects 
    // TODO like above but save the object to IO
    // TODO http://api.dartlang.org/docs/releases/latest/serialization.html
    // TODO try writing your own rules
  });
}
