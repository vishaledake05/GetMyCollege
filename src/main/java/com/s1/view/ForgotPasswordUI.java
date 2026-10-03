package com.s1.view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ForgotPasswordUI  {
    Scene forgotScene;
    Stage forgotStage;

    public void setForgotScene(Scene forgotScene) {
        this.forgotScene = forgotScene;
    }
    public void setForgotStage(Stage forgotStage) {
        this.forgotStage = forgotStage;
    }
    public VBox createforgotpage (Runnable back) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10); 
        grid.setVgap(12); 
        grid.setPadding(new Insets(25, 25, 25, 25));
        grid.setStyle("-fx-background-color: #F0F4F8;");

        Text scenetitle = new Text("Forgot Your Password?");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 24));
        scenetitle.setFill(Color.web("#333"));
        grid.add(scenetitle, 0, 0, 2, 1); 

        Label infoLabel = new Label("No problem. Enter your email address below and we'll send you a link to reset it.");
        infoLabel.setWrapText(true); 
        infoLabel.setFont(Font.font("Tahoma", FontWeight.NORMAL, 14));
        grid.add(infoLabel, 0, 1, 2, 1);

        Label emailLabel = new Label("Email:");
        emailLabel.setFont(Font.font("Tahoma", FontWeight.BOLD, 14));
        grid.add(emailLabel, 0, 2);

        TextField emailTextField = new TextField();
        emailTextField.setPromptText("Enter your email");
        emailTextField.setStyle(
            "-fx-font-size: 14px;" +
            "-fx-border-color: #BDBDBD;" +
            "-fx-border-radius: 5;" +
            "-fx-background-radius: 5;" +
            "-fx-padding: 8;"
        );
        grid.add(emailTextField, 1, 2);
        Button backButton = new Button("Back");
        backButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                back.run();
            }
            
        });

        Button sendLinkBtn = new Button("Send Reset Link");
        sendLinkBtn.setMaxWidth(Double.MAX_VALUE); 
        sendLinkBtn.setStyle(
            "-fx-background-color: #007BFF;" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 14px;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 5;" +
            "-fx-padding: 10 20;" +
            "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 5, 0, 0, 1);"
        );

        sendLinkBtn.setOnMouseEntered(e -> sendLinkBtn.setStyle(
            "-fx-background-color: #0056b3;" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 14px;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 5;" +
            "-fx-padding: 10 20;" +
            "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.3), 8, 0, 0, 2);"
        ));
        sendLinkBtn.setOnMouseExited(e -> sendLinkBtn.setStyle(
            "-fx-background-color: #007BFF;" +
            "-fx-text-fill: white;" +
            "-fx-font-size: 14px;" +
            "-fx-font-weight: bold;" +
            "-fx-background-radius: 5;" +
            "-fx-padding: 10 20;" +
            "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 5, 0, 0, 1);"
        ));


        final Label actiontarget = new Label();
        actiontarget.setTextFill(Color.GREEN);
        actiontarget.setFont(Font.font("Tahoma", FontWeight.NORMAL, 12));
        grid.add(actiontarget, 1, 4);

        VBox hbBtn = new VBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(sendLinkBtn);
        grid.add(hbBtn, 1, 3);

        sendLinkBtn.setOnAction(e -> {
            String email = emailTextField.getText();
            if (isValidEmail(email)) {
                
                System.out.println("Password reset link requested for: " + email);

                actiontarget.setTextFill(Color.GREEN);
                actiontarget.setText("If an account exists for " + email + ", a reset link has been sent.");
            } else {
                actiontarget.setTextFill(Color.RED);
                actiontarget.setText("Please enter a valid email address.");
            }
        });
       VBox root = new VBox(grid, backButton);
        root.setAlignment(Pos.CENTER);
        root.setSpacing(15);
        root.setPadding(new Insets(20));
        return root;

    }
    private boolean isValidEmail(String email) {
        if (email == null || email.isEmpty()) {
            return false;
        }
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailRegex);
    }

  
}
