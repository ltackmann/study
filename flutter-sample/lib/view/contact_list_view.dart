import 'package:flutter_sample/model/model_lib.dart';

abstract class ContactListView {
  void onLoadContactsComplete(List<Contact> items);
  
  void onLoadContactsError();
}
