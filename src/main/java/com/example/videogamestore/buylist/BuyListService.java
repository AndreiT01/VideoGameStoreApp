package com.example.videogamestore.buylist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuyListService {

    private final BuyListRepository buyListRepository;

    @Autowired
    public BuyListService(BuyListRepository buyListRepository) {
        this.buyListRepository = buyListRepository;
    }

    public List<BuyList> getBuyListForUser(Integer userId) {
        return buyListRepository.findByUserId(userId);
    }

    public void addToBuyList(BuyList buyList) {
        buyListRepository.save(buyList);
    }
}
