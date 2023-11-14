import 'package:flutter/material.dart';

class TextExample1 extends StatelessWidget {
  const TextExample1({super.key});

  @override
  Widget build(BuildContext context) {
    return SizedBox(
      width: 150, // set size to force line break
      child: Text(
        "Long sample text so we can see how it wraps, aligns and truncates",
      ),
    );
  }
}
