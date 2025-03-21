package com.example.videogamestore;

import com.example.videogamestore.games.Games;
import com.example.videogamestore.games.GamesRepository;
import com.example.videogamestore.games.GamesService;
import com.example.videogamestore.genre.Genre;
import com.example.videogamestore.orders.Orders;
import com.example.videogamestore.orders.OrdersRepository;
import com.example.videogamestore.orders.OrdersService;
import com.example.videogamestore.platform.Platform;
import com.example.videogamestore.stock.Stock;
import com.example.videogamestore.stock.StockRepository;
import com.example.videogamestore.user.Users;
import com.example.videogamestore.user.UsersRepository;
import com.example.videogamestore.user.UsersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class VideoGameStoreServiceTest {

	// Mock repositories
	@Mock
	private UsersRepository usersRepository;

	@Mock
	private GamesRepository gamesRepository;

	@Mock
	private OrdersRepository ordersRepository;

	@Mock
	private StockRepository stockRepository;

	@InjectMocks
	private UsersService usersService;

	@InjectMocks
	private GamesService gamesService;

	private OrdersService ordersService;

	// Sample data for users, games, and orders
	private Users user;
	private Games game1;
	private Stock stock;
	private Orders order;

	private Genre genre = new Genre("Action");
	private Platform platform = new Platform("PlayStation");

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);  // Initialize mocks

		user = new Users("alice", "alice@example.com");
		game1 = new Games("The Last of Us", genre, platform);
		stock = new Stock(null, game1, 10);
		order = new Orders(user, game1, 2);

		ordersService = new OrdersService(ordersRepository, stockRepository, usersService);
	}

	// Tests for UsersService

	@Test
	public void testAddNewUser_Success() {
		when(usersRepository.findUsersByEmail(user.getEmail())).thenReturn(Optional.empty());
		usersService.addNewUser(user);
		verify(usersRepository, times(1)).save(user);
	}

	@Test
	public void testAddNewUser_ThrowsException_WhenEmailExists() {
		when(usersRepository.findUsersByEmail(user.getEmail())).thenReturn(Optional.of(user));
		RuntimeException exception = assertThrows(RuntimeException.class, () -> {
			usersService.addNewUser(user);
		});
		assertEquals("User with email alice@example.com already exists", exception.getMessage());
	}

	@Test
	public void testDeleteUser_Success() {
		when(usersRepository.existsById(user.getId())).thenReturn(true);
		usersService.deleteUser(user.getId());
		verify(usersRepository, times(1)).deleteById(user.getId());
	}

	// Tests for GamesService

	@Test
	public void testGetAllGames() {
		when(gamesRepository.findAll()).thenReturn(Arrays.asList(game1));
		List<Games> gamesList = gamesService.getAllGames();
		assertEquals(1, gamesList.size());
	}

	@Test
	public void testAddGame_Success() {
		gamesService.addGame(game1);
		verify(gamesRepository, times(1)).save(game1);
	}

	// Tests for OrdersService

	@Test
	public void testPlaceOrder_Success() {
		when(usersService.findUserByEmail(user.getEmail())).thenReturn(Optional.of(user));
		when(stockRepository.findByGameId(game1.getId())).thenReturn(Optional.of(stock));

		ordersService.placeOrder(order);

		verify(stockRepository, times(1)).save(stock);
		verify(ordersRepository, times(1)).save(order);
		assertEquals(8, stock.getQuantity()); // Stock should reduce by 2
	}

	@Test
	public void testPlaceOrder_ThrowsException_WhenUserDoesNotExist() {
		when(usersService.findUserByEmail(user.getEmail())).thenReturn(Optional.empty());

		RuntimeException exception = assertThrows(RuntimeException.class, () -> {
			ordersService.placeOrder(order);
		});

		assertEquals("User does not exist. Please register first.", exception.getMessage());
		verify(ordersRepository, never()).save(order);
	}

	@Test
	public void testPlaceOrder_ThrowsException_WhenStockNotFound() {
		when(usersService.findUserByEmail(user.getEmail())).thenReturn(Optional.of(user));
		when(stockRepository.findByGameId(game1.getId())).thenReturn(Optional.empty());

		RuntimeException exception = assertThrows(RuntimeException.class, () -> {
			ordersService.placeOrder(order);
		});

		assertEquals("Stock not found for game ID: " + game1.getId(), exception.getMessage());
		verify(ordersRepository, never()).save(order);
	}

	@Test
	public void testPlaceOrder_ThrowsException_WhenNotEnoughStock() {
		when(usersService.findUserByEmail(user.getEmail())).thenReturn(Optional.of(user));
		stock.setQuantity(1);
		when(stockRepository.findByGameId(game1.getId())).thenReturn(Optional.of(stock));

		RuntimeException exception = assertThrows(RuntimeException.class, () -> {
			ordersService.placeOrder(order);
		});

		assertEquals("Not enough stock available for game: " + game1.getName(), exception.getMessage());
		verify(ordersRepository, never()).save(order);
	}
}
