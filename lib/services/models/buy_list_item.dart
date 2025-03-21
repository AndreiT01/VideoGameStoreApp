class BuyList {
  final int id;
  final int userId;
  final List<int> gameIds; 

  BuyList({required this.id, required this.userId, required this.gameIds});

  // Convert JSON data to BuyList object
  factory BuyList.fromJson(Map<String, dynamic> json) {
    return BuyList(
      id: json['id'],
      userId: json['userId'],
      gameIds: List<int>.from(json['gameIds']),
    );
  }

  // Convert BuyList object to JSON format
  Map<String, dynamic> toJson() {
    return {
      'userId': userId,
      'gameIds': gameIds,
    };
  }
}
