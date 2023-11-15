import 'package:flutter/material.dart';

class TextExample2 extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return SizedBox(
      width: 150, // set size to force line break
      child: Text(
        "Long sample text so we can see how it wraps, aligns and truncates",
        maxLines: 2,
      ),
    );
  }
}
