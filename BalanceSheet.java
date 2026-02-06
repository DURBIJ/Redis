package com.example.fampay.test;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class BalanceSheet {
    // user1 -> (user2 -> amount)
    Map<User, Map<User, Double>> balanceMap;

    public BalanceSheet() {
        balanceMap = new HashMap<>();
    }
}
