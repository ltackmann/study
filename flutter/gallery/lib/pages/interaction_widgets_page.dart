import 'package:flutter/material.dart';
import 'package:gallery/interaction_widgets/button_examples.dart';

class InteractionWidgetsPage extends StatelessWidget {
  const InteractionWidgetsPage({super.key});

  @override
  Widget build(BuildContext context) {
    return Row(
      children: [
        ButtonExamples(),
      ],
    );
  }
}
