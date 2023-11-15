import 'package:flutter/material.dart';
import 'package:gallery/layout_widgets/fitted_box/box_fit_cover_example.dart';
import 'package:gallery/layout_widgets/fitted_box/box_fit_fill_example.dart';
import 'package:gallery/widgets/gallery_widget.dart';

class FittedBoxExamples extends StatelessWidget {
  const FittedBoxExamples({super.key});

  @override
  Widget build(BuildContext context) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        GalleryWidget(
          description: "FittedBox",
          examples: [
            (
              sampleWidget: BoxFitCoverExample(),
              sampleText: "fit: BoxFit.cover"
            ),
            (sampleWidget: BoxFitFillExample(), sampleText: "fit: BoxFit.fill"),
          ],
        ),
      ],
    );
  }
}
