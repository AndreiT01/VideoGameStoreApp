package com.example.videogamestore.warehouses;

import com.example.videogamestore.common.ImplementId;
import com.example.videogamestore.suppliers.Suppliers;
import jakarta.persistence.*;

@Entity
@Table(name = "warehouses")
public class Warehouses extends ImplementId {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String location;

    @ManyToOne
    @JoinColumn(name = "supplier_id", nullable = false)
    private Suppliers supplier;

    public Warehouses() {
    }

    public Warehouses(String location, Suppliers supplier) {
        this.location = location;
        this.supplier = supplier;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Suppliers getSupplier() {
        return supplier;
    }

    public void setSupplier(Suppliers supplier) {
        this.supplier = supplier;
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
