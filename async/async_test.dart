import "dart:async";
import "dart:io";
import "package:unittest/unittest.dart";

// TODO http://www.dartlang.org/articles/using-future-based-apis/ (Futures.wait + chains)
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
    
    test("return immediatly", () {
      expect(isOnline(), completion(equals(true)));
    });
    
    test("convert sync function into async", () {
      expect(isOffline(), completion(equals(false)));
    });
    
    test("run command after completion", () {
      var hasRun = false;
      var compute = new Compute();
      compute.sumIt([1, 2, 3]).then(expectAsync1((int sum) {
        expect(sum, equals(6));
        hasRun = true;
      })).whenComplete(() {
        expect(hasRun, isTrue);
      });
    });
  });
  
  group("stream -", () {
    var stream = new File('/etc/passwd').openRead();
    stream.listen(onData: (List<int> data) { print(data.length); });
  });
  
  //Stream<T>
  //StreamConsumer<S, T>
  //StreamSink<T>
  //StreamSubscription<T>
  //StreamTransformer<S, T>
  //Timer
  //Sink
  
  // TODO http://news.dartlang.org/2012/11/introducing-new-streams-api.html
}

/*
 * Helpers 
 */

class Compute {
  Future<int> sumIt(List<int> data) {
    var completer = new Completer<int>();
    int sum = data.reduce(0, (a,b) => a+b);
    completer.complete(sum);
    return completer.future;
  }
}

Future<bool> isOnline() {  
  return new Future.immediate(true);  
}  

bool checkConnection() => false; 
Future<bool> isOffline() {  
  return new Future.of(checkConnection);  
 }  