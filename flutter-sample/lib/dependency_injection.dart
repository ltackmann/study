import 'package:flutter_sample/model/model_lib.dart';
import 'package:flutter_sample/model/contact_repository_random.dart';


/// Simple DI
class Injector {
  static final Injector _singleton = new Injector._internal();
  static Flavor _flavor;

  static void configure(Flavor flavor) {
    _flavor = flavor;
  }

  factory Injector() {
    return _singleton;
  }

  Injector._internal();

  ContactRepository get contactRepository {
    switch(_flavor) {
      case Flavor.MOCK: return new ContactRepositoryMock();
      default: // Flavor.PRO:
        return new ContactRepositoryRandom();
    }
  }
}

enum Flavor {
  MOCK,
  PRO
}
