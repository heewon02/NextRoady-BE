package com.yoajung.jobplanner.roadmap.service.parser;

import com.yoajung.jobplanner.roadmap.dto.response.LectureSuggestion;
import com.yoajung.jobplanner.roadmap.dto.response.LectureSuggestion.Lecture;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OnlineLectureParser {
    private static final Pattern lecturePattern = Pattern.compile("(.*) & (.*) \\| (.*)");

    public static LectureSuggestion parse(String rawInput) {
        Matcher matcher = lecturePattern.matcher(rawInput);
        List<Lecture> lectures = new ArrayList<>();
        while (matcher.find()) {
            String title = matcher.group(1).trim();
            String link = matcher.group(2).trim();
            String section = matcher.group(3).trim();
            lectures.add(new Lecture(title, link, section));
        }
        return new LectureSuggestion(lectures);
    }
}
