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
   dartCallback(1);
}

function invokeCustomEvent() {
	var evt = document.createEvent("Event");
	evt.initEvent("CustomEvent",true,true);
	
	// add custom property
	evt.eventType = "CustomEvent";
	
	// dispatch
	console.log("custom event send");
	document.dispatchEvent(evt);
}
