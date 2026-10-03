package com.s1.view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

public class ContactUsPage {

    private ImageView profileImageView;
    private ImageView bigPhoto;

    Scene contact1Scene;
    Stage contact1Stage;

    public void setContact1Scene(Scene contact1Scene) {
        this.contact1Scene = contact1Scene;
    }

    public void setContact1Stage(Stage contact1Stage) {
        this.contact1Stage = contact1Stage;
    }

    public VBox createcontact(Runnable backRunnable){

        ImageView logo = new ImageView(new Image("Assets\\Images\\logo-removebg-preview.png"));
        logo.setFitHeight(80);
        logo.setFitWidth(200);

        TextField searchBar = new TextField();
        searchBar.setPromptText("Search Colleges");
        searchBar.setPrefWidth(300);
        searchBar.setStyle("-fx-background-radius: 8; -fx-padding: 8 10; -fx-font-size: 14px;");

        Button searchBtn = new Button("Search");
        searchBtn.setStyle("-fx-background-color: #f26722; -fx-text-fill: white; -fx-font-weight: bold;");
        searchBtn.setPrefHeight(30);

        HBox searchBox = new HBox(10, searchBar, searchBtn);
        searchBox.setAlignment(Pos.CENTER_LEFT);

        profileImageView = new ImageView(new Image("Assets\\Images\\asash.png"));
        profileImageView.setFitHeight(45);
        profileImageView.setFitWidth(45);

        Circle clip = new Circle(22.5, 22.5, 22.5);
        profileImageView.setClip(clip);

        Circle border = new Circle(22.5, 22.5, 22.5);
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
        topRow.setBackground(new Background(new BackgroundFill(Color.web("#9fafb1ff"), CornerRadii.EMPTY, Insets.EMPTY)));
        
        bigPhoto = new ImageView(new Image("Assets\\Images\\logo-removebg-preview.png"));
        bigPhoto.setFitHeight(120);
        bigPhoto.setFitWidth(120);
        //Circle clip1 = new Circle(50, 50, 50);
        //bigPhoto.setClip(clip1);

        Button backButton = new Button("Back");
        backButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                backRunnable.run();
            }
            
        });

        Label contactLabel = new Label("Contact Us");
        contactLabel.setStyle("-fx-font-size: 22px; -fx-font-weight: bold; -fx-text-fill: #333;");

        Label adminName = new Label("GetMyCollege-Admin");
        adminName.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");

        Label adminEmail = new Label("📧 getmycollege@gmail.com");
        Label adminPhone = new Label("📞 +91 9876543210");
        Label adminLocation = new Label("📍 Sinhgad Valley, Pune");

        for (Label label : new Label[]{adminEmail, adminPhone, adminLocation}) {
            label.setStyle("-fx-font-size: 16px; -fx-text-fill: #444;");
        }

        VBox card = new VBox(8);
        card.setAlignment(Pos.CENTER);
        card.setPadding(new Insets(30));
        card.setMaxWidth(450);
        card.setStyle("-fx-background-color: white; -fx-background-radius: 16;");
        card.setEffect(new DropShadow(10, Color.gray(0.3)));

        card.getChildren().addAll(contactLabel, bigPhoto,  adminName, adminEmail, adminPhone, adminLocation);

        StackPane centerPane = new StackPane(card);
        centerPane.setPadding(new Insets(30));

        VBox root = new VBox(topRow, backButton,centerPane);
        root.setBackground(new Background(new BackgroundFill(Color.web("#e8f0fe"), CornerRadii.EMPTY, Insets.EMPTY)));

        return root;
    }

    
}
