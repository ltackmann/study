part of mixin_test;

class Person {
  Person(this.name);
  
  final String name;
}

class Musical {
  //Musical(this.instrument);
  
  String instrument;
}

class Aggressive {
  //Aggressive(this.aggressionLevel);
  
  String aggressionLevel;
}

class Maestro extends Person with Musical, Aggressive {
  Maestro(name, aggressionLevel, instrument): super(name) {
    // TODO this should not be needeed
    this.aggressionLevel = aggressionLevel;
    this.instrument = instrument;
  }
  // TODO try passing args mixin classes Aggressive(degree), Musical(instrument);
}


