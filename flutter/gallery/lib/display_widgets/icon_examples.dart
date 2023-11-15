import 'package:flutter/material.dart';
import 'package:gallery/display_widgets/icon/icon_example1.dart';
import 'package:gallery/widgets/gallery_widget.dart';

class IconExamples extends StatelessWidget {
  const IconExamples({super.key});

  @override
  Widget build(BuildContext context) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        GalleryWidget(
          description: "Icon",
          examples: [
            (sampleWidget: IconExample1(), sampleText: "Image: AssetImage"),
          ],
        ),
      ],
    );
  }
}
