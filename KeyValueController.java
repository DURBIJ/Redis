package com.example.fampay.controller;


import com.example.fampay.service.KeyValueService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/kv")
public class KeyValueController {

    private final KeyValueService service;

    public KeyValueController(KeyValueService service) {
        this.service = service;
    }

    // SET key value ttl
    @PostMapping("/set")
    public String set(
            @RequestParam String key,
            @RequestParam String value,
            @RequestParam(required = false, defaultValue = "0") long ttl
    ) {
        service.set(key, value, ttl);
        return "OK";
    }

    // GET key
    @GetMapping("/get")
    public String get(@RequestParam String key) {
        String value = service.get(key);
        return value != null ? value : "NULL";
    }

    // DEL key
    @DeleteMapping("/delete")
    public String delete(@RequestParam String key) {
        return service.delete(key) ? "DELETED" : "NOT FOUND";
    }

    // UPDATE API
    @PutMapping("/update")
    public String update(
            @RequestParam String key,
            @RequestParam String value,
            @RequestParam(required = false, defaultValue = "0") long ttl
    ) {
        boolean updated = service.update(key, value, ttl);
        return updated ? "UPDATED" : "KEY NOT FOUND";
    }

}

