import 'package:flutter/material.dart';
import 'package:gallery/widgets/sample_widget.dart';

class ImageSamples extends StatelessWidget {
  const ImageSamples({super.key});

  @override
  Widget build(BuildContext context) {
    return SampleWidget(
      "Image",
      sample: Image(
        image: AssetImage("web/icons/Icon-192.png"),
      ),
    );
  }
}
