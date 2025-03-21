class Game {
  final int id;
  final String name;
  final String genre;
  final String platform;

  Game({
    required this.id,
    required this.name,
    required this.genre,
    required this.platform,
  });

  // Convert JSON data to Game object
  factory Game.fromJson(Map<String, dynamic> json) {
    return Game(
      id: json['id'],
      name: json['name'],
      genre: json['genre']['name'], // Access nested "name" property
      platform: json['platform']['name'], // Access nested "name" property
    );
  }

  // Convert Game object to JSON format
  Map<String, dynamic> toJson() {
    return {
      'id': id,
      'name': name,
      'genre': genre,
      'platform': platform,
    };
  }
}
