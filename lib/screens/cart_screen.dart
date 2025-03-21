import 'package:flutter/material.dart';
import '../services/models/game.dart';

class CartScreen extends StatefulWidget {
  final List<Game> cart;

  const CartScreen({super.key, required this.cart});

  @override
  _CartScreenState createState() => _CartScreenState();
}

class _CartScreenState extends State<CartScreen> {
  List<Game> cartItems = [];

  @override
  void initState() {
    super.initState();
    cartItems = List.from(widget.cart);
  }

  // Remove item from cart
  void _removeFromCart(Game game) {
    setState(() {
      cartItems.remove(game);
    });
    ScaffoldMessenger.of(context).showSnackBar(
      SnackBar(content: Text('${game.name} removed from cart')),
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Your Cart'),
        centerTitle: true,
        backgroundColor: Colors.green,
        elevation: 2,
      ),
      body: cartItems.isEmpty
          ? _buildEmptyCart()
          : _buildCartList(),
    );
  }

  // Widget for displaying empty cart
  Widget _buildEmptyCart() {
    return const Center(
      child: Column(
        mainAxisAlignment: MainAxisAlignment.center,
        children: [
          Icon(Icons.shopping_cart_outlined, size: 80, color: Colors.grey),
          SizedBox(height: 16),
          Text(
            'Your cart is empty!',
            style: TextStyle(fontSize: 18, fontWeight: FontWeight.w500, color: Colors.grey),
          ),
        ],
      ),
    );
  }

  // Widget for displaying the cart items
  Widget _buildCartList() {
    return Padding(
      padding: const EdgeInsets.symmetric(horizontal: 16, vertical: 8),
      child: ListView.builder(
        itemCount: cartItems.length,
        itemBuilder: (context, index) {
          final game = cartItems[index];
          return Card(
            elevation: 3,
            shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(12)),
            margin: const EdgeInsets.symmetric(vertical: 8),
            child: ListTile(
              contentPadding: const EdgeInsets.all(12),
              title: Text(
                game.name,
                style: const TextStyle(fontWeight: FontWeight.bold, fontSize: 18),
              ),
              subtitle: Text('${game.genre} - ${game.platform}'),
              trailing: IconButton(
                icon: const Icon(Icons.remove_circle, color: Colors.red),
                onPressed: () => _removeFromCart(game),
              ),
            ),
          );
        },
      ),
    );
  }
}
