class User {
  final int id;
  final String userName;
  final String email;

  User({required this.id, required this.userName, required this.email});

  // Convert JSON data to User object
  factory User.fromJson(Map<String, dynamic> json) {
    return User(
      id: json['id'],
      userName: json['userName'],
      email: json['email'],
    );
  }

  // Convert User object to JSON format
  Map<String, dynamic> toJson() {
    return {
      'userName': userName,
      'email': email,
    };
  }
}
