part of functional_dart;

class MonadsTest {
  MonadsTest() {
    test("optional", () {
      var noCar = new Person(new Optional<Car>(null));
      var noInsurance = new Person(new Optional<Car>(new Car(new Optional<Insurance>(null))));
      var carWithInsurance = new Person(new Optional<Car>(new Car(new Optional<Insurance>(new Insurance("full")))));
    
      expect(getCarInsuranceName(new Optional<Person>(noCar)), equals("Unknown"));
      expect(getCarInsuranceName(new Optional<Person>(noInsurance)), equals("Unknown"));
      expect(getCarInsuranceName(new Optional<Person>(carWithInsurance)), equals("full"));
    });
    
    test("validation", () {
      var toYoung = new Person.withAge(-1);
      var toOld = new Person.withAge(140);
      var justRight = new Person.withAge(42);
      
      expect(validateAge(toYoung), new isInstanceOf<Failure>());
      expect(validateAge(toOld), new isInstanceOf<Failure>());
      expect(validateAge(justRight), new isInstanceOf<Success>());
    });
  }
  
  String getCarInsuranceName(Optional<Person> person) {
    return person.flatMap((Person p) => p.car)
        .flatMap((Car c) => c.insurance)
          .map((Insurance i) => i.name)
            .orElse("Unknown");
  }
  
  Validation<String, Person> validateAge(Person person) {
    return (person.age > 0 && person.age < 130) ? success(person) : failure("Age must be between 0 and 130", person);  
  }
}

// test classes
class Person {
  Person(this.car);
  Person.withAge(this.age);
  Optional<Car> car;
  String fullname;
  int age;
}

class Car {
  Car(this.insurance);
  Optional<Insurance> insurance;
}

class Insurance {
  Insurance(this.name);
  String name;
}