package com.yoajung.jobplanner.roadmap.service.fileloader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class FileLoader {
    public static String loadExpectedResponse(String filePath) {
        ClassLoader classLoader = FileLoader.class.getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(filePath)) {
            StringBuilder content = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(
                    new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    content.append(line).append(System.lineSeparator());
                }
            }
            return content.toString();
        } catch (IOException ioException) {
            throw new RuntimeException(ioException.getMessage());
        }
    }
}
