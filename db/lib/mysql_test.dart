import '../packages/sqljocky/sqljocky.dart';

main() {
  var cnx = new Connection();
  int port = 8080;
  cnx.connect("host", port, "user", "password", "dbName").then((nothing) {
    // Do something with the connection
    cnx.query("show tables").then((Results results) {
    print("tables");
    for (List row in results) {
      print(row);
    }
  });
 });
}