import 'package:flutter/material.dart';
import 'package:flutter_sample/view/contacts_view.dart';
import 'package:flutter_sample/dependency_injection.dart';

void main() {
  Injector.configure(Flavor.PRO);

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
