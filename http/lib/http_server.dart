import 'dart:io';
import 'package:http_server/http_server.dart';

echo(HttpRequest req) {
  print('received submit');
  HttpBodyHandler.processRequest(req).then((HttpBody body) {
    print(body.body.runtimeType); // Map
    req.response.headers.add('Access-Control-Allow-Origin', '*');
    req.response.headers.add('Content-Type', 'text/plain');
    req.response.statusCode = 201;
    req.response.write(body.body.toString());
    req.response.close();
  });
}

main() {
  HttpServer.bind('0.0.0.0', 8888).then((HttpServer server) {
    print('Server is running');
    server.listen((HttpRequest req) {
      if (req.uri.path == '/submit' && req.method == 'POST') {
        echo(req);
      }
    });
  });
}