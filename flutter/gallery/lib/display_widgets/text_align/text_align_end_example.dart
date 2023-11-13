import 'package:flutter/material.dart';

class TextAlignEndExample extends StatelessWidget {
  const TextAlignEndExample({super.key});

  @override
  Widget build(BuildContext context) {
    // use sized box to ensure text wraps, so we can see alignment
    return SizedBox(
      width: 150,
      child: Text(
        '''Long sample text so we can see how it wraps, aligns and truncates''',
        textAlign: TextAlign.end,
      ),
    );
  }
}
