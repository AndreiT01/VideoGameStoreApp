package com.example.videogamestore.staff;

import com.example.videogamestore.user.Users;
import jakarta.persistence.*;

@Entity
@Table(name = "staff")
public class Staff extends Users {

    private String role;
    private String storeLocation;

    public Staff() {
    }

    public Staff(String userName, String email, String role, String storeLocation) {
        super(userName, email);
        this.role = role;
        this.storeLocation = storeLocation;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStoreLocation() {
        return storeLocation;
    }

    public void setStoreLocation(String storeLocation) {
        this.storeLocation = storeLocation;
    }
}
