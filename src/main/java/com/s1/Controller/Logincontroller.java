package com.s1.Controller;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Logincontroller {

    private final TextField emailField;
    private final PasswordField passwordField;
    private final javafx.scene.text.Text errorText;
    private final Button loginButton;
    private final Runnable onLoginSuccess;

    private static final String API_KEY = "AIzaSyByMwB2mdEpU5qQ7n5LTJNLYD__L07XMTo";

    public Logincontroller(TextField emailField,PasswordField passwordField,Text errorText,Button loginButton, Runnable onLoginSuccess)
    {
        this.emailField = emailField;
        this.passwordField = passwordField;
        this.errorText = errorText;
        this.loginButton = loginButton;
        this.onLoginSuccess = onLoginSuccess;

        attachLoginHandler();
    }

    private void attachLoginHandler() {
        loginButton.setOnAction(this::handleLogin);
    }

    private void handleLogin(ActionEvent event) {
        String email = emailField.getText().trim();
        String password = passwordField.getText().trim();

        if (email.isEmpty() || password.isEmpty()) {
            errorText.setText("Please enter both email and password.");
            return;
        }

        String loginResult = attemptFirebaseLogin(email, password);
        switch (loginResult) {
            case "SUCCESS":
                errorText.setText("");
                onLoginSuccess.run();
                break;
            case "EMAIL_NOT_FOUND":
                errorText.setText("No account found for this email.");
                break;
            case "INVALID_PASSWORD":
                errorText.setText("Incorrect password.");
                break;
            case "INVALID_EMAIL":
                errorText.setText("Invalid email format.");
                break;
            case "USER_DISABLED":
                errorText.setText("This user account has been disabled.");
                break;
            default:
                errorText.setText("Login failed. Please try again.");
        }

         emailField.clear();
         passwordField.clear();
    }

    /**
     * @return "SUCCESS", "EMAIL_NOT_FOUND", "INVALID_PASSWORD", "USER_DISABLED", "INVALID_EMAIL", or "FAILED"
     */
    private String attemptFirebaseLogin(String email, String password) {
        try {
            URI uri = URI.create("https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=" + API_KEY);
            HttpURLConnection conn = (HttpURLConnection) uri.toURL().openConnection();

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            JsonObject payload = new JsonObject();
            payload.addProperty("email", email);
            payload.addProperty("password", password);
            payload.addProperty("returnSecureToken", true);

            try (OutputStream os = conn.getOutputStream()) {
                os.write(payload.toString().getBytes(StandardCharsets.UTF_8));
            }

            int responseCode = conn.getResponseCode();
            if (responseCode == 200) {
                String responseBody;
                try (Scanner scanner = new Scanner(conn.getInputStream(), StandardCharsets.UTF_8)) {
                    responseBody = scanner.useDelimiter("\\A").next();
                }
                JsonObject authResponse = JsonParser.parseString(responseBody).getAsJsonObject();
                if (!authResponse.has("idToken") || !authResponse.has("localId")) {
                    return "FAILED";
                }
                FirebaseSession.setCredentials(
                        authResponse.get("idToken").getAsString(),
                        authResponse.get("localId").getAsString()
                );
                Controller.email1 = email;
                return "SUCCESS";
            } else {
                // Error from Firebase
                String errorBody;
                try (Scanner scanner = new Scanner(conn.getErrorStream())) {
                    errorBody = scanner.useDelimiter("\\A").next();
                }
                if (errorBody.contains("EMAIL_NOT_FOUND")) return "EMAIL_NOT_FOUND";
                if (errorBody.contains("INVALID_PASSWORD")
                        || errorBody.contains("INVALID_LOGIN_CREDENTIALS")) return "INVALID_PASSWORD";
                if (errorBody.contains("USER_DISABLED")) return "USER_DISABLED";
                if (errorBody.contains("INVALID_EMAIL")) return "INVALID_EMAIL";
                return "FAILED";
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "FAILED";
        }
    }
}
