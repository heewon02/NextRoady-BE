package com.yoajung.jobplanner.roadmap.service;

import com.yoajung.jobplanner.roadmap.dto.response.OutlookResponseDTO;
import reactor.core.publisher.Mono;

public interface OutlookService {
    Mono<OutlookResponseDTO> requestOutlookRequest(String job);
}
