package com.example.fampay.dao;

import com.example.fampay.domain.KeyValueEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KeyValueRepository extends JpaRepository<KeyValueEntity, String> {
}