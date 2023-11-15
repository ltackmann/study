import 'package:flutter/material.dart';
import 'package:gallery/display_widgets/image/image_asset_example.dart';
import 'package:gallery/display_widgets/image/image_file_example.dart';
import 'package:gallery/display_widgets/image/image_network_example.dart';
import 'package:gallery/widgets/gallery_widget.dart';

class ImageExamples extends StatelessWidget {
  const ImageExamples({super.key});

  @override
  Widget build(BuildContext context) {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        GalleryWidget(
          description: "Image",
          examples: [
            (
              sampleWidget: ImageAssetExample(),
              sampleText: "Image: AssetImage"
            ),
            (
              sampleWidget: ImageNetworkExample(),
              sampleText: "Image: NetworkImage"
            ),
            (
              sampleWidget: ImageFileExample(),
              sampleText: "Image: FileImage (device only)"
            ),
          ],
        ),
      ],
    );
  }
}
