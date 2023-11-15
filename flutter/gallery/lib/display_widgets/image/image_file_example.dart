import 'package:flutter/material.dart';

class ImageFileExample extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Text(
      """
      Image(image: FileImage(File.png))
      // devices only as browsers provide no IO file api
    """,
    );
  }
}
