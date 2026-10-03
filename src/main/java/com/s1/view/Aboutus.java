package com.s1.view;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Aboutus  {

    
        Scene aboutusScene;
        Stage aboutusStage;
        
    public void setAboutusScene(Scene aboutusScene) {
            this.aboutusScene = aboutusScene;
        }

        public void setAboutusStage(Stage aboutusStage) {
            this.aboutusStage = aboutusStage;
        }

    ScrollPane aboutus(Runnable back) {

        Button backButton = new Button("Back");
        backButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                back.run();
            }
            
        });


        Text text = new Text("About-Us");
        text.setFill(Color.BLUEVIOLET);
        text.setFont(new Font(28));

        ImageView logo = new ImageView(new Image("Assets/images/logo-removebg-preview.png"));
        logo.setFitHeight(80);
        logo.setFitWidth(200);

        TextField searchBar = new TextField();
        searchBar.setPromptText("Search");
        searchBar.setPrefWidth(250);
        searchBar.setStyle("-fx-background-radius: 10; -fx-padding: 8 10; -fx-font-size: 14px;");

        Button searchBtn = new Button("Search");
        searchBtn.setStyle("-fx-background-color: #f26722; -fx-text-fill: white; -fx-font-weight: bold;");
        searchBtn.setPrefHeight(30);
        searchBtn.setOnAction(e -> System.out.println("Search: " + searchBar.getText()));

        Image profileImage = new Image("Assets/images/home profie.png");
        ImageView profileImageView = new ImageView(profileImage);
        profileImageView.setFitHeight(45);
        profileImageView.setFitWidth(45);
        profileImageView.setClip(new Circle(22.5, 22.5, 22.5));
        StackPane avatar = new StackPane(profileImageView);
        avatar.setEffect(new DropShadow(4, Color.gray(0, 0.4)));

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        
        Region spacer1 = new Region();
        HBox.setHgrow(spacer1, Priority.ALWAYS);

        


        HBox topBar = new HBox(20, logo, spacer, searchBar, searchBtn,spacer1, avatar);
        topBar.setPadding(new Insets(15));
        topBar.setAlignment(Pos.CENTER_LEFT);
        topBar.setBackground(new Background(new BackgroundFill(Color.web("#3f51b5"), CornerRadii.EMPTY, Insets.EMPTY)));

        Text thanksTitle = new Text("Special Thanks");
        thanksTitle.setFont(Font.font("Verdana", 28));
        thanksTitle.setFill(Color.web("#2e244f"));

        Text thanksContent = new Text("We sincerely thank Shashi Bagal Sir for his invaluable guidance and constant support.\n" +
                "His encouragement inspired us to learn and grow as coders.");
        thanksContent.setFont(Font.font("Segoe UI", 16));
        thanksContent.setFill(Color.DIMGRAY);

        VBox thanksTextBox = new VBox(10, thanksTitle, thanksContent);
        thanksTextBox.setAlignment(Pos.CENTER_LEFT);

        ImageView thanksImage = new ImageView(new Image("Assets\\Images\\Shashi_sir.jpg"));
        thanksImage.setFitWidth(200);
        thanksImage.setPreserveRatio(true);
        thanksImage.setSmooth(true);
        thanksImage.setCache(true);

        HBox specialThanksBox = new HBox(30, thanksTextBox, thanksImage);
        specialThanksBox.setAlignment(Pos.CENTER_LEFT);
        specialThanksBox.setPadding(new Insets(30));

        VBox specialThanksSection = new VBox(specialThanksBox);
        specialThanksSection.setPadding(new Insets(20));
        specialThanksSection.setMaxWidth(900);
        specialThanksSection.setStyle("-fx-background-color: #f9f9f9; -fx-background-radius: 10;");
        applyHoverEffect(specialThanksSection);

        Text teamTitle = new Text("Our Team Members");
        teamTitle.setFont(Font.font("Verdana", 28));
        teamTitle.setFill(Color.web("#2e244f"));

        Text teamList = new Text(
                "• Yash Khanapure\n" +
                "• Vishal Edake\n" +
                "• Manish Deokate\n" +
                "• Abhimanyu Patil"
        );
        teamList.setFont(Font.font("Segoe UI", 16));
        teamList.setFill(Color.DIMGRAY);

        VBox teamBox = new VBox(10, teamTitle, teamList);
        teamBox.setPadding(new Insets(30));
        teamBox.setMaxWidth(900);
        teamBox.setStyle("-fx-background-color: #fff9e6; -fx-background-radius: 10;");
        applyHoverEffect(teamBox);

        Text featureTitle = new Text("Our Key Features");
        featureTitle.setFont(Font.font("Verdana", 28));
        featureTitle.setFill(Color.web("#2e244f"));

        VBox keyPoints = new VBox(
                createBullet("Find colleges across Maharashtra and India for UG/PG courses."),
                createBullet("Supports Engineering, Medical, Law, and Design streams."),
                new Text(""),
                createBullet("Personalized college list based on your marks or percentile."),
                createBullet("AI-powered chatbot to assist students with queries and guidance.")
        );
        keyPoints.setSpacing(8);

        ImageView logoImageView = new ImageView(new Image("Assets/Images/logo-removebg-preview.png"));
        logoImageView.setFitWidth(200);
        logoImageView.setPreserveRatio(true);

        HBox keyFeatureContent = new HBox(40, keyPoints, logoImageView);
        keyFeatureContent.setAlignment(Pos.CENTER_LEFT);

        VBox featureList = new VBox(15, featureTitle, keyFeatureContent);
        featureList.setPadding(new Insets(30));
        featureList.setStyle("-fx-background-color: #f5f5f5; -fx-background-radius: 10;");
        featureList.setMaxWidth(900);
        applyHoverEffect(featureList);

        Text achievementTitle = new Text("Achievements");
        achievementTitle.setFont(Font.font("Verdana", 28));
        achievementTitle.setFill(Color.web("#2e244f"));

        VBox achPoints = new VBox(
                createBullet("95% prediction match with past admission trends in Maharashtra."),
                createBullet("Instant counseling recommendation in under 30 seconds."),
                createBullet("Smart filters based on exams, category, and preferences.")
        );
        achPoints.setSpacing(8);

        VBox achievementBox = new VBox(15, achievementTitle, achPoints);
        achievementBox.setPadding(new Insets(30));
        achievementBox.setStyle("-fx-background-color: #e9ecef; -fx-background-radius: 10;");
        achievementBox.setMaxWidth(900);
        applyHoverEffect(achievementBox);

        Text footerText = new Text("Thank you for visiting GetMyCollege!");
        footerText.setFont(Font.font("Arial", 20));
        footerText.setFill(Color.web("#007bff"));

        VBox footer = new VBox(footerText);
        footer.setAlignment(Pos.CENTER);
        footer.setPadding(new Insets(30));

        VBox layout = new VBox(
                topBar,
                backButton,
                text,
                specialThanksSection,
                featureList,
                achievementBox,
                 teamBox,
                footer
        );

        layout.setSpacing(20);
        layout.setPadding(new Insets(20));
        layout.setAlignment(Pos.TOP_CENTER);
        layout.setStyle("-fx-background-color: white;");

        ScrollPane scrollPane = new ScrollPane(layout);
        scrollPane.setFitToWidth(true);
        scrollPane.setStyle("-fx-background: white;");

        return scrollPane;
    }

    private Text createBullet(String text) {
        Text t = new Text("• " + text);
        t.setFont(Font.font("Segoe UI", 16));
        t.setFill(Color.DIMGRAY);
        return t;
    }

    private void applyHoverEffect(Region node) {
        DropShadow shadow = new DropShadow(10, Color.web("#999999"));
        node.setOnMouseEntered(e -> {
            node.setEffect(shadow);
            node.setScaleX(1.02);
            node.setScaleY(1.02);
            node.setStyle(node.getStyle() + "; -fx-cursor: hand;");
        });
        node.setOnMouseExited(e -> {
            node.setEffect(null);
            node.setScaleX(1.0);
            node.setScaleY(1.0);
        });
    }
}
