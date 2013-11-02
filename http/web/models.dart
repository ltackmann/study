library models;

import 'package:polymer/polymer.dart';

class Person extends Object with ObservableMixin {
  @observable String firstName;
  @observable String lastName;
  @observable int age = 0;
}