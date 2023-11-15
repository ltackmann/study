import 'package:flutter/material.dart';

class ElevatedButtonExample extends StatefulWidget {
  const ElevatedButtonExample({super.key});

  @override
  State<ElevatedButtonExample> createState() => _ElevatedButtonExampleState();
}

class _ElevatedButtonExampleState extends State<ElevatedButtonExample> {
  String _text = "press button";

  @override
  Widget build(BuildContext context) {
    return Row(
      children: [
        ElevatedButton(
          onPressed: () {
            setState(() {
              _text = "Pressed ElevatedButton";
            });
          },
          child: Text("ElevatedButton"),
        ),
        Center(
          child: Text(_text),
        ),
      ],
    );
  }
}
