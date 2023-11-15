import 'package:flutter/material.dart';

class ImageNetworkExample extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Image(
      image: NetworkImage("https://picsum.photos/250?image=9"),
    );
  }
}
