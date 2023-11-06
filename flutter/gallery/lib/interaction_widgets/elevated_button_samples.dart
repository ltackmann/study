import 'package:flutter/material.dart';
import 'package:gallery/widgets/sample_widget.dart';

class ElevatedButtonSamples extends StatefulWidget {
  const ElevatedButtonSamples({super.key});

  @override
  State<ElevatedButtonSamples> createState() => _ElevatedButtonSamplesState();
}

class _ElevatedButtonSamplesState extends State<ElevatedButtonSamples> {
  var _selectedAlign = TextAlign.center;
  var examples = [
    (align: TextAlign.start),
    (align: TextAlign.center),
    (align: TextAlign.justify),
    (align: TextAlign.end),
  ];

  @override
  Widget build(BuildContext context) {
    return Row(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: examples.map((example) {
            return GestureDetector(
              onTap: () {
                setState(() {
                  _selectedAlign = example.align;
                });
              },
              child: Text(
                "textAlign: ${example.align}",
                style: TextStyle(
                  color: (_selectedAlign == example.align)
                      ? Colors.red
                      : Colors.black,
                ),
              ),
            );
          }).toList(),
        ),
        Container(
          decoration: BoxDecoration(
            border: Border.all(
              color: Colors.black,
            ),
          ),
          margin: EdgeInsets.all(8.0),
          width: 150,
          child: Text(
            '''Long sample text so we can see how it wraps, aligns and truncates''',
            textAlign: _selectedAlign,
          ),
        ),
      ],
    );
  }
}
