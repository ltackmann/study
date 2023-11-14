import 'package:flutter/material.dart';

class ImageAssetExample extends StatelessWidget {
  const ImageAssetExample({super.key});

  @override
  Widget build(BuildContext context) {
    return Image(
      image: AssetImage("web/icons/Icon-192.png"),
    );
  }
}
