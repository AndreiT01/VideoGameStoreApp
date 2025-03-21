# Video Game Store Web App  

This is a Full-Stack Web Application for a Video Game Store, built using Spring Boot for the backend and Flutter for the frontend. The application allows users to browse, buy, and manage video games, and supports  orders, inventory management, and supplier tracking.

## Features  

### Backend (Spring Boot)  
- User Management: Register, retrieve, and delete users.  
- Game Inventory: Add, view, and manage game stock.  
- Orders and Buy List: Place orders and manage wishlists.  
- Stock and Suppliers: Track stock levels and suppliers.  
- Security and CORS Configuration: Configured for seamless integration with the Flutter frontend.  

### Frontend (Flutter)  
- User Registration: New users can register via a simple form.  
- Game Listing: Browse available video games from the backend.  
- Cart System: Add games to the cart and place orders.  
- API Integration: Connects seamlessly with the backend via REST APIs.  

## Tech   

### Backend  
- Java (Spring Boot)  
- Spring Data JPA (for database management)  
- PostgreSQL (Database)  
- JUnit and Mockito (for testing)    

### Frontend  
- Flutter (Dart)  
- HTTP package (API calls)  
- Material UI Components  

## Project Structure  

### Backend (Spring Boot)
**Directory:** `src/main/java/com/example/videogamestore/`

- **games/** - Game Management  
- **users/** - User Management  
- **orders/** - Order Processing  
- **stock/** - Stock and Warehouses  
- **suppliers/** - Supplier Tracking  
- **buylist/** - User Wishlist (BuyList)  
- **platform/** - Platform Data  
- **genre/** - Game Genre Management  
- **staff/** - Store Staff  
- **common/** - Common Abstract Classes (ImplementId)  
- **config/** - CORS and Web Configuration
- **test/** - Unit Tests (`src/test/java/com/example/videogamestore/`)  


### Frontend (Flutter)
**Directory:** `/lib`

- **screens/** - UI Screens  
- **services/** - API Calls & Models  
- **models/** - Data Models (Game, User, BuyList item)  
- **main.dart** - App Entry Point  


## API Endpoints  

### User API  
- `GET /users` → Get all users  
- `POST /users` → Register a new user  
- `DELETE /users/{id}` → Delete a user  

### Game API  
- `GET /games` → Get all games  
- `POST /games` → Add a new game  

### Order API  
- `POST /orders/placeOrder` → Place an order  

### Buy List API  
- `GET /buylist/{userId}` → Get wishlist for user  
- `POST /buylist` → Add a game to wishlist  
### User Registration Screen
The **User Registration Screen** allows new users to sign up for the **Video Game Store** by providing their **name** and **email**.
It features visual feedback through **loading indicators and error messages**.

Once registered, users are directed to the **game browsing section**, where they can explore and purchase games.

![image](https://github.com/user-attachments/assets/e4720bec-da09-4021-b669-51d7d89fdaa6)

### Game List Screen

The **Game List Screen** allows users to browse available video games and add them to their cart.
It features **smooth user interaction**, allowing users to easily add games using the **"Add" button**.
Pressing the cart button navigates to the **Cart Screen**, where users can view and manage their selected games.

![image](https://github.com/user-attachments/assets/f1428cdb-ee0a-491c-bb2a-332e539d725a)

### Cart Screen

The **Cart Screen** displays the selected games that users have added from the Game List Screen.
It features - a **remove button** for each game, allowing users to remove items from the cart.
Users can return to the Game List Screen by pressing the **back button** in the top left corner.

![image](https://github.com/user-attachments/assets/5beddf09-39fd-4080-b454-42d9b0ef5329)



## Installation and Setup  

### Prerequisites  
- Install Java 17 and Spring Boot  
- Install Flutter SDK  
- Install PostgreSQL 
- Install the HTTP package for Flutter
- Install Spring Boot Web Starter
- Install Spring Boot Data JPA
- Spring Boot Starter Test

### Backend Setup  
```sh
# Clone the repository
git clone https://github.com/yourusername/videogame-store.git
cd videogame-store

# Configure database connection in application.properties

# Run the Spring Boot application
./mvnw spring-boot:run  (for Windows)
./mvnw spring-boot:run  (for Mac/Linux)

```
### Frontend Setup
```sh
# Navigate to the Flutter project
cd frontend/

# Install dependencies
flutter pub get

# Run the Flutter app
flutter run -d chrome --web-port=8000
```
