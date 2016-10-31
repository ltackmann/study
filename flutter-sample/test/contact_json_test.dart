import 'dart:io';
import 'dart:convert';

import 'package:flutter_sample/model/model_lib.dart';
import 'package:test/test.dart';

main() async {
  var contactsFile = new File("test/contact_samples.json");
  var contactsJson;

  test("load json", () async {
    print("loading contact file: ${contactsFile.absolute.path}");
    var contactsData = await contactsFile.readAsString(encoding: ASCII);
    contactsJson =  (new JsonDecoder()).convert(contactsData);
    expect(contactsJson, contains("results"));
  });

  test("parse json", () async {
    List<Contact> contacts = contactsJson['results'].map((Map contactData) => new Contact.fromMap(contactData)).toList();
    expect(contacts, hasLength(3));
  });
}
