package com.s1.view;

import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
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
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
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

public class Designexams {
    Stage designStage;
    public void setDesignStage(Stage designStage) {
        this.designStage = designStage;
    }
    public void setDesignScene(Scene designScene) {
        this.designScene = designScene;
    }
    Scene designScene,formScene,homeScene,aboutuScene;
    
    public VBox designExam(Runnable backrRunnable) {

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




        Text tx = new Text("Select Exam");
        tx.setStyle("-fx-stroke:black");
        tx.setFont(new Font(30));

        
        HBox hb1 = new HBox();
        Label nid = new Label("NID DAT");
        nid.setFont(new Font(15));
        hb1.setPrefSize(150, 150); 
        hb1.setStyle("-fx-background-color: #2a9df4; -fx-padding: 10 20; -fx-background-radius: 10; -fx-cursor: hand;");
        hb1.setAlignment(Pos.CENTER);
        hb1.setEffect(new DropShadow(8, 0, 2, Color.rgb(0,0,0,0.5)));
        hb1.getChildren().add(nid);

        ScaleTransition st1 = new ScaleTransition(javafx.util.Duration.millis(200), hb1);
        hb1.setOnMouseEntered(e -> {
            st1.setToX(1.05);
            st1.setToY(1.05);
            st1.playFromStart();
        });
        hb1.setOnMouseExited(e -> {
            st1.setToX(1);
            st1.setToY(1);
            st1.playFromStart();
        });

        HBox hb2 = new HBox();
        Label nift = new Label("NIFT Entrance Exam");
        nift.setFont(new Font(15));
        hb2.setPrefSize(150, 150);
        hb2.setStyle("-fx-background-color: #2a9df4; -fx-padding: 10 20; -fx-background-radius: 10; -fx-cursor: hand;");
        hb2.setAlignment(Pos.CENTER);
        hb2.setEffect(new DropShadow(8, 0, 2, Color.rgb(0,0,0,0.5)));
        hb2.getChildren().add(nift);

        ScaleTransition st2 = new ScaleTransition(javafx.util.Duration.millis(200), hb2);
        hb2.setOnMouseEntered(e -> {
            st2.setToX(1.05);
            st2.setToY(1.05);
            st2.playFromStart();
        });
        hb2.setOnMouseExited(e -> {
            st2.setToX(1);
            st2.setToY(1);
            st2.playFromStart();
        });

        HBox hb3 = new HBox();
        Label mitid = new Label("MITID DAT");
        mitid.setFont(new Font(15));
        hb3.setPrefSize(150, 150);
        hb3.setStyle("-fx-background-color: #2a9df4; -fx-padding: 10 20; -fx-background-radius: 10; -fx-cursor: hand;");
        hb3.setAlignment(Pos.CENTER);
        hb3.setEffect(new DropShadow(8, 0, 2, Color.rgb(0,0,0,0.5)));
        hb3.getChildren().add(mitid);

        ScaleTransition st3 = new ScaleTransition(javafx.util.Duration.millis(200), hb3);
        hb3.setOnMouseEntered(e -> {
            st3.setToX(1.05);
            st3.setToY(1.05);
            st3.playFromStart();
        });
        hb3.setOnMouseExited(e -> {
            st3.setToX(1);
            st3.setToY(1);
            st3.playFromStart();
        });

        HBox hb4 = new HBox();
        Label seat = new Label("SEAT");
        seat.setFont(new Font(15));
        hb4.setPrefSize(150, 150);
        hb4.setStyle("-fx-background-color: #2a9df4; -fx-padding: 10 20; -fx-background-radius: 10; -fx-cursor: hand;");
        hb4.setAlignment(Pos.CENTER);
        hb4.setEffect(new DropShadow(8, 0, 2, Color.rgb(0,0,0,0.5)));
        hb4.getChildren().add(seat);

        ScaleTransition st4 = new ScaleTransition(javafx.util.Duration.millis(200), hb4);
        hb4.setOnMouseEntered(e -> {
            st4.setToX(1.05);
            st4.setToY(1.05);
            st4.playFromStart();
        });
        hb4.setOnMouseExited(e -> {
            st4.setToX(1);
            st4.setToY(1);
            st4.playFromStart();
        });

        Label aieed = new Label("AIEED");
        aieed.setFont(new Font(15));
        HBox hb5 = new HBox();
        hb5.setPrefSize(150, 150);
        hb5.setStyle("-fx-background-color: #2a9df4; -fx-padding: 10 20; -fx-background-radius: 10; -fx-cursor: hand;");
        hb5.setAlignment(Pos.CENTER);
        hb5.setEffect(new DropShadow(8, 0, 2, Color.rgb(0,0,0,0.5)));
        hb5.getChildren().add(aieed);

        ScaleTransition st5 = new ScaleTransition(javafx.util.Duration.millis(200), hb5);
        hb5.setOnMouseEntered(e -> {
            st5.setToX(1.05);
            st5.setToY(1.05);
            st5.playFromStart();
        });
        hb5.setOnMouseExited(e -> {
            st5.setToX(1);
            st5.setToY(1);
            st5.playFromStart();
        });

        hb1.setOnMouseClicked(new EventHandler<Event>() {

            @Override
            public void handle(Event arg0) {
                initializeFormpage();
                designStage.setScene(formScene);
                //designStage.setMaximized(true);
            }
            
        });

        hb2.setOnMouseClicked(new EventHandler<Event>() {

            @Override
            public void handle(Event arg0) {
                 initializeFormpage();
                designStage.setScene(formScene);
                
            }
            
        });

        hb3.setOnMouseClicked(new EventHandler<Event>() {

            @Override
            public void handle(Event arg0) {
                 initializeFormpage();
                designStage.setScene(formScene);
            }
            
        });

        hb4.setOnMouseClicked(new EventHandler<Event>() {

            @Override
            public void handle(Event arg0) {
                initializeFormpage();
                designStage.setScene(formScene);
            }
            
        });

        hb5.setOnMouseClicked(new EventHandler<Event>() {

            @Override
            public void handle(Event arg0) {
                 initializeFormpage();
                designStage.setScene(formScene);
            }
            
        });
   Button backbButton = new Button("Back");
   backbButton.setOnAction(new EventHandler<ActionEvent>() {

    @Override
    public void handle(ActionEvent arg0) {
       backrRunnable.run();
    }
    
   });

    HBox h = new HBox(backbButton);
    h.setAlignment(Pos.TOP_LEFT);
    //h.setPadding(new Insets(10));
        HBox set1  = new HBox(70,hb1,hb2,hb3,hb4,hb5);
        VBox root = new VBox(40,tx,set1);
       // root.setPadding(new Insets(20));
        VBox mainVBox = new VBox(topRow,h,root);
        return mainVBox;
    }
    private void initializeFormpage(){
        Form fm = new Form();
        fm.setFormstage(designStage);
        formScene = new Scene(fm.createform(this::handlebackbutton),1600,800);
        //designStage.setMaximized(true);
        fm.setFormScene(formScene);

    }
     private void InitializerHome(){
        Home home = new Home();
        home.setPrimaryStage(designStage);
        homeScene = new Scene(home.createHomepage(),1500,800);
    }
    private void initialializeaboutus(){
        Aboutus aboutus = new Aboutus();
        aboutus.setAboutusStage(designStage);
        aboutuScene = new Scene(aboutus.aboutus(this::handel2),1500,800);
    }
    private void handel2(){
        designStage.setScene(Home.home1Scene);

    }
    private void handlebackbutton(){
        designStage.setScene(designScene);
        designStage.setMinWidth(1600); 
        designStage.setMinHeight(1300);
    }
    
}
