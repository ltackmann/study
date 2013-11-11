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