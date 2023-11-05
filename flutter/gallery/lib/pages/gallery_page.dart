import 'package:flutter/material.dart';

class GalleryPage extends StatelessWidget {
  const GalleryPage({super.key});

  @override
  Widget build(BuildContext context) {
    return Column(
      children: [
        Text(
          "Gallery of Flutter widgets",
          style: TextStyle(fontSize: 18),
        ),
        Row(
          children: [
            BigCard(
              title: "Display widgets",
              description: "For displaying content such as text or images",
            ),
            BigCard(
              title: "Interaction widgets",
              description: "For user interactions such as buttons and input",
            ),
          ],
        )
      ],
    );
  }
}

class BigCard extends StatelessWidget {
  const BigCard({super.key, required this.title, required this.description});
  final String title;
  final String description;

  @override
  Widget build(BuildContext context) {
    return Card(
        child: Padding(
            padding: EdgeInsets.all(8.0),
            child: Column(children: [
              Text(title,
                  style: TextStyle(fontSize: 18, fontWeight: FontWeight.bold)),
              Text(description, style: TextStyle(fontSize: 14))
            ])));
  }
}
