package com.predict.ai.service;

import org.springframework.http.ResponseEntity;

public interface ResultService {
    ResponseEntity<byte[]> findByID(Long id);
}
