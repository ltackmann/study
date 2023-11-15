import 'package:flutter/material.dart';
import 'package:gallery/interaction_widgets/button/elevated_button_example.dart';
import 'package:gallery/interaction_widgets/button/floating_action_button_example.dart';
import 'package:gallery/interaction_widgets/button/icon_button_example.dart';
import 'package:gallery/interaction_widgets/button/outlined_button_example.dart';
import 'package:gallery/interaction_widgets/button/text_button_example.dart';
import 'package:gallery/widgets/gallery_widget.dart';

class ButtonExamples extends StatelessWidget {
  const ButtonExamples({super.key});

  @override
  Widget build(BuildContext context) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        GalleryWidget(
          description: "Buttons",
          examples: [
            (
              sampleWidget: ElevatedButtonExample(),
              sampleText: "ElevatedButton"
            ),
            (
              sampleWidget: FloatingActionButtonExample(),
              sampleText: "FloatingActionButton"
            ),
            (sampleWidget: IconButtonExample(), sampleText: "IconButton"),
            (
              sampleWidget: OutlinedButtonExample(),
              sampleText: "OutlinedButton"
            ),
            (sampleWidget: TextButtonExample(), sampleText: "TextButton"),
          ],
        ),
      ],
    );
  }
}
