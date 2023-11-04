import 'package:gallery/pages/display_widgets_page.dart';
import 'package:gallery/pages/gallery_page.dart';
import 'package:flutter/material.dart';
import 'package:gallery/pages/interaction_widgets_page.dart';
import 'package:gallery/pages/layout_widgets_page.dart';

class AppLayoutWidget extends StatefulWidget {
  @override
  State<AppLayoutWidget> createState() => _AppLayoutWidgetState();
}

class _AppLayoutWidgetState extends State<AppLayoutWidget> {
  var selectedIndex = 0; // ← Add this property.

  @override
  Widget build(BuildContext context) {
    Widget page;
    switch (selectedIndex) {
      case 0:
        page = GalleryPage();
        break;
      case 1:
        page = DisplayWidgetsPage();
        break;
      case 2:
        page = InteractionWidgetsPage();
        break;
      case 3:
        page = LayoutWidgetsPage();
        break;
      default:
        throw UnimplementedError('no widget for $selectedIndex');
    }

    return LayoutBuilder(builder: (context, constraints) {
      return Scaffold(
        body: Row(
          children: [
            SafeArea(
              child: NavigationRail(
                extended: constraints.maxWidth >= 600,
                destinations: [
                  NavigationRailDestination(
                    icon: Icon(Icons.home),
                    label: Text('Gallery home'),
                  ),
                  NavigationRailDestination(
                    icon: Icon(Icons.tv),
                    label: Text('Display widgets'),
                  ),
                  NavigationRailDestination(
                    icon: Icon(Icons.gesture),
                    label: Text('Interaction widgets'),
                  ),
                  NavigationRailDestination(
                    icon: Icon(Icons.auto_graph),
                    label: Text('Layout widgets'),
                  ),
                ],
                selectedIndex: selectedIndex,
                onDestinationSelected: (value) {
                  setState(() {
                    selectedIndex = value;
                  });
                },
              ),
            ),
            Expanded(
              child: Container(
                color: Theme.of(context).colorScheme.primaryContainer,
                child: page,
              ),
            ),
          ],
        ),
      );
    });
  }
}
