import 'dart:convert';
import 'package:http/http.dart' as http;
import 'models/game.dart';
import 'models/user.dart';

class ApiService {
  final String baseUrl = 'http://localhost:8080'; // For Chrome emulator

  // User Methods 
  Future<List<User>> getUsers() async {
    final response = await http.get(Uri.parse('$baseUrl/users'));

    if (response.statusCode == 200) {
      List<dynamic> data = json.decode(response.body);
      return data.map((userJson) => User.fromJson(userJson)).toList();
    } else {
      throw Exception('Failed to load users');
    }
  }

  Future<void> addUser(User user) async {
    final response = await http.post(
      Uri.parse('$baseUrl/users'),
      headers: <String, String>{'Content-Type': 'application/json'},
      body: json.encode(user.toJson()),
    );

    if (response.statusCode != 201) {
      throw Exception('Failed to add user');
    }
  }

  Future<void> deleteUser(int userId) async {
    final response = await http.delete(
      Uri.parse('$baseUrl/users/$userId'),
    );

    if (response.statusCode != 200) {
      throw Exception('Failed to delete user');
    }
  }

  //  Game Methods
  Future<List<Game>> getGames() async {
    final response = await http.get(Uri.parse('$baseUrl/games'));

    if (response.statusCode == 200) {
      List<dynamic> data = json.decode(response.body);
      return data.map((gameJson) => Game.fromJson(gameJson)).toList();
    } else {
      throw Exception('Failed to load games');
    }
  }

  Future<void> addGame(Game game) async {
    final response = await http.post(
      Uri.parse('$baseUrl/games'),
      headers: <String, String>{'Content-Type': 'application/json'},
      body: json.encode(game.toJson()),
    );

    if (response.statusCode != 201) {
      throw Exception('Failed to add game');
    }
  }

  Future<void> deleteGame(int gameId) async {
    final response = await http.delete(
      Uri.parse('$baseUrl/games/$gameId'),
    );

    if (response.statusCode != 200) {
      throw Exception('Failed to delete game');
    }
  }

  //  Order Methods 
  Future<void> placeOrder({
    required String email, 
    required int gameId,
    required int quantity, required String userName,
  }) async {
    final response = await http.post(
      Uri.parse('$baseUrl/orders/placeOrder'),
      headers: <String, String>{'Content-Type': 'application/json'},
      body: jsonEncode({
        'user': {'email': email.toLowerCase()}, 
        'game': {'id': gameId},
        'quantity': quantity,
      }),
    );

    if (response.statusCode != 200) {
      throw Exception('Failed to place order');
    }
  }
}
