import 'package:sqljocky/sqljocky.dart';

main() {
  var cnx = new Connection();
  cnx.connect(username, password, dbName, port, hostname).then((nothing) {
    // Do something with the connection
    cnx.query("show tables").then((Results results) {
    print("tables");
    for (List row in results) {
      print(row);
    }
  });
 });
}