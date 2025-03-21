import 'package:flutter/material.dart';
import 'screens/register_screen.dart';

void main() {
  runApp(const VideoGameStoreApp());
}

class VideoGameStoreApp extends StatelessWidget {
  const VideoGameStoreApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      title: 'Video Game Store',
      theme: ThemeData(primarySwatch: Colors.green),
      home: const RegisterScreen(),
    );
  }
}

//  flutter run -d chrome --web-port=8000
