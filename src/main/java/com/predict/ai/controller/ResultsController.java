package com.predict.ai.controller;

import com.predict.ai.service.ResultService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/results")
@AllArgsConstructor
public class ResultsController {

    private ResultService resultService;

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> findById(@PathVariable Long id) {
        return resultService.findByID(id);
    }

}
