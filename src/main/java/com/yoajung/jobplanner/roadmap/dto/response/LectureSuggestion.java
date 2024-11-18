package com.yoajung.jobplanner.roadmap.dto.response;

import java.util.List;

public record LectureSuggestion(List<Lecture> lectures) {
    public record Lecture(String name, String link, String section) {
    }
}
