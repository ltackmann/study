import 'package:flutter/material.dart';

import 'contact_list.dart';
import 'contact_data.dart';

void main() {
  runApp(
    new MaterialApp(
      title: 'Flutter Sample',
      theme: new ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: new ContactsPage(),
    ),
  );
}

class ContactsPage extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    return new Scaffold(
        appBar: new AppBar(
          title: new Text("Contacts"),
        ),
        body: new ContactList(kContacts)
      );
  }

}
