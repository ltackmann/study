import '../../../../../../Applications/dart/dart-sdk/lib/async/async.dart';
import '../../../../../../Applications/dart/dart-sdk/lib/io/io.dart';
import '../../../../.pub-cache/hosted/pub.dartlang.org/unittest-0.4.7+1/lib/unittest.dart';

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
    
    solo_test("read file as stream", () {
      Stream<String> stream = new File('/etc/passwd').openRead().transform(new StringDecoder());
      stream.listen((String data) => print(data));
    });
    
    test("create file", () {
      // assert by checking file exists
    });
    
    test("write to file", () {
      var file = new File('sandbox/io/packages/test.txt'); 
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
    test("get", () {
      var completer = new Completer<String>();
      var httpClient = new HttpClient();
      var conn = httpClient.get("http://www.google.com", 80, "");
      conn.then((HttpClientRequest request) {
        // fire request.
        return request.close();
      }).then((HttpClientResponse response) {
        if(response.statusCode != 200) {
          completer.completeError(new StateError("HTTP faild with ${response.statusCode}"));
        } else {
          response.listen((List<int> bodyData) {
            var body = new String.fromCharCodes(bodyData);
            completer.complete(body);
          });
        }
      });
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
