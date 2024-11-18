package com.yoajung.jobplanner.roadmap.controller;

import com.yoajung.jobplanner.roadmap.dto.request.JobValidationRequestDTO;
import com.yoajung.jobplanner.roadmap.dto.request.OutlookRequestDTO;
import com.yoajung.jobplanner.roadmap.dto.request.WholeRoadMapRequestDTO;
import com.yoajung.jobplanner.roadmap.dto.request.YearRoadMapRequestDTO;
import com.yoajung.jobplanner.roadmap.dto.response.JobValidationResponseDTO;
import com.yoajung.jobplanner.roadmap.dto.response.OutlookResponseDTO;
import com.yoajung.jobplanner.roadmap.dto.response.WholeRoadMapResponseDTO;
import com.yoajung.jobplanner.roadmap.dto.response.YearRoadMapResponseDTO;
import com.yoajung.jobplanner.roadmap.service.RoadMapService;
import com.yoajung.jobplanner.roadmap.service.impl.JobValidationServiceImpl;
import com.yoajung.jobplanner.roadmap.service.impl.OutlookServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/roadmap")
public class RoadMapController {

    private final RoadMapService roadMapService;
    private final OutlookServiceImpl outlookServiceImpl;
    private final JobValidationServiceImpl jobValidationServiceImpl;

    @PostMapping("/whole")
    public Mono<ResponseEntity<WholeRoadMapResponseDTO>> getWholeRoadMap(
            @RequestBody WholeRoadMapRequestDTO requestDTO) {
        Mono<WholeRoadMapResponseDTO> responseDTOMono = roadMapService.requestWholeRoadMap(requestDTO.job());
        return responseDTOMono.map(ResponseEntity::ok);
    }

    @PostMapping("/year")
    public Mono<ResponseEntity<YearRoadMapResponseDTO>> getYearRoadMap(@RequestBody YearRoadMapRequestDTO requestDTO) {
        Mono<YearRoadMapResponseDTO> yearRoadMapMono = roadMapService.requestRoadMap(requestDTO.job());
        return yearRoadMapMono.map(ResponseEntity::ok);
    }

    @PostMapping("outlook")
    public Mono<ResponseEntity<OutlookResponseDTO>> getOutlook(@RequestBody OutlookRequestDTO requestDTO) {
        Mono<OutlookResponseDTO> outlookResponseDTOMono = outlookServiceImpl.requestOutlookRequest(requestDTO.job());
        return outlookResponseDTOMono.map(ResponseEntity::ok);
    }

    @PostMapping("validate")
    public Mono<ResponseEntity<JobValidationResponseDTO>> validateJob(@RequestBody JobValidationRequestDTO requestDTO) {
        Mono<JobValidationResponseDTO> jobValidationResponseDTOMono = jobValidationServiceImpl.validateJob(
                requestDTO.job());
        return jobValidationResponseDTOMono.map(ResponseEntity::ok);
    }
}
