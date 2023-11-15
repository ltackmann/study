import 'package:flutter/material.dart';

class FloatingActionButtonExample extends StatefulWidget {
  const FloatingActionButtonExample({super.key});

  @override
  State<FloatingActionButtonExample> createState() =>
      _FloatingActionButtonExampleState();
}

class _FloatingActionButtonExampleState
    extends State<FloatingActionButtonExample> {
  String _text = "press button";

  @override
  Widget build(BuildContext context) {
    return Row(
      children: [
        FloatingActionButton(
          onPressed: () {
            setState(() {
              _text = "Pressed FloatingActionButton";
            });
          },
          child: Icon(Icons.add),
        ),
        Center(
          child: Text(_text),
        ),
      ],
    );
  }
}
