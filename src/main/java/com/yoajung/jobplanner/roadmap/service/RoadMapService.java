package com.yoajung.jobplanner.roadmap.service;

import com.yoajung.jobplanner.roadmap.dto.response.WholeRoadMapResponseDTO;
import com.yoajung.jobplanner.roadmap.dto.response.YearRoadMapResponseDTO;
import reactor.core.publisher.Mono;

public interface RoadMapService {
    Mono<WholeRoadMapResponseDTO> requestWholeRoadMap(String job);
    Mono<YearRoadMapResponseDTO> requestRoadMap(String job);
}
