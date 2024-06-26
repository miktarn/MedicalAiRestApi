package com.predict.ai.controller;

import com.predict.ai.service.ProcessService;
import java.io.IOException;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/process")
@AllArgsConstructor
public class ProcessController {

    private final ProcessService processService;

    @PostMapping("/image")
    public ResponseEntity<byte[]> performPredictOnImage(@RequestParam("userId") Long userId,
                                                        @RequestParam("image") MultipartFile file) {
        if (file.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return processService.performPredictOnImage(userId, file);
    }

    @GetMapping("/test")
    public ResponseEntity<byte[]> getTestImage() {
        try {
            Resource resource = new ClassPathResource("data/teddyBear.jpg");
            byte[] imageBytes = resource.getInputStream().readAllBytes();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(org.springframework.http.MediaType.IMAGE_JPEG);
            headers.setContentLength(imageBytes.length);

            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
