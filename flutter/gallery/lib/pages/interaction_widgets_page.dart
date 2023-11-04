import 'package:flutter/material.dart';
import 'package:gallery/widgets/sample_widget.dart';

class InteractionWidgetsPage extends StatelessWidget {
  const InteractionWidgetsPage({super.key});

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        SampleWidget("ElevatedButton"),
        SampleWidget("ElevatedButton.icon")
      ],
    );
  }
}
