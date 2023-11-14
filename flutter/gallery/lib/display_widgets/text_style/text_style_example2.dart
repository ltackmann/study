import 'package:flutter/material.dart';

class TextStyleExample2 extends StatelessWidget {
  const TextStyleExample2({super.key});

  @override
  Widget build(BuildContext context) {
    return Text(
      '''Text to be styled''',
      style: TextStyle(
        fontWeight: FontWeight.bold,
      ),
    );
  }
}
