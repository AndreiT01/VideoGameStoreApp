package com.example.videogamestore.buylist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/buylist")
public class BuyListController {

    private final BuyListService buyListService;

    @Autowired
    public BuyListController(BuyListService buyListService) {
        this.buyListService = buyListService;
    }

    @GetMapping("/{userId}")
    public List<BuyList> getBuyListForUser(@PathVariable Integer userId) {
        return buyListService.getBuyListForUser(userId);
    }

    @PostMapping
    public void addToBuyList(@RequestBody BuyList buyList) {
        buyListService.addToBuyList(buyList);
    }
}
