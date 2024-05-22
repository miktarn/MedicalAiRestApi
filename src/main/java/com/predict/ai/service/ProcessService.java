package com.predict.ai.service;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface ProcessService {
    ResponseEntity<byte[]> performPredictOnImage(Long userId, MultipartFile file);
}
