package com.yoajung.jobplanner.roadmap.service;

import com.yoajung.jobplanner.roadmap.dto.response.JobValidationResponseDTO;
import reactor.core.publisher.Mono;

public interface JobValidationService {
    Mono<JobValidationResponseDTO> validateJob(String job);
}
