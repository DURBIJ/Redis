package com.example.fampay.service;


import com.example.fampay.dao.KeyValueRepository;
import com.example.fampay.domain.KeyValueEntity;
import com.example.fampay.storage.InMemoryKeyValueStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KeyValueService {

    private final InMemoryKeyValueStore store = new InMemoryKeyValueStore();

    @Autowired
    private KeyValueRepository repository;

//    public KeyValueService(KeyValueRepository repository) {
//        this.repository = repository;
//    }

    public void save(String key, String value, long expiryTime) {
        KeyValueEntity entity = new KeyValueEntity();
        entity.setKey(key);
        entity.setValue(value);
        entity.setExpiryTime(expiryTime);

        repository.save(entity);
    }
    public void getAllKeyValueEntity(){
        List<KeyValueEntity> lst=repository.findAll();

        for (KeyValueEntity e : lst) {
            System.out.println(
                    "Key = " + e.getKey() +
                            ", Value = " + e.getValue() +
                            ", Expiry = " + e.getExpiryTime()
            );
        }
    }

    public void set(String key, String value, long ttl) {
        store.set(key, value, ttl);
        save(key,value,ttl);
    }

    public String get(String key) {
        getAllKeyValueEntity();
        return store.get(key);
    }

    public boolean delete(String key) {
        return store.delete(key);
    }

    // ✅ UPDATE (only if key exists & not expired)
    public boolean update(String key, String newValue, long ttl) {
        String existingValue = store.get(key);

        if (existingValue == null) {
            return false;
        }

        store.set(key, newValue, ttl);
        return true;
    }
}
