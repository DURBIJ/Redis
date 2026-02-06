package com.example.fampay.test;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Expense {
    String expenseId;
    User paidBy;
    double amount;
    ExpenseType expenseType;
    List<Split> splits;
}
