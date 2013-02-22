
import 'dart:io';

// TODO http://news.dartlang.org/2013/02/io-library-now-uses-streams.html
main() {
  group("file test", () {
    setUp(() {
      // delete file if it exists
    });
    
    test("create file", () {
      // assert by checking file exists
    });
    
    test("write to file", () {
      // assert by readin file
    });
    
    tearDown(() {
      // delete file
    });
  });
}
