import "package:logging/logging.dart";

void main() {
  Logger.root.level = Level.ALL;
  Logger.root.onRecord.listen((LogRecord rec) {
    print('${rec.level.name}: ${rec.time}: ${rec.message}');
  });

  var logger = new Logger("test");
  logger.info("a info message");
}
