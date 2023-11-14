import 'package:flutter/material.dart';
import 'package:gallery/old/app_state.dart';
import 'package:gallery/app_layout.dart';
import 'package:provider/provider.dart';

void main() {
  runApp(GalleryApp());
}

class GalleryApp extends StatelessWidget {
  const GalleryApp({super.key});

  @override
  Widget build(BuildContext context) {
    return ChangeNotifierProvider(
      create: (context) => AppState(),
      child: MaterialApp(
        title: 'Gallery App',
        theme: ThemeData(
          useMaterial3: true,
          colorScheme: ColorScheme.fromSeed(seedColor: Colors.deepOrange),
        ),
        home: AppLayoutWidget(),
      ),
    );
  }
}
