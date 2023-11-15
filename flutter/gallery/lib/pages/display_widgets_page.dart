import 'package:flutter/material.dart';
import 'package:gallery/display_widgets/icon_examples.dart';
import 'package:gallery/display_widgets/image_examples.dart';
import 'package:gallery/display_widgets/text_examples.dart';

class DisplayWidgetsPage extends StatelessWidget {
  const DisplayWidgetsPage({super.key});

  @override
  Widget build(BuildContext context) {
    return Row(
      children: [
        IconExamples(),
        ImageExamples(),
        TextExamples(),
      ],
    );
  }
}
