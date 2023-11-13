import 'package:flutter/material.dart';
import 'package:gallery/display_widgets/text_align/text_align_center_example.dart';
import 'package:gallery/display_widgets/text_align/text_align_end_example.dart';
import 'package:gallery/display_widgets/text_align/text_align_justify_example.dart';
import 'package:gallery/display_widgets/text_align/text_align_default_example.dart';
import 'package:gallery/display_widgets/text_align/text_align_start_example.dart';

class TextSamples extends StatefulWidget {
  const TextSamples({super.key});

  @override
  State<TextSamples> createState() => _TextSamplesState();
}

class _TextSamplesState extends State<TextSamples> {
  var samples = [
    (widget: TextAlignDefaultExample(), ex: "textAlign: default"),
    (widget: TextAlignCenterExample(), ex: "textAlign: TextAlign.center"),
    (widget: TextAlignEndExample(), ex: "textAlign: TextAlign.end"),
    (widget: TextAlignJustifyExample(), ex: "textAlign: TextAlign.justify"),
    (widget: TextAlignStartExample(), ex: "textAlign: TextAlign.start"),
  ];
  late Widget _current = samples.first.widget;

  @override
  Widget build(BuildContext context) {
    return Row(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        Padding(
          padding: EdgeInsets.all(8.0),
          child: Text("Text align examples"),
        ),
        Padding(
          padding: EdgeInsets.all(8.0),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: samples.map((sample) {
              return GestureDetector(
                onTap: () {
                  setState(() {
                    _current = sample.widget;
                  });
                },
                child: Text(
                  sample.ex,
                  style: TextStyle(
                    color:
                        (_current == sample.widget) ? Colors.red : Colors.black,
                  ),
                ),
              );
            }).toList(),
          ),
        ),
        Padding(
          padding: EdgeInsets.all(8.0),
          child: Container(
            child: _current,
          ),
        ),
      ],
    );
  }
}
