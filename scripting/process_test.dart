import 'dart:io';
import '../../dart-warrior/packages/unittest/unittest.dart';

main(){
  test("call shell", () {
    var shellScript = '/bin/ls';
    Process.run(shellScript, []);
  });
  
  test("call shell and parse output", () {
    var exec = Process.start('ls', ['/']);
    exec.onComplete((process) {
      StringInputStream stringStream = new StringInputStream(process.stdout);
      stringStream.onData = () {
        String content = stringStream.read();
        print("process: $content");
      };
    });
  });
}

