import 'package:flutter/material.dart';

class TextStyleExample1 extends StatelessWidget {
  const TextStyleExample1({super.key});

  @override
  Widget build(BuildContext context) {
    return Text(
      '''Text to be styled''',
      style: TextStyle(
        color: Colors.blue,
        fontSize: 16,
      ),
    );
  }
}
