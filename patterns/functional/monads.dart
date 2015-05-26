part of functional_dart;

// optional monad
class Optional<T> {
  Optional(this.value);
  
  Optional map(dynamic f(T)) {
    return value == null ? EMPTY : new Optional(f(value));  
  }
  
  Optional flatMap(Optional f(T)) {
    return value == null ? EMPTY : f(value);  
  }
  
  T orElse(T other) {
    return value != null ? value : other; 
  }
  
  final T value;
  static final EMPTY = new Optional(null);
}

// validation monads
abstract class Validation<L,A> {
  Validation(this.value);
  
  Validation<L, dynamic> map(dynamic mapper(A));
  
  Validation<L, dynamic> flatMap(Validation mapper(A));
  
  bool get isSuccess;
  
  final A value;
}

class Success<L,A> extends Validation<L,A> {
  Success._internal(A value): super(value);  
  
  Validation<L, dynamic> map(dynamic mapper(A)) => success(mapper(value));
  
  Validation<L, dynamic> flatMap(Validation mapper(A)) => mapper(value);
  
  bool get isSuccess => true; 
}
Success success(dynamic v) => new Success._internal(v);

class Failure<L,A> extends Validation<L,A> {
  Failure._internal(A value, this.left): super(value);
  
  Validation<L, dynamic> map(dynamic mapper(A)) => failure(left, mapper(value));
  
  Validation<L, dynamic> flatMap(Validation mapper(A)) {
    var result = mapper(value);
    return result.isSuccess ? failure(left, result.value) : failure(result.left, result.value);
  }
  
  bool get isSuccess => false; 
  
  final L left;
}
Failure failure(dynamic v, dynamic l) => new Failure._internal(v, l);