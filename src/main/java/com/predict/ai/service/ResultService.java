package com.predict.ai.service;

import com.predict.ai.model.dto.response.ResultResponseDto;
import java.util.List;
import org.springframework.http.ResponseEntity;

public interface ResultService {
    ResponseEntity<byte[]> findByID(Long id);

    List<ResultResponseDto> findAllResultsByUserId(Long id);
}
