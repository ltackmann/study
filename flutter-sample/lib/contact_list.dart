import 'package:flutter/material.dart';

import 'contact_data.dart';
import 'contact_list_item.dart';

class ContactList extends StatelessWidget {

  final List<Contact> _contacts;

  ContactList(this._contacts);

  @override
  Widget build(BuildContext context) {
    return new MaterialList(
          type: MaterialListType.twoLine,
          padding: new EdgeInsets.symmetric(vertical: 8.0),
          children: _buildContactList()
        );
  }

  List<ContactListItem> _buildContactList() {
    return _contacts.map((contact) => new ContactListItem(contact))
                    .toList();
  }
}
