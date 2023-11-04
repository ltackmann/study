import 'package:flutter/material.dart';

class SampleWidget extends StatelessWidget {
  final String description;

  const SampleWidget(this.description, {super.key});

  @override
  Widget build(BuildContext context) {
    return Row(
      children: [Text(description)],
    );
  }
}
