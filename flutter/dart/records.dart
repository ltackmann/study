main() {
  (String, int) nameAndAge;
  nameAndAge = ("lars", 42);
  var name = nameAndAge.$1;
  var age = nameAndAge.$2;

  var (nameValue, ageValue) = nameAndAge;
  print("$nameValue is $ageValue years old");
}
