import 'package:flutter/material.dart';
import 'package:gallery/widgets/sample_widget.dart';

class TextSamples extends StatelessWidget {
  final String _sampleText = '''
    Long sample text so we can see how it wraps, aligns and truncates
  ''';
  const TextSamples({super.key});

  @override
  Widget build(BuildContext context) {
    return SampleWidget(
      "Text",
      description: "For displaying and formatting text",
      sample: Column(
        children: [
          SampleWidget(
            "standard text",
            sample: Text(_sampleText),
          ),
          SampleWidget(
            "text align start",
            sample: Text(
              _sampleText,
              textAlign: TextAlign.start,
            ),
          ),
          SampleWidget(
            "text align center",
            sample: Text(
              _sampleText,
              textAlign: TextAlign.center,
            ),
          ),
          SampleWidget(
            "text align justify",
            sample: Text(
              _sampleText,
              textAlign: TextAlign.justify,
            ),
          ),
          SampleWidget(
            "text align end",
            sample: Text(
              _sampleText,
              textAlign: TextAlign.end,
            ),
          ),
          SampleWidget(
            "styled text",
            sample: Text(
              _sampleText,
              style: TextStyle(
                color: Colors.red,
                fontSize: 16,
              ),
            ),
          ),
        ],
      ),
    );
  }
}
