package com.s1.view;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import com.s1.Controller.Controller;
import com.s1.Controller.MystorageSDK;

public class profileside {
    Signup signup  = new Signup();
    private ImageView profileImageView;
    private ImageView bigPhoto;

    private Label nameLabel;
    private Label emailLabel;
    public String locationLabel = "Pune, India"; // sample default
    public String phoneLabel = "+91 9876543210"; // sample default

    private TextField nameField;
    private TextField emailField;
    private TextField locationField;
    private TextField phoneField;

    private VBox infoBox;
    private boolean isEditMode = false;
    Scene profileScene, loginpageScene, signupScene;
    Stage profilStage;
    static String url;

    public void setProfileScene(Scene profileScene) {
        this.profileScene = profileScene;
    }

    public void setProfilStage(Stage profilStage) {
        this.profilStage = profilStage;
    }

    MystorageSDK mystorageSDK = new MystorageSDK();

    public StackPane creteprofile(Runnable back) {
        String accountEmail = Controller.email1;
        if (accountEmail != null && !accountEmail.isBlank()) {
            try {
                url = mystorageSDK.loadImageUrl(accountEmail);
            } catch (Exception e) {
                new Alert(Alert.AlertType.ERROR,
                        "Could not load the local profile picture:\n" + e.getMessage()).showAndWait();
            }
        }

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

        profileImageView = new ImageView(new Image(url == null ? "Assets\\Images\\asash.png" : url));
        profileImageView.setFitHeight(45);
        profileImageView.setFitWidth(45);

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
        topRow.setBackground(new Background(new BackgroundFill(Color.web("#9fafb1ff"), CornerRadii.EMPTY, Insets.EMPTY)));

        // Profile Picture
        bigPhoto = new ImageView(new Image(url == null ? "Assets\\Images\\asash.png" : url));
        bigPhoto.setFitHeight(130);
        bigPhoto.setFitWidth(130);
        Circle clip1 = new Circle(65, 65, 65);
        bigPhoto.setClip(clip1);

        bigPhoto.setOnMouseClicked(e -> {
            if (isEditMode) {
                try {
                    String localImageUrl = mystorageSDK.uploadImage(profilStage, emailLabel.getText());
                    if (localImageUrl == null) {
                        return;
                    }
                    url = localImageUrl;
                    updateProfileImages(localImageUrl);
                } catch (Exception ex) {
                    new Alert(Alert.AlertType.ERROR,
                            "Could not save profile picture on this computer:\n" + ex.getMessage()).showAndWait();
                }
            }
        });

        // Initialize Labels
         nameLabel = new Label(Controller.name != null ? Controller.name : "yash");
         nameLabel.setFont(new Font(15));
         emailLabel = new Label(Controller.email1 != null ? Controller.email1 : "yash@gmail.com");
         emailLabel.setFont(new Font(15));

        // Initialize TextFields with current text
        nameField = new TextField(nameLabel.getText());
        emailField = new TextField(emailLabel.getText());
        locationField = new TextField(locationLabel);
        phoneField = new TextField(phoneLabel);
        infoBox = new VBox(15,
                createInfoRow("Name:", nameLabel),
                createInfoRow("Email:", emailLabel),
                createInfoRow("Location:", locationLabel),
                createInfoRow("Phone No:", phoneLabel)
        );
        infoBox.setAlignment(Pos.TOP_LEFT);

        HBox profileSection = new HBox(40, bigPhoto, infoBox);
        profileSection.setAlignment(Pos.TOP_LEFT);
        profileSection.setPadding(new Insets(50, 0, 0, 80));

        Button logoutButton = new Button("Logout");
        logoutButton.setStyle("-fx-background-color: #d32f2f; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10 20;");
        logoutButton.setOnAction(arg0 -> back.run());

        Button backButton = new Button("Back");
        backButton.setOnAction(arg0 -> {
            homepage();
            profilStage.setScene(signupScene);
        });
        backButton.setPadding(new Insets(10));

        Button editButton = new Button("Edit Profile");
        editButton.setStyle("-fx-background-color: #1976d2; -fx-text-fill: white; -fx-font-weight: bold; -fx-padding: 10 20;");

        editButton.setOnAction(e -> {
            if (isEditMode) {
                 nameLabel.setText(nameField.getText());
                // emailLabel.setText(emailField.getText());
                locationLabel = locationField.getText();
                phoneLabel = phoneField.getText();

                updateInfoLabels();
                editButton.setText("Edit Profile");
                // StackPane vb =(creteprofile(this::handleback));
                // Scene sc = new Scene(vb,1600,800);
                // profileScene = sc;
                // profilStage.setScene(sc);
                isEditMode = false;
            } else {
                setFieldsFromLabels();
                updateInfoFields();
                
                editButton.setText("Save");
                // profilStage=null;
                // profilStage.setScene(profileScene);
                // StackPane vb =(creteprofile(this::handleback));
                // Scene sc = new Scene(vb,1600,800);
                // profileScene = sc;
                // profilStage.setScene(sc);
                isEditMode = true;
            }
        });


        HBox buttonBox = new HBox(20, editButton, logoutButton);
        buttonBox.setAlignment(Pos.BOTTOM_RIGHT);
        buttonBox.setPadding(new Insets(0, 60, 40, 0));

        VBox root = new VBox();
        root.getChildren().addAll(topRow, backButton, profileSection);

        BorderPane mainPane = new BorderPane();
        mainPane.setTop(root);
        mainPane.setBottom(buttonBox);
        StackPane gradientPane = new StackPane();
        gradientPane.setStyle("-fx-background-color: radial-gradient(center 50% 60%, radius 80%, white 0%, #e0e8ff 40%, #a0b8ff 80%, #7080ff 100%)");
        gradientPane.getChildren().add(mainPane);
        //profilStage.setScene(profileScene);
        return gradientPane;
    }
    public void handleback(){

    }
    private void updateProfileImages(String imageUrl) {
        Image image = new Image(imageUrl, true);
        bigPhoto.setImage(image);
        profileImageView.setImage(image);
        if (Home.profileImageView != null) {
            Home.profileImageView.setImage(image);
        }
    }

