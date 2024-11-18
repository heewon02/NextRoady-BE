package com.yoajung.jobplanner.roadmap.dto.response;

import java.util.List;

public record YearRoadMap(List<MonthPlan> monthPlans) {

    public record MonthPlan(Integer month, List<Content> contents) {
    }

    public record Content(String title, List<String> todos) {
    }
}
