import 'package:flutter/material.dart';

class OutlinedButtonExample extends StatefulWidget {
  const OutlinedButtonExample({super.key});

  @override
  State<OutlinedButtonExample> createState() => _OutlinedButtonExampleState();
}

class _OutlinedButtonExampleState extends State<OutlinedButtonExample> {
  String _text = "press button";

  @override
  Widget build(BuildContext context) {
    return Row(
      children: [
        OutlinedButton(
          onPressed: () {
            setState(() {
              _text = "Pressed OutlinedButton";
            });
          },
          child: Text("OutlinedButton"),
        ),
        Center(
          child: Text(_text),
        ),
      ],
    );
  }
}
