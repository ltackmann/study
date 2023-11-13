import 'package:flutter/material.dart';

class TextStyleExample1 extends StatelessWidget {
  const TextStyleExample1({super.key});

  @override
  Widget build(BuildContext context) {
    return Text(
      '''Long sample text so we can see how it wraps, aligns and truncates''',
      style: TextStyle(
        color: Colors.red,
        fontSize: 16,
      ),
    );
  }
}
