package com.yoajung.jobplanner.roadmap.service.parser;

import com.yoajung.jobplanner.roadmap.dto.response.YearRoadMap;
import com.yoajung.jobplanner.roadmap.dto.response.YearRoadMap.Content;
import com.yoajung.jobplanner.roadmap.dto.response.YearRoadMap.MonthPlan;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class YearRoadMapParser {

    private static final Pattern phasePattern = Pattern.compile("\\$(.*?)\\$");
    private static final Pattern categoryPattern = Pattern.compile("#(.*?)#");
    private static final Pattern detailPattern = Pattern.compile("\\*(.*?)\\*");

    public static YearRoadMap parse(String rawString) {
        System.out.println(rawString);
        Matcher phaseMatcher = phasePattern.matcher(rawString);

        List<MonthPlan> monthPlans = new ArrayList<>();
        int monthCount = 3;

        while (phaseMatcher.find()) {
            String phaseContent = getPhaseContent(phasePattern, rawString, phaseMatcher.end());
            Matcher categoryMatcher = categoryPattern.matcher(phaseContent);

            List<Content> contents = new ArrayList<>();
            while (categoryMatcher.find()) {
                List<String> todos = new ArrayList<>();
                String currentCategory = categoryMatcher.group(1).trim();

                String categoryContent = getPhaseContent(categoryPattern, phaseContent, categoryMatcher.end());

                Matcher detailMatcher = detailPattern.matcher(categoryContent);
                while (detailMatcher.find()) {
                    todos.add(detailMatcher.group(1).trim());
                }
                contents.add(new Content(currentCategory, todos));
            }
            monthPlans.add(new MonthPlan(monthCount, contents));
            monthCount += 3;
        }
        return new YearRoadMap(monthPlans);
    }

    private static String getPhaseContent(Pattern pattern, String input, Integer startIndex) {
        Matcher matcher = pattern.matcher(input);
        matcher.region(startIndex, input.length());
        int endIndex = input.length();
        if (matcher.find()) {
            endIndex = matcher.start();
        }
        return input.substring(startIndex, endIndex).trim();
    }
}
