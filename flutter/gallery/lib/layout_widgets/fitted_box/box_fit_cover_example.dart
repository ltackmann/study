import 'package:flutter/material.dart';

class BoxFitCoverExample extends StatelessWidget {
  const BoxFitCoverExample({super.key});

  @override
  Widget build(BuildContext context) {
    return Container(
      height: 400,
      width: 300,
      color: Colors.blue,
      child: const FittedBox(
        fit: BoxFit.cover,
        child: Placeholder(),
      ),
    );
  }
}
