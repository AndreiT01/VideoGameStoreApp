package com.example.videogamestore.buylist;

import com.example.videogamestore.common.ImplementId;
import jakarta.persistence.*;
import com.example.videogamestore.user.Users;
import com.example.videogamestore.games.Games;

@Entity
@Table(name = "buylist")
public class BuyList extends ImplementId {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @ManyToOne
    @JoinColumn(name = "game_id", nullable = false)
    private Games game;

    public BuyList() {
    }

    public BuyList(Users user, Games game) {
        this.user = user;
        this.game = game;
    }

    public Integer getId() {
        return id;
    }

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
}
