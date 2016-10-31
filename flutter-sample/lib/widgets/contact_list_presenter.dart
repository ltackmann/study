import 'package:flutter_sample/model/model_lib.dart';
import 'package:flutter_sample/dependency_injection.dart';

import 'contact_list_view.dart';

class ContactListPresenter {
  ContactListView _view;
  ContactRepository _repository;

  ContactListPresenter(this._view){
    _repository = new Injector().contactRepository;
  }

  loadContacts(){
    assert(_view != null);

    _repository.fetch()
              .then((contacts) => _view.onLoadContactsComplete(contacts))
              .catchError((onError) {
                print(onError);
                _view.onLoadContactsError();
              });
  }
}
