import "dart:async";
import "package:unittest/unittest.dart";

/// Test http://api.dartlang.org/docs/releases/latest/dart_async.html
void main() {
  group("future -", () {
    test("testing with completion", () {
      var compute = new Compute();    
      var future = compute.sumIt([1, 2, 3]);
      expect(future, completion(equals(6)));
    });
    
    test("testing with expectAsync", () {
      var compute = new Compute();
      compute.sumIt([1, 2, 3]).then(expectAsync1((int sum) {
        expect(sum, equals(6));
      }));
    });
  });
  
  group("stream -", () {
    new File('/etc/passwd').openForRead() // returns a Stream.
    .subscribe(onData: (List<int> data) { print(data.length); });
  });
  
  //Stream<T>
  //StreamConsumer<S, T>
  //StreamSink<T>
  //StreamSubscription<T>
  //StreamTransformer<S, T>
  //Timer
  
  // TODO http://news.dartlang.org/2012/11/introducing-new-streams-api.html
}

class Compute {
  Future<int> sumIt(List<int> data) {
    var completer = new Completer<int>();
    int sum = data.reduce(0, (a,b) => a+b);
    completer.complete(sum);
    return completer.future;
  }
}