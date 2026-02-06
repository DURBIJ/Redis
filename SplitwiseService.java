package com.example.fampay.test;

import java.util.HashMap;
import java.util.Map;

public class SplitwiseService {

    BalanceSheet balanceSheet;

    public SplitwiseService() {
        balanceSheet = new BalanceSheet();
    }

    public void addExpense(Expense expense) {
        User paidBy = expense.paidBy;

        for (Split split : expense.splits) {
            User user = split.user;
            double amount = split.amount;

            if (!user.equals(paidBy)) {
                updateBalance(paidBy, user, amount);
            }
        }
    }

    private void updateBalance(User paidBy, User user, double amount) {

    // paidBy -> user
    Map<User, Double> paidByMap = balanceSheet.balanceMap.get(paidBy);
    if (paidByMap == null) {
        paidByMap = new HashMap<>();
        balanceSheet.balanceMap.put(paidBy, paidByMap);
    }
    paidByMap.put(user, paidByMap.getOrDefault(user, 0.0) + amount);

    // user -> paidBy (negative balance)
    Map<User, Double> userMap = balanceSheet.balanceMap.get(user);
    if (userMap == null) {
        userMap = new HashMap<>();
        balanceSheet.balanceMap.put(user, userMap);
    }
    userMap.put(paidBy, userMap.getOrDefault(paidBy, 0.0) - amount);
}


}
