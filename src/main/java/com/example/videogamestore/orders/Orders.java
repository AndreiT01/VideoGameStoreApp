package com.example.videogamestore.orders;

import com.example.videogamestore.common.ImplementId;
import com.example.videogamestore.games.Games;
import com.example.videogamestore.user.Users;
import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Orders extends ImplementId {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne()
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Games game;

    private Integer quantity;

    public Orders() {}

    public Orders(Users user, Games game, Integer quantity) {
        this.user = user;
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

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
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
}
