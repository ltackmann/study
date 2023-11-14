main() {
  // record as a indexed tuple
  (String, int) nameAndAge;
  nameAndAge = ("lars", 42);
  print("${nameAndAge.$1} is ${nameAndAge.$2} years old");

  // deconstruct tuple into named values
  var (nameValue, ageValue) = nameAndAge;
  print("$nameValue is $ageValue years old");

  // named record
  ({String name, int age}) namedRecord;
  namedRecord = (name: "Lars", age: 42);
  print("${namedRecord.name} is ${namedRecord.age} years old");
}
