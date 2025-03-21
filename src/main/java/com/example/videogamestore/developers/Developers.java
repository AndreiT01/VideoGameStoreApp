package com.example.videogamestore.developers;

import com.example.videogamestore.common.ImplementId;
import jakarta.persistence.*;

@Entity
@Table(name = "developers")
public class Developers extends ImplementId {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    public Developers() {
    }

    public Developers(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
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
}
