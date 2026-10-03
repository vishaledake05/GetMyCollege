package com.s1.Controller;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.s1.view.Pg;
import com.s1.view.nextpage;


public class controllerpg {
    private static final String API_KEY = "AIzaSyByMwB2mdEpU5qQ7n5LTJNLYD__L07XMTo";
    private static final HttpClient HTTP_CLIENT = HttpClient.newHttpClient();

    public interface DataCallback {
        void onSuccess(List<nextpage> colleges);
        void onFailure(String error);
    }

    public void fetchCollegeDatapg(String caste, String branch, double score,
                                 DataCallback callback) {
        String stream = Pg.Engineeringpg;
        String exam = Pg.Exam;
        if (stream == null || stream.isBlank() || exam == null || exam.isBlank()) {
            callback.onFailure("Select a stream and exam before predicting.");
            return;
        }
        if (caste == null || caste.isBlank() || branch == null || branch.isBlank()) {
            callback.onFailure("Select a category and branch before predicting.");
            return;
        }

        String documentPath = encodePathSegment(stream + "PG") + "/" + encodePathSegment(exam);
        String firestoreUrl = "https://firestore.googleapis.com/v1/projects/getmycollge"
                + "/databases/(default)/documents/" + documentPath + "?key=" + API_KEY;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(firestoreUrl))
                .GET()
                .build();

        HTTP_CLIENT
            .sendAsync(
                request,
                HttpResponse.BodyHandlers.ofString()
            )
            .thenApply(response -> {
                if (response.statusCode() < 200 || response.statusCode() >= 300) {
                    throw new IllegalStateException(
                            "Firestore returned HTTP " + response.statusCode() + ": "
                                    + getFirestoreError(response.body()));
                }
                return response.body();
            })
            .thenApply(json -> parseData(json, caste, branch, score))
            .thenAccept(callback::onSuccess)
            .exceptionally(error -> {
                Throwable cause = error.getCause() == null ? error : error.getCause();
                callback.onFailure("Could not load prediction data: " + cause.getMessage());
                return null;
            });
    }

    public List<nextpage> parseData(String json, String caste, String branch, double score) {
        List<nextpage> list = new ArrayList<>();
        JsonObject root = JsonParser.parseString(json).getAsJsonObject();
        JsonObject fields = root.getAsJsonObject("fields");
        if (fields == null) {
            throw new IllegalArgumentException("Firestore response does not contain college fields.");
        }

        for (String collegeName : fields.keySet()) {
            JsonObject collegeField = fields.getAsJsonObject(collegeName);
            JsonObject collegeData = getMapFields(collegeField);
            if (!collegeData.has(caste)) {
                continue;
            }

            JsonObject casteData = getMapFields(collegeData.getAsJsonObject(caste));
            if (!casteData.has(branch)) {
                continue;
            }

            double cutoff = getNumericValue(casteData.getAsJsonObject(branch));
            if (cutoff <= score) {
                list.add(new nextpage(collegeName, branch, cutoff, caste));
            }
        }
        return list;
    }

    private static JsonObject getMapFields(JsonObject field) {
        if (field == null || !field.has("mapValue")) {
            throw new IllegalArgumentException("Unexpected Firestore map field.");
        }
        JsonObject mapValue = field.getAsJsonObject("mapValue");
        JsonObject fields = mapValue == null ? null : mapValue.getAsJsonObject("fields");
        if (fields == null) {
            throw new IllegalArgumentException("Firestore map is missing nested fields.");
        }
        return fields;
    }

    private static double getNumericValue(JsonObject field) {
        if (field == null) {
            throw new IllegalArgumentException("Firestore cutoff field is missing.");
        }
        if (field.has("stringValue")) {
            return Double.parseDouble(field.get("stringValue").getAsString());
        }
        if (field.has("doubleValue")) {
            return field.get("doubleValue").getAsDouble();
        }
        if (field.has("integerValue")) {
            return Double.parseDouble(field.get("integerValue").getAsString());
        }
        throw new IllegalArgumentException("Firestore cutoff is not a numeric value.");
    }

    private static String encodePathSegment(String value) {
        return URLEncoder.encode(value, StandardCharsets.UTF_8).replace("+", "%20");
    }

    private static String getFirestoreError(String responseBody) {
        try {
            JsonObject error = JsonParser.parseString(responseBody)
                    .getAsJsonObject()
                    .getAsJsonObject("error");
            if (error != null && error.has("message")) {
                return error.get("message").getAsString();
            }
        } catch (RuntimeException ignored) {
            // Fall through to the response body when Firebase returns a non-standard error.
        }
        return responseBody;
    }
}
