package com.predict.ai.service.impl;

import com.predict.ai.model.Result;
import com.predict.ai.repository.ResultRepository;
import com.predict.ai.service.ResultService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ResultServiceImpl implements ResultService {

    private ResultRepository resultRepository;

    @Override
    public ResponseEntity<byte[]> findByID(Long id) {
        Result result = resultRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("no result with id " + id));
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.valueOf(result.getMediaType()));
        return new ResponseEntity<>(result.getImageData(), httpHeaders, HttpStatus.OK);
    }
}
