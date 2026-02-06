package com.example.fampay.test;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Group {
    String groupId;
    String name;
    List<User> members;
}
