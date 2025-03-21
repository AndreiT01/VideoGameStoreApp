package com.example.videogamestore.buylist;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BuyListRepository extends JpaRepository<BuyList, Integer> {
    List<BuyList> findByUserId(Integer userId);
}
