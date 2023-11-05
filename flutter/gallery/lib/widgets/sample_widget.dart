import 'package:flutter/material.dart';

class SampleWidget extends StatelessWidget {
  final String title;
  final String? description;
  final Widget sample;

  const SampleWidget(
    this.title, {
    this.description,
    super.key,
    this.sample = const Placeholder(),
  });

  @override
  Widget build(BuildContext context) {
    return Container(
      decoration: BoxDecoration(
        border: Border.all(
          color: Colors.black,
        ),
      ),
      child: Column(
        children: [
          Text(title),
          Container(
            margin: EdgeInsets.all(8.0),
            width: 150,
            child: sample,
          ),
        ],
      ),
    );
  }
}
