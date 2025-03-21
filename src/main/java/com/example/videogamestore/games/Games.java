package com.example.videogamestore.games;

import com.example.videogamestore.common.ImplementId;
import com.example.videogamestore.developers.Developers;
import com.example.videogamestore.genre.Genre;
import com.example.videogamestore.platform.Platform;
import jakarta.persistence.*;

@Entity
@Table
public class Games extends ImplementId {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "genre_id", nullable = false)
    private Genre genre;

    @ManyToOne
    @JoinColumn(name = "platform_id", nullable = false)
    private Platform platform;

    @ManyToOne
    @JoinColumn(name = "developer_id", nullable = false)
    private Developers developer;

    public Games() {}

    public Games(String name, Genre genre, Platform platform) {
        this.name = name;
        this.genre = genre;
        this.platform = platform;
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

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }
}
