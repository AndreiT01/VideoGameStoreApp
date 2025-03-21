package com.example.videogamestore.suppliers;

import com.example.videogamestore.common.ImplementId;
import com.example.videogamestore.warehouses.Warehouses;
import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "suppliers")
public class Suppliers extends ImplementId {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;

    @OneToMany(mappedBy = "supplier")
    private Set<Warehouses> warehouses;

    public Suppliers() {
    }

    public Suppliers(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Warehouses> getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(Set<Warehouses> warehouses) {
        this.warehouses = warehouses;
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
