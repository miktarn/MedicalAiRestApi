package com.predict.ai.model.dto.response;

import java.time.LocalDateTime;

public record ResultResponseDto(
        Long id,
        Long userId,
        String imageName,
        String mediaType,
        LocalDateTime processedDat
) {
}
