function returnTest(msg) {
   return "Hello " + msg;
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