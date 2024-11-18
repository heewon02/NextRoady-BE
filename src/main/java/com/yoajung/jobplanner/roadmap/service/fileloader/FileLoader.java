package com.yoajung.jobplanner.roadmap.service.fileloader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import org.springframework.core.io.ClassPathResource;

public class FileLoader {
    public static String loadExpectedResponse(String filePath) {
        try {
            Path path = new ClassPathResource(filePath).getFile().toPath();
            return Files.readString(path);
        } catch (IOException ioException) {
            throw new RuntimeException(ioException.getMessage());
        }
    }
}
