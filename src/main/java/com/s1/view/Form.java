package com.s1.view;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Form {
    Scene FormScene,topcourScene;
    Stage Formstage;
    static String cast;
    static String branch;
    public void setFormScene(Scene formScene) {
        FormScene = formScene;
    }
    public void setFormstage(Stage formstage) {
        Formstage = formstage;
       // Formstage.setMaximized(true);
    }
    public VBox createform(Runnable bk){


        
        ImageView logo = new ImageView(new Image("Assets/Images/getmycollegelogoyellow-removebg-preview.png"));
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

        Image profileImage = new Image("Assets\\Images\\logo-removebg-preview.png");
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
        topRow.setBackground(new Background(new BackgroundFill(Color.web("#9fafb1ff"), CornerRadii.EMPTY, Insets.EMPTY)));

        Text percentilText = new Text("Brach");
        percentilText.setFont(new Font(15));
        HBox hb1 = new HBox(percentilText);
        hb1.setMaxWidth(250);
         ComboBox<String> barachBox = new ComboBox<>();
        barachBox.getItems().addAll("CS","ENTC","Mechanical","Electrical","Civil");
        barachBox.setPromptText("Select Branch");
        barachBox.setEditable(true);
        barachBox.setMaxWidth(250);
            barachBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
              branch = barachBox.getValue();
            }
            
        });
    
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll("Open", "NT-C", "ST","OBC","SC","General");
        comboBox.setPromptText("Select Caste");
        comboBox.setEditable(true);
        comboBox.setMaxWidth(250);
        comboBox.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent arg0) {
             cast = comboBox.getValue();
            }
            
        });
        
        
        VBox vb2 = new VBox(10,comboBox);
        vb2.setAlignment(Pos.TOP_CENTER);

        Button predict = new Button("Predict");
        predict.setMaxWidth(150);
        predict.setFocusTraversable(false);
        predict.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
              Initializercouerse();
              Formstage.setScene(topcourScene);
            }
            
        });


        Button back = new Button("Back");
        back.setMaxWidth(60);

        back.setFocusTraversable(false);
        back.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                bk.run();
            }
            
        });
        Label L1 = new Label("Form");
        HBox HB = new HBox(10,back,L1);
     
        VBox root = new VBox(20,HB,vb2,barachBox,predict);
        VBox main = new VBox(topRow,root);
        root.setAlignment(Pos.TOP_CENTER);
        return main;       
    }
    private void Initializercouerse(){
        Topcourseexam topcourseexam = new Topcourseexam();
        topcourseexam.setCollegestage(Formstage);
        topcourScene = new Scene(topcourseexam.collegemethode(this::handel),1500,800) ;  
    }
    private void handel(){
        Formstage.setScene(FormScene);
    }
}