    private HBox createInfoRow(String labelText, Label labelValue) {
        Label label = new Label(labelText);
        label.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        HBox box = new HBox(10, label, labelValue);
        box.setAlignment(Pos.CENTER_LEFT);
        return box;
    }

    private HBox createInfoRow(String labelText, String valueText) {
        Label label = new Label(labelText);
        label.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        Label value = new Label(valueText);
        value.setStyle("-fx-font-size: 18px;");
        HBox box = new HBox(10, label, value);
        box.setAlignment(Pos.CENTER_LEFT);
        return box;
    }

    private void updateInfoLabels() {
        infoBox.getChildren().setAll(
                createInfoRow("Name:", nameLabel),
                createInfoRow("Email:", emailLabel),
                createInfoRow("Location:", locationLabel),
                createInfoRow("Phone No:", phoneLabel)
        );
    }

    private void updateInfoFields() {
        infoBox.getChildren().setAll(
                 createEditableRow("Name:", nameField),
                // createEditableRow("Email:", emailField),
                createEditableRow("Location:", locationField),
                createEditableRow("Phone No:", phoneField)
        );
    }

    private void setFieldsFromLabels() {
        nameField.setText(nameLabel.getText());
        emailField.setText(emailLabel.getText());
        locationField.setText(locationLabel);
        phoneField.setText(phoneLabel);
    }

    private void initializelogin() {
        Loginpage loginpage = new Loginpage();
        loginpage.setLoginStage(Home.primaryStage);
    }

    private void homepage() {
        Home home = new Home();
        home.setPrimaryStage(profilStage);
        signupScene = new Scene(home.createHomepage());
    }

    private HBox createEditableRow(String labelText, TextField field) {
        Label label = new Label(labelText);
        label.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
        field.setStyle("-fx-font-size: 18px;");
        HBox box = new HBox(10, label, field);
        box.setAlignment(Pos.CENTER_LEFT);
        return box;
    }
}
