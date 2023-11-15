import 'package:flutter/material.dart';
import 'package:gallery/display_widgets/text/text_example1.dart';
import 'package:gallery/display_widgets/text/text_example2.dart';
import 'package:gallery/display_widgets/text/text_example3.dart';
import 'package:gallery/display_widgets/text/text_example4.dart';
import 'package:gallery/display_widgets/text/text_example5.dart';
import 'package:gallery/display_widgets/text/align/text_align_center_example.dart';
import 'package:gallery/display_widgets/text/align/text_align_end_example.dart';
import 'package:gallery/display_widgets/text/align/text_align_justify_example.dart';
import 'package:gallery/display_widgets/text/align/text_align_default_example.dart';
import 'package:gallery/display_widgets/text/align/text_align_start_example.dart';
import 'package:gallery/display_widgets/text/style/text_style_example1.dart';
import 'package:gallery/display_widgets/text/style/text_style_example2.dart';
import 'package:gallery/widgets/gallery_widget.dart';

class TextExamples extends StatelessWidget {
  const TextExamples({super.key});

  @override
  Widget build(BuildContext context) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        GalleryWidget(
          description: "Text",
          examples: [
            (sampleWidget: TextExample1(), sampleText: "Text: default"),
            (sampleWidget: TextExample2(), sampleText: "Text: maxLines 2"),
            (sampleWidget: TextExample3(), sampleText: "TextOverflow.clip"),
            (sampleWidget: TextExample4(), sampleText: "TextOverflow.ellipsis"),
            (sampleWidget: TextExample5(), sampleText: "TextOverflow.fade"),
          ],
        ),
        GalleryWidget(
          description: "TextStyle",
          examples: [
            (
              sampleWidget: TextStyleExample1(),
              sampleText: "TextStyle: color and font size"
            ),
            (
              sampleWidget: TextStyleExample2(),
              sampleText: "TextStyle: fontWeight bold"
            ),
          ],
        ),
        GalleryWidget(
          description: "TextAlign",
          examples: [
            (
              sampleWidget: TextAlignDefaultExample(),
              sampleText: "TextAlign: default"
            ),
            (
              sampleWidget: TextAlignCenterExample(),
              sampleText: "TextAlign: TextAlign.center"
            ),
            (
              sampleWidget: TextAlignEndExample(),
              sampleText: "TextAlign: TextAlign.end"
            ),
            (
              sampleWidget: TextAlignJustifyExample(),
              sampleText: "TextAlign: TextAlign.justify"
            ),
            (
              sampleWidget: TextAlignStartExample(),
              sampleText: "TextAlign: TextAlign.start"
            ),
          ],
        ),
      ],
    );
  }
}
