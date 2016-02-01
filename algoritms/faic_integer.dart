library faic_integer;

import 'faic_natural.dart';

/**
 * Represent integers as natural signed numbers
 */
class Integer implements Comparable<Integer> {
  static const Sign Positive = const Sign("+");
  static const Sign Negative = const Sign("-");

  static const Integer Zero = const Integer._internal(Positive, Natural.Zero);
  static const Integer One = const Integer._internal(Positive, Natural.One);

  final Sign sign;
  final Natural magnitude;

  bool get _isDefault => sign == null;

  const Integer._internal(this.sign, this.magnitude);

  factory Integer(Sign sign, Natural magnitude) {
    if (magnitude == Natural.Zero) {
      return Zero;
    }
    return new Integer._internal(sign, magnitude);
  }

  factory Integer.fromNatural(Natural n) {
    if(n == null) {
      throw new ArgumentError("null natural value passed to integer constructor");
    }
    return new Integer(Positive, n);
  }

  // ---------
  // operators
  // ---------

  Integer operator -() {
    return _negate(this);
  }

  Integer operator +(Integer other) {
    return _add(this, other);
  }

  Integer operator -(Integer other) {
    return _subtract(this, other);
  }

  Integer operator *(Integer other) {
    return _multiply(this, other);
  }

  Integer operator ^(Integer other) {
    return _power(this, other.asNatural);
  }

  bool operator <(Integer other) { return _compareTo(this, other) < 0; }

  bool operator >(Integer other) { return _compareTo(this, other) > 0; }

  bool operator <=(Integer other) { return _compareTo(this, other) <= 0; }

  bool operator >=(Integer other) { return _compareTo(this, other) >= 0; }

  bool operator ==(Integer other) { return _compareTo(this, other) == 0; }

  Integer operator /(Integer other) { return _divide(this, other); }

  Integer operator %(Integer other) { return _remainder(this, other); }




  // -------
  // methods
  // -------

  Natural get asNatural {
    if (_isDefault) {
      return Zero.magnitude;
    } else if (sign == Negative) {
      throw new StateError("cannot convert negative integer to natural");
    }
    return magnitude;
  }

  @override
  String toString() {
    Integer x = this;
    if (x._isDefault) x = Zero;
    var str = x.magnitude.toString();
    if (x.sign == Negative)
        str = "-" + str;
    return str;
  }

  Integer get increment =>_add(this, One);

  Integer get decrement => _subtract(this, One);

  @override
  int compareTo(Integer other) { return _compareTo(this, other); }

  @override
  int get hashCode {
    Integer x = this;
    if (x._isDefault) x = Zero;
    return asInt;
  }

  int get asInt {
    int value = magnitude.asInt;
    return (sign == Negative) ? -value : value;
  }

  // ----------------
  // internal statics
  // ----------------

  static Integer _negate(Integer x) {
    if (x._isDefault) x = Zero;
    return new Integer(x.sign == Positive ? Negative : Positive, x.magnitude);
  }

  static Integer _add(Integer x, Integer y) {
    if (x._isDefault) x = Zero;
    if (y._isDefault) y = Zero;
    if (x.sign == y.sign) {
      return new Integer(x.sign, x.magnitude + y.magnitude);
    } else if (x.sign == Positive) {
      // x + -y = x - y
      return x - (-y);
    } else {
      // -x + y = y - x
      return y - (-x);
    }
  }

  static Integer _subtract(Integer x, Integer y) {
    if (x._isDefault) x = Zero;
    if (y._isDefault) y = Zero;
    if (x.sign != y.sign) {
      // -x - y = -(x + y)
      return new Integer(x.sign, x.magnitude + y.magnitude);
    } else if (x.sign == Negative) {
      // -x - -y = x - y
      return (-y) - (-x);
    } else if (x.magnitude >= y.magnitude) {
      // x > y && x > 0 && y > 0 => x - y using naturals
      return new Integer(Positive, x.magnitude - y.magnitude);
    } else {
      // x < y && x > 0 && y > 0 => -(y - x)
      return new Integer(Negative, y.magnitude - x.magnitude);
    }
  }

  static Integer _multiply(Integer x, Integer y) {
    if (x._isDefault) x = Zero;
    if (y._isDefault) y = Zero;
    var sign = x.sign == y.sign ? Positive : Negative;
    var value = x.magnitude * y.magnitude;
    return new Integer(sign, value);
  }

  static Integer _power(Integer x, Natural exponent) {
    if (exponent == null) {
      throw new ArgumentError("exponent");
    }
    if (x._isDefault) x = Zero;
    var sign = x.sign == Negative && exponent % Natural.Two != Natural.Zero ? Negative : Positive;
    var value = (x.magnitude)^exponent;
    return new Integer(sign, value);
  }

  static int _compareTo(Integer x, Integer y) {
    if (identical(x, y)) {
      return 0;
    } else if (x == null) {
      return -1;
    } else if (y == null) {
      return 1;
    }

    if (x._isDefault) x = Zero;
    if (y._isDefault) y = Zero;

    if (x.sign == Negative && y.sign == Positive) {
      // x < 0 && y > 0
      return -1;
    } else if (x.sign == Positive && y.sign == Negative) {
        // x > 0 && y < 0
      return 1;
    } else if (x.sign == Positive) {
      // x > 0 && y > 0
      return x.magnitude.compareTo(y.magnitude);
    } else {
      // x < 0 && y < 0
      return y.magnitude.compareTo(x.magnitude);
    }
  }

  static Integer _divide(Integer x, Integer y) {
    if (x._isDefault) x = Zero;
    if (y._isDefault) y = Zero;
    if (y == Zero) {
      throw new ArgumentError("cannot divide with zero");
    } else {
      return new Integer(x.sign == y.sign ? Positive : Negative, x.magnitude / y.magnitude);
    }
  }

  static Integer _remainder(Integer x, Integer y) {
    if (x._isDefault) x = Zero;
    if (y._isDefault) y = Zero;
    if (y == Zero) {
      throw new ArgumentError("cannot finde remainder when dividing with zero");
    } else {
      return new Integer(x.sign, x.magnitude % y.magnitude);
    }
  }
}

class Sign {
  final String sign;

  const Sign(this.sign);

  @override
  String toString() => this.sign;
}
