class Musical {
  Musical(this.instrument);
  
  final String instrument;
}

class Aggressive {
  Aggressive(this.aggressionLevel);
  
  final String aggressionLevel;
}

class Maestro extends Object with Musical, Aggressive {
  Maestro(degree, instrument);
  //Aggressive(degree), Musical(instrument);
}


