function increment(int) {
   return int + 1;
}

function objectTest(initial) {
  var value;

  var reset = function() {
    value = initial;
  } 
  reset();

  return {
    "getValue": function() {
       return value;
    },
    "setValue": function(newVal) {
       value = newVal;
    },
    "resetValue": function() {
       reset();
    }
  }
}

function testCallback() {
   // call function in dart
   testCallbackOnce(1);
}