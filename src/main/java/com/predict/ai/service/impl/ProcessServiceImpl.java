package com.predict.ai.service.impl;

import com.predict.ai.model.Result;
import com.predict.ai.model.User;
import com.predict.ai.repository.AiRequestProcessor;
import com.predict.ai.repository.ResultRepository;
import com.predict.ai.service.ProcessService;
import com.predict.ai.service.UserService;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@AllArgsConstructor
public class ProcessServiceImpl implements ProcessService {

    private UserService userService;
    private AiRequestProcessor requestProcessor;
    private ResultRepository resultRepository;

    @Override
    public ResponseEntity<byte[]> performPredictOnImage(Long userId, MultipartFile file) {
        if (!userService.isUserExists(userId)) {
            throw new RuntimeException("There is no user with id " + userId);
        }
        ResponseEntity<byte[]> response = requestProcessor.performPredictOnImage(file);
        if (response.getStatusCode() != HttpStatus.OK) {
            throw new RuntimeException("Something goes wrong. Response code "
                    + response.getStatusCode());
        }
        Result imageProcessingResult = Result.builder()
                .imageName("predicted_" + file.getOriginalFilename())
                .mediaType(response.getHeaders().getContentType().toString())
                .user(User.builder().id(userId).build())
                .imageData(response.getBody())
                .processedDate(LocalDateTime.now())
                .build();
        resultRepository.save(imageProcessingResult);
        return response;
    }

}
