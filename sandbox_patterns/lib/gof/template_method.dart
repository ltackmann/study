/**
 * Define the skeleton of an algorithm in an operation, deferring some steps 
 * to subclasses. Lets subclasses redefine certain steps of an algorithm 
 * without changing the algorithm's structure.
 */

main() {
  Report htmlReport = new HTMLReport();
  print(htmlReport.buildReport());
  
  Report plainTextReport = new PlainTextReport();
  print(plainTextReport.buildReport());
}

abstract class Report {
  String buildReport() {
    _report.clear();
    buildStart();
    buildHead();
    buildBodyStart();
    buildBody();
    buildBodyEnd();
    buildEnd();
    return _report.toString();
  }
  
  addLine(String line) => _report.write(line);
  
  // implemented/overwritten in subclasses
  buildBodyStart();
  buildBody() => text.forEach((line) => addLine(line));
  buildBodyEnd();
  buildEnd();
  buildHead() => addLine(title);
  buildStart();
  
  final StringBuffer _report = new StringBuffer();
  String title = "Monthly Report";
  List<String> text = [ "things are going well "]; 
}

class HTMLReport extends Report {
  buildBodyStart() => addLine(r"<body>");
  buildBody() => text.forEach((line) => addLine('<p>${line}</p>'));
  buildBodyEnd() => addLine(r"</body>");
  buildEnd() => addLine(r"</html>");
  buildHead() {
    addLine(
 '''<head>
      <title>${title}</title>
    </head>
    ''');
  }
  buildStart() => addLine(r"<html>");
}

class PlainTextReport extends Report {
  buildBodyStart() => null; // do nothing for plain text
  buildBody() => text.forEach((line) => addLine("\n${line}"));
  buildBodyEnd() => null; // do nothing for plain text
  buildEnd() => null; // do nothing for plain text
  buildHead() => addLine('****${title}****');
  buildStart() => null; // do nothing for plain text
}