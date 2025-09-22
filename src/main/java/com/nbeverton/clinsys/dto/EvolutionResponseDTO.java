package com.nbeverton.clinsys.dto;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class EvolutionResponseDTO {

    private Long id;
    private String content;
    private Long patientId;
    private Long authorId;
    private String authorName;
    private Instant createdAt;
    private Instant updatedAt;
    private Long appointmentId;

}
