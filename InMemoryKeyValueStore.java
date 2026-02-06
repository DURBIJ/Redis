package com.example.fampay.storage;

import java.util.concurrent.ConcurrentHashMap;

public class InMemoryKeyValueStore {

    private final ConcurrentHashMap<String, ValueWrapper> store = new ConcurrentHashMap<>();

    // SET key value [ttl]
    public void set(String key, String value, long ttlMillis) {
        long expiryTime = ttlMillis > 0
                ? System.currentTimeMillis() + ttlMillis
                : -1;

        store.put(key, new ValueWrapper(value, expiryTime));
    }

    // GET key
    public String get(String key) {
        ValueWrapper wrapper = store.get(key);

        if (wrapper == null) {
            return null;
        }

        if (wrapper.isExpired()) {
            store.remove(key);
            return null;
        }

        return wrapper.getValue();
    }

    // DEL key
    public boolean delete(String key) {
        return store.remove(key) != null;
    }

    // UPDATE key value [ttl] (atomic & safe)
    // (Not required)
    public boolean update(String key, String value, long ttlMillis) {

        ValueWrapper oldWrapper = store.get(key);

        // key not present
        if (oldWrapper == null) {
            return false;
        }

        // key expired
        if (oldWrapper.isExpired()) {
            store.remove(key);
            return false;
        }

        long expiryTime;
        if (ttlMillis > 0) {
            expiryTime = System.currentTimeMillis() + ttlMillis;
        } else {
            expiryTime = -1; // no expiry
        }

        store.put(key, new ValueWrapper(value, expiryTime));
        return true;
    }

}
