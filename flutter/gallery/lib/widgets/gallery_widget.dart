import 'package:flutter/material.dart';

class GalleryWidget extends StatefulWidget {
  GalleryWidget({required this.description, required this.examples, super.key});
  final String description;
  final List<({Widget sampleWidget, String sampleText})> examples;

  @override
  State<GalleryWidget> createState() => _GalleryWidgetState();
}

class _GalleryWidgetState extends State<GalleryWidget> {
  _GalleryWidgetState();
  late Widget _current = widget.examples.first.sampleWidget;

  @override
  Widget build(BuildContext context) {
    return Row(
      children: [
        Padding(
          padding: EdgeInsets.all(8.0),
          child: Text(widget.description),
        ),
        Padding(
          padding: EdgeInsets.all(8.0),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.start,
            children: widget.examples.map((sample) {
              return GestureDetector(
                onTap: () {
                  setState(() {
                    _current = sample.sampleWidget;
                  });
                },
                child: Text(
                  sample.sampleText,
                  style: TextStyle(
                    color: (_current == sample.sampleWidget)
                        ? Colors.red
                        : Colors.black,
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
