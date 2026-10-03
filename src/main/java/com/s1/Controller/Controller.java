package com.s1.Controller;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Scanner;

import com.s1.Dao.SignUpdao;
import com.s1.Model.SignUpModel;
import com.s1.view.Signup;

public class Controller {

    private final TextField nameField;
    private final TextField emailField;
    private final PasswordField passwordField;
    private final PasswordField conpasswordField;
    private final Button signupBtn;
    public static String name;
    public static String email1;

    private static final String API_KEY = "AIzaSyByMwB2mdEpU5qQ7n5LTJNLYD__L07XMTo" ;

    public Controller(TextField nameField, TextField emailField, PasswordField passwordField,
                      PasswordField conpasswordField, Button signupBtn) {
        this.nameField = nameField;
        this.emailField = emailField;
        this.passwordField = passwordField;
        this.conpasswordField = conpasswordField;
        this.signupBtn = signupBtn;

        attachHandlers();
    }

    private void attachHandlers() {
        signupBtn.setOnAction(this::handleSignUp);
    }

    private void handleSignUp(ActionEvent event) {
        String username = nameField.getText().trim();
        String email = emailField.getText().trim();
        String password = passwordField.getText().trim();
        String confirmPassword = conpasswordField.getText().trim();

        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            showAlert(Alert.AlertType.WARNING, "Please fill in all fields.");
            return;
        }

        if (!password.equals(confirmPassword)) {
            showAlert(Alert.AlertType.ERROR, "Passwords do not match.");
            return;
        }

        boolean success = firebaseSignup(email, password);

        if (success) {
            showAlert(Alert.AlertType.INFORMATION, "Sign up successful!");
            // nameField.clear();
            // emailField.clear();
            // passwordField.clear();
            // conpasswordField.clear();
            
                SignUpModel signUpModel = new SignUpModel(nameField.getText().trim(),emailField.getText().trim(),password);

                name=nameField.getText();
                email1=emailField.getText();

                
                Map<String,String> data = signUpModel.getMap();
                data.remove("password");
                SignUpdao.addSignUpData(data);

        } else {
            showAlert(Alert.AlertType.ERROR, "Sign up failed! Email already registered?");
        }
    }


    private boolean firebaseSignup(String email, String password) {
        try {
            URL url = new URL("https://identitytoolkit.googleapis.com/v1/accounts:signUp?key=" + API_KEY);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            conn.setDoOutput(true);

            JsonObject payload = new JsonObject();
            payload.addProperty("email", email);
            payload.addProperty("password", password);
            payload.addProperty("returnSecureToken", true);

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = payload.toString().getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            int responseCode = conn.getResponseCode();
            // 200 = success
            if (responseCode == 200) {
                try (Scanner scanner = new Scanner(conn.getInputStream())) {
                    String responseBody = scanner.useDelimiter("\\A").next();
                    JsonObject authResponse = JsonParser.parseString(responseBody).getAsJsonObject();
                    if (!authResponse.has("idToken") || !authResponse.has("localId")) {
                        return false;
                    }
                    FirebaseSession.setCredentials(
                            authResponse.get("idToken").getAsString(),
                            authResponse.get("localId").getAsString()
                    );
                }
                return true;
            } else {
                try (Scanner scanner = new Scanner(conn.getErrorStream())) {
                    String errorMessage = scanner.useDelimiter("\\A").next();
                    System.out.println("Firebase error: " + errorMessage);
                }
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void showAlert(Alert.AlertType type, String message) {
        Alert alert = new Alert(type);
        alert.setTitle("Signup");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
