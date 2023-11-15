import 'package:flutter/material.dart';

class TextButtonExample extends StatefulWidget {
  const TextButtonExample({super.key});

  @override
  State<TextButtonExample> createState() => _TextButtonExampleState();
}

class _TextButtonExampleState extends State<TextButtonExample> {
  String _text = "press button";

  @override
  Widget build(BuildContext context) {
    return Row(
      children: [
        TextButton(
          onPressed: () {
            setState(() {
              _text = "Pressed TextButton";
            });
          },
          child: Text("TextButton"),
        ),
        Center(
          child: Text(_text),
        ),
      ],
    );
  }
}
