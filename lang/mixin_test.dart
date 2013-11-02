import 'package:unittest/unittest.dart';

main() {
  test("mixin", () {
    var maestro = new Maestro(name:"Mozart",aggressionLevel:"high", instrument:"violin");
    
    expect(maestro.plays, "Mozart plays violin");
    expect(maestro.name, equals("Mozart"));
    expect(maestro.aggressionLevel, equals("high"));
    expect(maestro.instrument, equals("violin"));
    expect(maestro, new isInstanceOf<Aggressive>("Aggressive"));
    expect(maestro, new isInstanceOf<Musical>("Musical"));
    expect(maestro, new isInstanceOf<Person>("Person"));
  });
}

abstract class Person {
  String name;
}

abstract class Musical {
  String instrument;
  
  String get plays => "${name} plays ${instrument}";
  
  String get name;
}

abstract class Aggressive {
  String aggressionLevel;
}

class Maestro extends Object with Aggressive, Musical, Person {
  Maestro({String name, String aggressionLevel, String instrument}) {
    // TODO this should not be needed
    this.name = name;
    this.aggressionLevel = aggressionLevel;
    this.instrument = instrument;
  }
}
