class Contact {
  final String fullName;
  final String email;

  const Contact({this.fullName, this.email});
}

const kContacts = const <Contact>[
    const Contact(
      fullName: 'Romain Hoogmoed',
      email:'romain.hoogmoed@example.com'
    ),
    const Contact(
      fullName: 'Emilie Olsen',
      email:'emilie.olsen@example.com'
    )
];
