package com.s1.Controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class chatbotcontrollerTest {

    @Test
    void parsesGeminiReply() {
        String response = """
                {
                  "candidates": [{
                    "content": {
                      "parts": [{"text": "Consider your interests and entrance exam."}]
                    }
                  }]
                }
                """;

        assertEquals("Consider your interests and entrance exam.",
                chatbotcontroller.parseResponse(200, response));
    }

    @Test
    void reportsQuotaErrorsInsteadOfTryingToParseThemAsReplies() {
        String response = """
                {"error":{"message":"Quota exceeded"}}
                """;

        String result = chatbotcontroller.parseResponse(429, response);

        assertTrue(result.contains("quota or rate limit"));
        assertTrue(result.contains("Quota exceeded"));
    }

    @Test
    void reportsMissingCandidateResponse() {
        assertEquals("Gemini returned no answer. Please try rephrasing your question.",
                chatbotcontroller.parseResponse(200, "{}"));
    }
}
