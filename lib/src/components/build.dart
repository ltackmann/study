import 'package:web_components/component_build.dart';
import 'dart:io';

void main() {
  //build(new Options().arguments, ['--clean', '']);
  build(['--clean'], ['web/app.html']);
}