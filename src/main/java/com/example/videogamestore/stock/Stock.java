package com.example.videogamestore.stock;

import com.example.videogamestore.common.ImplementId;
import com.example.videogamestore.warehouses.Warehouses;
import com.example.videogamestore.games.Games;
import jakarta.persistence.*;

@Entity
@Table(name = "stock")
public class Stock extends ImplementId {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @JoinColumn(name = "warehouse_id", nullable = false)
    private Warehouses warehouse;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Games game;

    private Integer quantity;

    public Stock() {
    }

    public Stock(Warehouses warehouse, Games game, Integer quantity) {
        this.warehouse = warehouse;
        this.game = game;
        this.quantity = quantity;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Warehouses getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(Warehouses warehouse) {
        this.warehouse = warehouse;
    }

    public Games getGame() {
        return game;
    }

    public void setGame(Games game) {
        this.game = game;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public boolean isStockAvailable(int requiredQuantity) {
        return this.quantity >= requiredQuantity;
    }

    public void reduceStock(int quantityToReduce) {
        if (quantityToReduce > this.quantity) {
            throw new RuntimeException("Not enough stock available!");
        }
        this.quantity -= quantityToReduce;
    }

}
