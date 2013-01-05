import 'dart:html';
import 'dart:math';

main() {
  var body = query("body");
  test1(body); 
  test2(body);
}

test1(var body) {
  var dataset = [ 5, 10, 15, 20, 25 ];
  
  dataset.forEach((data) {
    var elm = new Element.html("<p>I can count up to $data</p>");
    elm.style.color =  (data > 15) ? "red" : "black";
    // TODO add data to element
    body.nodes.add(elm);
  });
}

test2(var body) {
  var dataset = new List<int>();
  for (var i = 0; i < 25; i++) {
    dataset.add((new Random()).nextInt(30));  
  }
  
  dataset.forEach((data) {
    var elm = new Element.html("<div></div>");
    elm.style.height = "${data * 5}px";
    elm.classes.add("bar");
    body.nodes.add(elm);
  });
}
