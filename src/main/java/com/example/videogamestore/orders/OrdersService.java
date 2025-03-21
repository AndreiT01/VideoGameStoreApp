package com.example.videogamestore.orders;

import com.example.videogamestore.stock.Stock;
import com.example.videogamestore.stock.StockRepository;
import com.example.videogamestore.user.Users;
import com.example.videogamestore.user.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class OrdersService {

    private final OrdersRepository ordersRepository;
    private final StockRepository stockRepository;
    private final UsersService usersService;

    @Autowired
    public OrdersService(OrdersRepository ordersRepository, StockRepository stockRepository, UsersService usersService) {
        this.ordersRepository = ordersRepository;
        this.stockRepository = stockRepository;
        this.usersService = usersService;

    }

    @Transactional
    public void placeOrder(Orders order) {
        //Check if user exists and associate it with the order
        Optional<Users> existingUser = usersService.findUserByEmail(order.getUser().getEmail());

        if (existingUser.isPresent()) {
            order.setUser(existingUser.get());
        } else {
            throw new RuntimeException("User does not exist. Please register first.");
        }


        // Check stock availability
        Optional<Stock> stockOptional = stockRepository.findByGameId(
                order.getGame().getId());

        if (stockOptional.isEmpty()) {
            throw new RuntimeException("Stock not found for game ID: " + order.getGame().getId());
        }

        Stock stock = stockOptional.get();
        if (!stock.isStockAvailable(order.getQuantity())) {
            throw new RuntimeException("Not enough stock available for game: " + order.getGame().getName());
        }

        // Reduce stock
        stock.reduceStock(order.getQuantity());
        stockRepository.save(stock);

        // Save the order
        ordersRepository.save(order);
    }
}
