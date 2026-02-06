package com.example.fampay.storage;

import lombok.Data;

@Data
public class ValueWrapper {

    private final String value;
    private final long expiryTime; // -1 means no expiry

    public ValueWrapper(String value, long expiryTime) {
        this.value = value;
        this.expiryTime = expiryTime;
    }


    public boolean isExpired() {
        return expiryTime != -1 && System.currentTimeMillis() > expiryTime;
    }
}

