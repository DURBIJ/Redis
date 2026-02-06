package com.example.fampay.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class main {

    public static void main(String[] args) {

        User u1 = new User("1", "Rahul","xyz@gmail.com");
        User u2 = new User("2", "Amit", "Abc@gmail.com");
        User u3 = new User("3", "Neha", "mno@gmail.com");

        List<User>members=new ArrayList<>();
        members.add(u1);
        members.add(u2);
        members.add(u3);


        Group trip = new Group("101", "Goa Trip",members);



        Expense expense = new Expense(
                "expenseId",
                u1,
                5000,
                ExpenseType.EQUAL,
                Arrays.asList(u1, u2, u3)
        );
        splitwiseService.addExpense(expense);



    }
}
