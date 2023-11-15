import 'package:flutter/material.dart';

class IconButtonExample extends StatefulWidget {
  const IconButtonExample({super.key});

  @override
  State<IconButtonExample> createState() => _IconButtonExampleState();
}

class _IconButtonExampleState extends State<IconButtonExample> {
  String _text = "press button";

  @override
  Widget build(BuildContext context) {
    return Row(
      children: [
        IconButton(
          onPressed: () {
            setState(() {
              _text = "Pressed IconButton";
            });
          },
          icon: Icon(Icons.star),
        ),
        Center(
          child: Text(_text),
        ),
      ],
    );
  }
}
