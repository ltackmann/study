import 'package:sqljocky/sqljocky.dart';

main() {
  var cnx = new ConnectionPool(user:"user", password:"password", db:"dbName");
  cnx.query("show tables").then((Results results) {
    print("tables");
    for (List row in results) {
      print(row);
    }
  });
}
