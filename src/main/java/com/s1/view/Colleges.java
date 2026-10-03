package com.s1.view;
import com.s1.Model.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class Colleges {
    Scene collegScene, listScene;
    Stage collegStage;
String cast =Ug.Cast;
String branch =Ug.Branch;
double pre=Ug.per;
    public void setCollegScene(Scene collegScene) {
        this.collegScene = collegScene;
    }

    public void setCollegStage(Stage primaryStage) {
        this.collegStage = primaryStage;
    }

    public Controllerugpg controller = new Controllerugpg();
    private VBox resultsContainer;
    private List<nextpage> selectedColleges = new ArrayList<>();

    public VBox collegemethode(Runnable Back) {


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

        Image profileImage = new Image("Assets\\Images\\profile_image-removebg-preview.png");
        ImageView profileImageView = new ImageView(profileImage);
        profileImageView.setFitHeight(45);
        profileImageView.setFitWidth(45);
        profileImageView.setOnMouseClicked(e -> System.out.println("Profile pressed"));
       
        Region spacer1 = new Region();
        spacer1.setPrefWidth(300);

        Region spacer2 = new Region();
        spacer2.setPrefWidth(400);

        HBox topRow = new HBox(20, logo, spacer1, searchBox, spacer2, profileImageView);
        topRow.setAlignment(Pos.CENTER_LEFT);
        topRow.setPadding(new Insets(10, 50, 10, 0));
        topRow.setBackground(new Background(new BackgroundFill(Color.web("#eb9d27ff"), CornerRadii.EMPTY, Insets.EMPTY)));


        Text ugLabel = new Text("UG");
     ugLabel.setStyle("-fx-stroke: black");
     VBox ug = new VBox(ugLabel);
     ug.setPadding(new Insets(15));


     Text predict = new Text("Predict College");
     predict.setFont(new Font("Verdana", 20) );
     predict.setStyle("-fx-stroke: black");
    Image image = new Image("Assets\\Images\\predictlogo.png");
      ImageView imageView = new ImageView(image);
     imageView.setPreserveRatio(true);
     imageView.setFitWidth(40);
     VBox vb3 = new VBox(predict);
     vb3.setPadding(new Insets(10));
    

        Label header = new Label("College Filter Results");
        resultsContainer = new VBox(10);
        resultsContainer.setPadding(new Insets(10));

        Button backbt = new Button("Back");
      //  backbt.setPadding(new Insets(10));

        backbt.setStyle("-fx-backgroung-color: orange");
        backbt.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                Back.run();    
            }
            
        });

        Button viewSelectedButton = new Button("View Selected Colleges");
        viewSelectedButton.setStyle("-fx-background-color: orange");
        viewSelectedButton.setOnAction(e -> {
            initializer();  
            collegStage.setScene(listScene);
        });
        VBox vb = new VBox(viewSelectedButton);
        vb.setPadding(new Insets(10));
        HBox hb = new HBox(5,imageView,vb3,ug);
        VBox backBox = new VBox(backbt);
        backBox.setPadding(new Insets(10));
        HBox buBox = new HBox(15,backBox,hb);

      hb.setMaxHeight(40);
      HBox main2 = new HBox(900,buBox,vb);
      main2.setStyle("-fx-border-color: black; -fx-border-width: 2;");


        VBox root = new VBox( topRow,main2, resultsContainer);
       // ScrollPane scrollPane = new ScrollPane(root);
       VBox main = new VBox(root);
       
       main.setStyle("-fx-background-color: radial-gradient(center 50% 60%, radius 80%, white 0%, #e0e8ff 40%, #a0b8ff 80%, #7080ff 100% )");
        // Fetch filtered colleges
        controller.fetchCollegeData(cast , branch, pre, new Controllerugpg.DataCallback() {
            @Override
            public void onSuccess(List<nextpage> colleges) {
                Platform.runLater(() -> {
                    if (colleges.isEmpty()) {
                        resultsContainer.getChildren().add(new Label("No colleges found."));

                    } else {
                        for (nextpage info : colleges) {
                            resultsContainer.getChildren().add(createCollegeBox(info));
                            resultsContainer.setStyle("-fx-border-color: black; -fx-border-width: 2;");
                        }
                    }
                });
            }

            @Override
            public void onFailure(String error) {
                Platform.runLater(() -> resultsContainer.getChildren().add(new Label(error)));
            }
        });

        return main;
    }
   
    private void initializer() {
        SelectedCollegesPage selectedCollegesPage = new SelectedCollegesPage();
        selectedCollegesPage.setListStage(collegStage);
        listScene = new Scene(selectedCollegesPage.Createdlist(selectedColleges, this::handel), 1500, 800);
        
    }

    private void handel() {
        collegStage.setScene(collegScene);
    }

    private HBox createCollegeBox(nextpage info) {
        Label infoLabel = new Label(info.toString());
        Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
            if (!selectedColleges.contains(info)) {
                selectedColleges.add(info);
            }
        });
            VBox add = new VBox(addButton);
            add.setPadding(new Insets(20));
            VBox infovBox = new VBox(infoLabel);
            infovBox.setMaxWidth(700);
        HBox box = new HBox(1000,infovBox,add);
        box.setPadding(new Insets(10));
        box.setPrefWidth(900);
        return box;
    }
}
