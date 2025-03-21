package com.example.videogamestore.platform;

import com.example.videogamestore.common.ImplementId;
import jakarta.persistence.*;
import java.util.Set;
import com.example.videogamestore.games.Games;

@Entity
@Table(name = "platform")
public class Platform extends ImplementId {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    public Platform() {
    }

    public Platform(String name) {
        this.name = name;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
