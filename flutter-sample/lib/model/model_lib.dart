library contacts;

import 'dart:async';

part 'contact.dart';

class ContactRepositoryMock implements ContactRepository{
  static const _mockContacts = const <Contact>[
      const Contact(
        fullName: 'Romain Hoogmoed',
        email:'romain.hoogmoed@example.com'
      ),
      const Contact(
        fullName: 'Emilie Olsen',
        email:'emilie.olsen@example.com'
      )
  ];

  @override
  Future<List<Contact>> fetch(){
    return new Future.value(_mockContacts);
  }
}
