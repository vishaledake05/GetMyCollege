package com.s1.view;

import com.s1.Controller.chatbotcontroller;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.*;
import javafx.stage.Stage;

public class Chatpage  {

    
Stage chatStage;
public void setChatStage(Stage chatStage) {
    this.chatStage = chatStage;
}

public void setChatScene(Scene chatScene) {
    this.chatScene = chatScene;
}

Scene chatScene;
VBox chatBox;
ScrollPane chatScroll;


    public VBox createChat(Runnable back) {       
    ImageView logo = new ImageView(new Image("Assets\\Images\\logo-removebg-preview.png"));
        logo.setFitHeight(80);
        logo.setFitWidth(200);

        TextField searchBar = new TextField();
        searchBar.setPromptText("Search Colleges");
        searchBar.setPrefWidth(300);
        searchBar.setStyle("-fx-background-radius: 5; -fx-padding: 8 10; -fx-font-size: 14px;");

        Button searchBtn = new Button("Search");
        searchBtn.setStyle("-fx-background-color: #f26722; -fx-text-fill: white; -fx-font-weight: bold;");
        searchBtn.setPrefHeight(30);
        searchBtn.setOnAction(e -> System.out.println("Search: " + searchBar.getText()));

        HBox searchBox = new HBox(10, searchBar, searchBtn);
        searchBox.setAlignment(Pos.CENTER_LEFT);

        Image profileImage = new Image("Assets\\Images\\home profie.png");
        ImageView profileImageView = new ImageView(profileImage);
        profileImageView.setFitHeight(45);
        profileImageView.setFitWidth(45);
        profileImageView.setOnMouseClicked(e -> System.out.println("Profile pressed"));

        Circle clip = new Circle(40, 40, 40); 
        profileImageView.setClip(clip);

        Circle border = new Circle(40, 40, 40);
        border.setStroke(Color.LIGHTGRAY);
        border.setStrokeWidth(2);
        border.setFill(null);

        StackPane avatar = new StackPane(border, profileImageView);
        avatar.setEffect(new DropShadow(4, Color.gray(0, 0.4)));

        Region spacer1 = new Region();
        spacer1.setPrefWidth(300);

        Region spacer2 = new Region();
        spacer2.setPrefWidth(400);

        HBox topRow = new HBox(20, logo, spacer1, searchBox, spacer2, avatar);
        topRow.setAlignment(Pos.CENTER_LEFT);
        topRow.setPadding(new Insets(10, 50, 10, 15));
        topRow.setBackground(new Background(new BackgroundFill(Color.web("#fff5ee"), CornerRadii.EMPTY, Insets.EMPTY)));

        Text chatTitle = new Text("Ask Your Queries");
        chatTitle.setFont(Font.font("Verdana", FontWeight.BOLD, 24));

        Button backButton = new Button("←");
        backButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                back.run();
            }
            
        });
        HBox backBox = new HBox(600, backButton, chatTitle);
        backBox.setAlignment(Pos.CENTER_LEFT);
        backBox.setPadding(new Insets(10));
        backBox.setBackground(new Background(
            new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
            

        Text liveChat = new Text("Live Chat");
        liveChat.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        liveChat.setFill(Color.GREEN);
        HBox liveChatBox = new HBox(liveChat);
        liveChatBox.setAlignment(Pos.CENTER);
        liveChatBox.setStyle("-fx-background-color: #f0eaff");
        liveChatBox.setPadding(new Insets(5, 10, 10, 10));

        Separator separator = new Separator();

        chatBox = new VBox(10);
        chatBox.setPadding(new Insets(10));
        chatBox.setBackground(Background.EMPTY);
    
        chatScroll = new ScrollPane(chatBox);
        chatScroll.setFitToWidth(true);
        chatScroll.setStyle("-fx-background: transparent; -fx-background-color: transparent;");
        VBox.setVgrow(chatScroll, Priority.ALWAYS);

        TextField inputField = new TextField();
        inputField.setPromptText("Type your message...");
        HBox.setHgrow(inputField, Priority.ALWAYS);

        Button sendBtn = new Button("➤");

        HBox bottomBar = new HBox(10, inputField, sendBtn);
        bottomBar.setPadding(new Insets(15));
        bottomBar.setAlignment(Pos.CENTER_LEFT);
        bottomBar.setStyle("-fx-background-color: rgba(242, 242, 242, 0.28);");

        VBox layout = new VBox(topRow,backBox, separator, liveChatBox, chatScroll, bottomBar);
        layout.setBackground(new Background(
            new BackgroundFill(Color.web("#f0eaff"), CornerRadii.EMPTY, Insets.EMPTY)));

        chatbotcontroller controller = new chatbotcontroller();

        Runnable sendMessage = () -> {
            String userInput = inputField.getText().trim();
            if (userInput.isEmpty()) return;

            displayMessage("You", userInput, Color.BLUE);
            inputField.clear();
            sendBtn.setDisable(true);

            controller.callChatbotAPI(userInput, response -> {
                Platform.runLater(() -> {
                    displayMessage("GetMyCollege", response, Color.DARKGREEN);
                    sendBtn.setDisable(false);
                    inputField.requestFocus();
                });
            });
        };
        sendBtn.setOnAction(e -> sendMessage.run());
        inputField.setOnAction(e -> sendMessage.run());
        return layout;
    }

    private void displayMessage(String sender, String message, Color color) {
        Text senderText = new Text(sender + ":");
        senderText.setFill(color.darker());
        senderText.setFont(Font.font("Arial", FontWeight.BOLD, 13));

        Text messageText = new Text(message);
        messageText.setFill(Color.BLACK);
        messageText.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        messageText.setWrappingWidth(600);

        VBox bubble = new VBox(3, senderText, messageText);
        bubble.setPadding(new Insets(10));
        bubble.setMaxWidth(700);
        bubble.setStyle(
            "-fx-background-color: " +
            (sender.equals("You") ? "rgba(209,231,255,0.9);" : "rgba(217,252,217,0.9);") +
            "-fx-background-radius: 12;" +
            "-fx-border-color: #cccccc;" +
            "-fx-border-radius: 12;" +
            "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 4, 0, 0, 2);"
        );

        HBox container = new HBox(bubble);
        container.setPadding(new Insets(5));
        container.setAlignment(sender.equals("You") ? Pos.CENTER_RIGHT : Pos.CENTER_LEFT);

        chatBox.getChildren().add(container);
        Platform.runLater(() -> chatScroll.setVvalue(1.0));
    }
}
