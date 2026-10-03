package com.s1.Controller;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.function.Consumer;

public class chatbotcontroller {

    private static final String API_KEY_ENV = "GETMYCOLLEGE_GEMINI_API_KEY";
    private static final String GEMINI_ENDPOINT =
            "https://generativelanguage.googleapis.com/v1beta/models/gemini-2.5-flash:generateContent";
    private static final HttpClient HTTP_CLIENT = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(15))
            .build();

    public void callChatbotAPI(String message, Consumer<String> responseHandler) {
        if (message == null || message.isBlank()) {
            responseHandler.accept("Please enter a question.");
            return;
        }

        String apiKey = System.getenv(API_KEY_ENV);
        if (apiKey == null || apiKey.isBlank()) {
            responseHandler.accept("Counseling chat is not configured. Set the "
                    + API_KEY_ENV + " environment variable to a Gemini API key.");
            return;
        }

        try {
            JsonObject textPart = new JsonObject();
            textPart.addProperty("text", message);
            JsonArray parts = new JsonArray();
            parts.add(textPart);

            JsonObject content = new JsonObject();
            content.add("parts", parts);
            JsonArray contents = new JsonArray();
            contents.add(content);

            JsonObject payload = new JsonObject();
            payload.add("contents", contents);

            URI uri = URI.create(GEMINI_ENDPOINT + "?key="
                    + URLEncoder.encode(apiKey, StandardCharsets.UTF_8));
            HttpRequest request = HttpRequest.newBuilder(uri)
                    .timeout(Duration.ofSeconds(60))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(payload.toString()))
                    .build();

            HTTP_CLIENT.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                    .thenApply(response -> parseResponse(response.statusCode(), response.body()))
                    .thenAccept(responseHandler)
                    .exceptionally(error -> {
                        Throwable cause = error.getCause() == null ? error : error.getCause();
                        responseHandler.accept("Could not reach Gemini: " + cause.getMessage());
                        return null;
                    });
        } catch (RuntimeException error) {
            responseHandler.accept("Could not send counseling request: " + error.getMessage());
        }
    }

    static String parseResponse(int statusCode, String json) {
        JsonObject response;
        try {
            response = JsonParser.parseString(json).getAsJsonObject();
        } catch (RuntimeException error) {
            return "Gemini returned an unreadable response (HTTP " + statusCode + ").";
        }

        if (statusCode < 200 || statusCode >= 300) {
            JsonObject apiError = response.has("error") && response.get("error").isJsonObject()
                    ? response.getAsJsonObject("error")
                    : null;
            String message = apiError != null && apiError.has("message")
                    ? apiError.get("message").getAsString()
                    : "No error details were returned.";
            return switch (statusCode) {
                case 400 -> "Gemini rejected the request (HTTP 400): " + message;
                case 401, 403 -> "Gemini API key is invalid or lacks permission (HTTP "
                        + statusCode + "): " + message;
                case 429 -> "Gemini quota or rate limit reached (HTTP 429): " + message;
                default -> "Gemini request failed (HTTP " + statusCode + "): " + message;
            };
        }

        JsonArray candidates = response.has("candidates") && response.get("candidates").isJsonArray()
                ? response.getAsJsonArray("candidates")
                : null;
        if (candidates != null && !candidates.isEmpty()) {
            JsonObject candidate = candidates.get(0).getAsJsonObject();
            JsonObject content = candidate.has("content") && candidate.get("content").isJsonObject()
                    ? candidate.getAsJsonObject("content")
                    : null;
            JsonArray parts = content != null && content.has("parts")
                    && content.get("parts").isJsonArray()
                    ? content.getAsJsonArray("parts")
                    : null;

            if (parts != null) {
                StringBuilder answer = new StringBuilder();
                for (var part : parts) {
                    if (part.isJsonObject() && part.getAsJsonObject().has("text")) {
                        answer.append(part.getAsJsonObject().get("text").getAsString());
                    }
                }
                if (!answer.isEmpty()) {
                    return answer.toString();
                }
            }
        }

        JsonObject promptFeedback = response.has("promptFeedback")
                && response.get("promptFeedback").isJsonObject()
                ? response.getAsJsonObject("promptFeedback")
                : null;
        if (promptFeedback != null && promptFeedback.has("blockReason")) {
            return "Gemini could not answer because the request was blocked: "
                    + promptFeedback.get("blockReason").getAsString();
        }
        return "Gemini returned no answer. Please try rephrasing your question.";
    }
}
