import 'package:flutter/material.dart';
import 'package:gallery/display_widgets/image_samples.dart';
import 'package:gallery/display_widgets/text_samples.dart';

class DisplayWidgetsPage extends StatelessWidget {
  const DisplayWidgetsPage({super.key});

  @override
  Widget build(BuildContext context) {
    return Row(
      children: [ImageSamples(), TextSamples()],
    );
  }
}
