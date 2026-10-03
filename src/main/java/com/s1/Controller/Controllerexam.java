package com.s1.Controller;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.s1.view.Enggexams;
import com.s1.view.Home;

import javafx.application.Platform;
import javafx.scene.control.TextArea;

public class Controllerexam {

    private static final String API_KEY = "AIzaSyByMwB2mdEpU5qQ7n5LTJNLYD__L07XMTo";
    private final String FIRESTORE_URL =
    "https://firestore.googleapis.com/v1/projects/getmycollge/databases/(default)/documents/"+Home.Eng+"UG"+"/"+Enggexams.Exam+"?key=AIzaSyByMwB2mdEpU5qQ7n5LTJNLYD__L07XMTo";


    public void fetchAndDisplay(String caste, String branch, double score, TextArea outputArea) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(FIRESTORE_URL))
                .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenAccept(json -> parseAndDisplay(json, caste, branch, score, outputArea))
                .exceptionally(e -> {
                    e.printStackTrace();
                    Platform.runLater(() -> outputArea.setText("Failed to fetch data."));
                    return null;
                });
    }

    private void parseAndDisplay(String json, String caste, String branch, double score, TextArea outputArea) {
    Gson gson = new Gson();
    JsonObject root = gson.fromJson(json, JsonObject.class);
    JsonObject fields = root.getAsJsonObject("fields");

    StringBuilder sb = new StringBuilder();
    boolean foundData = false;

    for (String college : fields.keySet()) {
        JsonObject collegeData = fields.getAsJsonObject(college)
                .getAsJsonObject("mapValue")
                .getAsJsonObject("fields");

        if (collegeData.has(caste)) {
            JsonObject casteData = collegeData.getAsJsonObject(caste)
                    .getAsJsonObject("mapValue")
                    .getAsJsonObject("fields");

            if (casteData.has(branch)) {
                JsonObject branchField = casteData.getAsJsonObject(branch);
                String value = branchField.get("stringValue").getAsString();
                double cutoff = Double.parseDouble(value);

                if (cutoff <= score) {
                    sb.append("College: ").append(college).append("\n")
                            .append("Branch: ").append(branch).append("\n")
                            .append("Cutoff: ").append(cutoff).append("\n")
                            .append("caste: ").append(caste).append("\n\n");
                    foundData = true;
                }
            }
        }
    }

    final String result = foundData ? sb.toString() : "No colleges found with cutoff matching " + score;
    Platform.runLater(() -> outputArea.setText(result));
}

}
