import 'package:flutter/material.dart';

class BoxFitFillExample extends StatelessWidget {
  const BoxFitFillExample({super.key});

  @override
  Widget build(BuildContext context) {
    return Container(
      height: 400,
      width: 300,
      color: Colors.blue,
      child: const FittedBox(
        fit: BoxFit.fill,
        child: Placeholder(),
      ),
    );
  }
}
