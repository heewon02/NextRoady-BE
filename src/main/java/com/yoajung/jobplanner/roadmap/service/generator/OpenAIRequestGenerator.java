package com.yoajung.jobplanner.roadmap.service.generator;

import com.yoajung.jobplanner.openai.domain.OpenAIRequest;
import com.yoajung.jobplanner.roadmap.service.fileloader.FileLoader;

public class OpenAIRequestGenerator {
    private static final String responseSetting1 = "The request may contain a mix of Korean and English but response must be Korean.";
    private static final String responseSetting2 = "Do not add any characters other than the format I provided in the response.";
    private static final String responseSetting3 = """
            If the response format includes ---, anything after --- represents another example of the response format.
            The response format is separated by --- lines.
            """;
    private static final String responseSetting4 = "Please strictly adhere to the response format. The format is as follows: %s";

    public static OpenAIRequest generate(String userRequest, String formatPath) {
        String responseFormat = FileLoader.loadExpectedResponse(formatPath);

        OpenAIRequest openAIRequest = OpenAIRequest.create4oMiniRequest();
        openAIRequest.addSystemSetting(responseSetting1);
        openAIRequest.addSystemSetting(responseSetting2);
        openAIRequest.addSystemSetting(responseSetting3);
        openAIRequest.addSystemSetting(String.format(responseSetting4, responseFormat));
        openAIRequest.addUserRequest(userRequest);
        return openAIRequest;
    }
}
