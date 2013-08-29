import 'dart:async';
import 'dart:convert';
import 'dart:io';
import 'package:unittest/unittest.dart';

// TODO http://news.dartlang.org/2013/02/io-library-now-uses-streams.html
main() {
  group("system", () {
    test("env", () {
      var env = Platform.environment;
      env.forEach((k,v) => print("$k:$v"));
      expect(env["HOME"], isNot(isEmpty));
    });
  });
  
  group("file test -", () {
    test("get current directory", () {
      var dir = Directory.current;
      expect(dir.path, equals("/Users/lt/Projects/dart/sandbox/lang"));
    });
    
    test("read file sync", () {
      var file = new File('pubspec.lock');  
      var content = file.readAsStringSync();
      expect(content, isNot(isEmpty));
    });
    
    test("read file async", () {
      var file = new File('pubspec.lock'); 
      var future = file.readAsString(); // returns immediately  
      future.then(expectAsync1((String content) {
        expect(content, isNot(isEmpty));
      }));
    });
    
    test("read file as stream", () {
      var stream = new File('/etc/passwd').openRead().transform(UTF8.decoder);
      //stream.listen((String data) => print(data));
    });
    
    test("create file", () {
      // assert by checking file exists
    });
    
    test("write to file", () {
      var file = new File('packages/test.txt'); 
      file.openSync(mode:FileMode.APPEND).writeStringSync("test ${(new DateTime.now()).toString()}\n");
      // TODO assert by reading list line of file 
    });
  });
  
  group("process test -", () {
    test("call shell", () {
      var shellScript = '/bin/ls';
      Process.run(shellScript, []);
    });
    
    test("call shell and parse output", () {
      Process.start('ls', ['/']).then(expectAsync1((Process process) {
        process.stdout.listen((List<int> data) {
          var result = new String.fromCharCodes(data);
          expect(result, isNot(isEmpty));
        });
      }));
    });
  });
  
  group("http test -", () {
    solo_test("get", () {
      var completer = new Completer<String>();
      var httpClient = new HttpClient();
      var connection = httpClient.get("google.com", 80, "");
      connection.then(expectAsync1((HttpClientRequest request) {
        // fire request.
        return request.close();
      })).then(expectAsync1((HttpClientResponse response) {
        if(response.statusCode == 200) {
          response.listen((List<int> bodyData) {
            var body = new String.fromCharCodes(bodyData);
            completer.complete(body);
          });
        } else {
          completer.completeError(new StateError("HTTP faild with ${response.statusCode}"));
        }
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
