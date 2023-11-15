import 'package:flutter/material.dart';
import 'package:gallery/layout_widgets/fitted_box_examples.dart';

class LayoutWidgetsPage extends StatelessWidget {
  const LayoutWidgetsPage({super.key});

  @override
  Widget build(BuildContext context) {
    return Row(
      children: [
        FittedBoxExamples(),
      ],
    );
  }
}
