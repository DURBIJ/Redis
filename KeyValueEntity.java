package com.example.fampay.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "kv_store")
public class KeyValueEntity {

    @Id
    @Column(name = "kv_key")   // ✅ renamed column
    private String key;

    @Column(name = "kv_value")
    private String value;

    @Column(name = "expiry_time")
    private long expiryTime;

}
