part of functional_dart;

class OptionalTest {
  OptionalTest() {
    test("optional", () {
      var noCar = new Person(new Optional<Car>(null));
      var noInsurance = new Person(new Optional<Car>(new Car(new Optional<Insurance>(null))));
      var carWithInsurance = new Person(new Optional<Car>(new Car(new Optional<Insurance>(new Insurance("full")))));
    
      expect(getCarInsuranceName(new Optional<Person>(noCar)), equals("Unknown"));
      expect(getCarInsuranceName(new Optional<Person>(noInsurance)), equals("Unknown"));
      expect(getCarInsuranceName(new Optional<Person>(carWithInsurance)), equals("full"));
    });
  }
  
  String getCarInsuranceName(Optional<Person> person) {
    return person.flatMap((Person p) => p.car)
        .flatMap((Car c) => c.insurance)
          .map((Insurance i) => i.name)
            .orElse("Unknown");
  }
}

// test classes
class Person {
  Person(this.car);
  Optional<Car> car;
}

class Car {
  Car(this.insurance);
  Optional<Insurance> insurance;
}

class Insurance {
  Insurance(this.name);
  String name;
}