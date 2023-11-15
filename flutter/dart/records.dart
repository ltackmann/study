main() {
  // records are indexed
  (String, int) indexedRecord;
  indexedRecord = ("lars", 42);
  print("${indexedRecord.$1} is ${indexedRecord.$2} years old");

  // deconstruct indexed into named values
  var (nameValue, ageValue) = indexedRecord;
  print("$nameValue is $ageValue years old");

  // record values can also be named
  ({String name, int age}) namedRecord;
  namedRecord = (name: "Lars", age: 42);
  print("${namedRecord.name} is ${namedRecord.age} years old");
}
