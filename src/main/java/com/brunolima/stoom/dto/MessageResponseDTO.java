package com.brunolima.stoom.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class MessageResponseDTO {
    private String message;
}
