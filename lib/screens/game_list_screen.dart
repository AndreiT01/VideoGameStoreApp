// ignore_for_file: library_private_types_in_public_api

import 'package:flutter/material.dart';
import '../services/api_service.dart';
import '../services/models/game.dart';
import 'cart_screen.dart';

class GameListScreen extends StatefulWidget {
  final String userName;
  final String email;

  const GameListScreen({super.key, required this.userName, required this.email});

  @override
  _GameListScreenState createState() => _GameListScreenState();
}

class _GameListScreenState extends State<GameListScreen> {
  List<Game> games = [];
  List<Game> cart = [];
  bool isLoading = true;
  String errorMessage = '';

  @override
  void initState() {
    super.initState();
    _fetchGames();
  }

  // Fetch games from the API
  Future<void> _fetchGames() async {
    try {
      ApiService apiService = ApiService();
      List<Game> fetchedGames = await apiService.getGames();
      setState(() {
        games = fetchedGames;
        isLoading = false;
      });
    } catch (e) {
      setState(() {
        isLoading = false;
        errorMessage = 'Failed to load games. Please try again later.';
      });
    }
  }

  // Add a game to the cart and place an order
  Future<void> _addToCart(Game game) async {
    try {
      await ApiService().placeOrder(
        email: widget.email,
        gameId: game.id,
        quantity: 1,
        userName: widget.userName,
      );
      setState(() {
        if (!cart.contains(game)) {
          cart.add(game);
        }
      });
      if (!mounted) return;
      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(content: Text('${game.name} added to cart!')),
      );
    } catch (e) {
      ScaffoldMessenger.of(context).showSnackBar(
        SnackBar(content: Text('Failed to add to cart: ${e.toString()}')),
      );
    }
  }

  // Navigate to the cart screen
  void _viewCart() {
    Navigator.push(
      context,
      MaterialPageRoute(
        builder: (context) => CartScreen(cart: cart),
      ),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Welcome, ${widget.userName}'),
        centerTitle: true,
        backgroundColor: Colors.green,
        elevation: 2,
        actions: [
          Stack(
            children: [
              IconButton(
                icon: const Icon(Icons.shopping_cart),
                onPressed: _viewCart,
              ),
              if (cart.isNotEmpty)
                Positioned(
                  right: 8,
                  top: 8,
                  child: Container(
                    padding: const EdgeInsets.all(4),
                    decoration: const BoxDecoration(
                      color: Colors.red,
                      shape: BoxShape.circle,
                    ),
                    child: Text(
                      '${cart.length}',
                      style: const TextStyle(color: Colors.white, fontSize: 12),
                    ),
                  ),
                ),
            ],
          ),
        ],
      ),
      body: Padding(
        padding: const EdgeInsets.symmetric(horizontal: 16, vertical: 12),
        child: isLoading
            ? const Center(child: CircularProgressIndicator())
            : errorMessage.isNotEmpty
                ? Center(
                    child: Text(
                      errorMessage,
                      style: const TextStyle(fontSize: 16, color: Colors.red),
                      textAlign: TextAlign.center,
                    ),
                  )
                : ListView.builder(
                    itemCount: games.length,
                    itemBuilder: (context, index) {
                      final game = games[index];
                      return Card(
                        elevation: 3,
                        shape: RoundedRectangleBorder(
                          borderRadius: BorderRadius.circular(12),
                        ),
                        margin: const EdgeInsets.symmetric(vertical: 8),
                        child: ListTile(
                          contentPadding: const EdgeInsets.all(12),
                          title: Text(
                            game.name,
                            style: const TextStyle(
                              fontWeight: FontWeight.bold,
                              fontSize: 18,
                            ),
                          ),
                          subtitle: Text('${game.genre} - ${game.platform}'),
                          trailing: ElevatedButton.icon(
                            onPressed: () => _addToCart(game),
                            icon: const Icon(Icons.add, size: 18),
                            label: const Text("Add"),
                            style: ElevatedButton.styleFrom(
                              backgroundColor: Colors.green,
                              foregroundColor: Colors.white,
                              shape: RoundedRectangleBorder(
                                borderRadius: BorderRadius.circular(12),
                              ),
                            ),
                          ),
                        ),
                      );
                    },
                  ),
      ),
    );
  }
}
