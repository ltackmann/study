import 'package:flutter/material.dart';
import 'package:gallery/interaction_widgets/elevated_button_samples.dart';

class InteractionWidgetsPage extends StatelessWidget {
  const InteractionWidgetsPage({super.key});

  @override
  Widget build(BuildContext context) {
    return Row(
      children: [
        ElevatedButtonSamples(),
        //SampleWidget("ElevatedButton"),
        //SampleWidget("ElevatedButton.icon"),
      ],
    );
  }
}
