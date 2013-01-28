// TODO http://api.dartlang.org/docs/releases/latest/dart_async.html
// TODO async testing with unittest lib

import "dart:async";
import 'package:unittest/unittest.dart';

// http://stackoverflow.com/questions/13678993/how-should-i-test-a-future

class Compute {
  Future<int> sumIt(List<int> data) {
    var completer = new Completer<int>();
    int sum = data.reduce(0, (a,b) => a+b);
    completer.complete(sum);
    return completer.future;
  }
}

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
}