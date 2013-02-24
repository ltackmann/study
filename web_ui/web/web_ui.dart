import 'dart:html';
import 'package:web_ui/web_ui.dart';

// global values availble to all components
int startingCount = 5; 
String dataValue;

void main() {
  var today = new Date.now();
  dataValue = 'world ${today.year}-${today.month}-${today.day}';
}
