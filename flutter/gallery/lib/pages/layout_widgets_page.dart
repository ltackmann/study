import 'package:flutter/material.dart';
import 'package:gallery/widgets/sample_widget.dart';

class LayoutWidgetsPage extends StatelessWidget {
  const LayoutWidgetsPage({super.key});

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [SampleWidget("Column"), SampleWidget("Row")],
    );
  }
}
