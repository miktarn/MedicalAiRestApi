package com.predict.ai.repository;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

@Component
public class AiRequestProcessor {
    @Value("${flask.url}")
    private final String flaskUrl;
    private final RestTemplate restTemplate;

    public AiRequestProcessor(@Value("${flask.url}") String flaskUrl, RestTemplate restTemplate) {
        this.flaskUrl = flaskUrl;
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<byte[]> performPredictOnImage(MultipartFile file) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("image", toByteArrayResource(file));

        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);

        return restTemplate.postForEntity(flaskUrl + "/upload-image", requestEntity, byte[].class);
    }

    private static ByteArrayResource toByteArrayResource(MultipartFile file) {
        byte[] bytes;
        try {
            bytes = file.getBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return new ByteArrayResource(bytes) {
            @Override
            public String getFilename() {
                return file.getOriginalFilename();
            }
        };
    }
}
