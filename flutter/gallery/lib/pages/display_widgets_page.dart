import 'package:flutter/material.dart';
import 'package:gallery/widgets/sample_widget.dart';

class DisplayWidgetsPage extends StatelessWidget {
  const DisplayWidgetsPage({super.key});

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [SampleWidget("Image"), SampleWidget("Text")],
    );
  }
}
