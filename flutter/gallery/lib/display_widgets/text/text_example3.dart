import 'package:flutter/material.dart';

class TextExample3 extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return SizedBox(
      width: 150, // set size to force truncation
      child: Text(
        "Long sample text so we can see how it wraps, aligns and truncates",
        overflow: TextOverflow.clip,
        // disallow wrapping
        maxLines: 1,
        softWrap: false,
      ),
    );
  }
}
