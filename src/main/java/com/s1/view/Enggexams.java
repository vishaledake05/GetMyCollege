package com.s1.view;

import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
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

public class Enggexams {
      public static String Exam;
    
Scene Enggcourse,formScene,homepaScene,aboutuScene;
Stage enggcourse;
    
    public void setEnggcourse(Scene enggcourse) {
    Enggcourse = enggcourse;
}
public void setEnggcourse(Stage enggcourse) {
    this.enggcourse = enggcourse;
}
    public VBox enggecource(Runnable back){


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
        Label jee = new Label("JEE MAINS");
        jee.setFont(new Font(15));
        hb1.setPrefSize(150, 150); 
        hb1.setStyle("-fx-background-color: #2a9df4; -fx-padding: 10 20; -fx-background-radius: 10; -fx-cursor: hand;");
        hb1.setAlignment(Pos.CENTER);
        hb1.setEffect(new DropShadow(8, 0, 2, Color.rgb(0,0,0,0.5)));
        hb1.getChildren().add(jee);

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
        Label mhtcet = new Label("MHT-CET");
        mhtcet.setFont(new Font(15));
        hb2.setPrefSize(150, 150);
        hb2.setStyle("-fx-background-color: #2a9df4; -fx-padding: 10 20; -fx-background-radius: 10; -fx-cursor: hand;");
        hb2.setAlignment(Pos.CENTER);
        hb2.setEffect(new DropShadow(8, 0, 2, Color.rgb(0,0,0,0.5)));
        hb2.getChildren().add(mhtcet);

        ScaleTransition st2 = new ScaleTransition(javafx.util.Duration.millis(200), hb2);
        hb2.setOnMouseEntered(e -> {
            Exam="MHT-CET" ;
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
        Label wbjee = new Label("WBJEE");
        wbjee.setFont(new Font(15));
        hb3.setPrefSize(150, 150);
        hb3.setStyle("-fx-background-color: #2a9df4; -fx-padding: 10 20; -fx-background-radius: 10; -fx-cursor: hand;");
        hb3.setAlignment(Pos.CENTER);
        hb3.setEffect(new DropShadow(8, 0, 2, Color.rgb(0,0,0,0.5)));
        hb3.getChildren().add (wbjee);

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
        Label comdek = new Label("COMEDK");
        comdek.setFont(new Font(15));
        hb4.setPrefSize(150, 150);
        hb4.setStyle("-fx-background-color: #2a9df4; -fx-padding: 10 20; -fx-background-radius: 10; -fx-cursor: hand;");
        hb4.setAlignment(Pos.CENTER);
        hb4.setEffect(new DropShadow(8, 0, 2, Color.rgb(0,0,0,0.5)));
        hb4.getChildren().add(comdek);

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

        Label kcet = new Label("KCET");
        kcet.setFont(new Font(15));
        HBox hb5 = new HBox();
        hb5.setPrefSize(150, 150);
        hb5.setStyle("-fx-background-color: #2a9df4; -fx-padding: 10 20; -fx-background-radius: 10; -fx-cursor: hand;");
        hb5.setAlignment(Pos.CENTER);
        hb5.setEffect(new DropShadow(8, 0, 2, Color.rgb(0,0,0,0.5)));
        hb5.getChildren().add(kcet);

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

        Label bitsat = new Label("BITSAT");
        bitsat.setFont(new Font(15));
        HBox hb6 = new HBox();
        hb6.setPrefSize(150, 150);
        hb6.setStyle("-fx-background-color: #2a9df4; -fx-padding: 10 20; -fx-background-radius: 10; -fx-cursor: hand;");
        hb6.setAlignment(Pos.CENTER);
        hb6.setEffect(new DropShadow(8, 0, 2, Color.rgb(0,0,0,0.5)));
        hb6.getChildren().add(bitsat);

        ScaleTransition st6 = new ScaleTransition(javafx.util.Duration.millis(200), hb6);
        hb6.setOnMouseEntered(e -> {
            st6.setToX(1.05);
            st6.setToY(1.05);
            st6.playFromStart();
        });
        hb6.setOnMouseExited(e -> {
            st6.setToX(1);
            st6.setToY(1);
            st6.playFromStart();
        });

        Label srmjee = new Label("SRMJEE");
        srmjee.setFont(new Font(15));
        HBox hb7 = new HBox();
        hb7.setPrefSize(150, 150);
        hb7.setStyle("-fx-background-color: #2a9df4; -fx-padding: 10 20; -fx-background-radius: 10; -fx-cursor: hand;");
        hb7.setAlignment(Pos.CENTER);
        hb7.setEffect(new DropShadow(8, 0, 2, Color.rgb(0,0,0,0.5)));
        hb7.getChildren().add(srmjee);

        ScaleTransition st7 = new ScaleTransition(javafx.util.Duration.millis(200), hb7);
        hb7.setOnMouseEntered(e -> {
            st7.setToX(1.05);
            st7.setToY(1.05);
            st7.playFromStart();
        });
        hb7.setOnMouseExited(e -> {
            st7.setToX(1);
            st7.setToY(1);
            st7.playFromStart();
        });

        Label eamcet = new Label("EAMCET");
        eamcet.setFont(new Font(15));
        HBox hb8 = new HBox();
        hb8.setPrefSize(150, 150);
        hb8.setStyle("-fx-background-color: #2a9df4; -fx-padding: 10 20; -fx-background-radius: 10; -fx-cursor: hand;");
        hb8.setAlignment(Pos.CENTER);
        hb8.setEffect(new DropShadow(8, 0, 2, Color.rgb(0,0,0,0.5)));
        hb8.getChildren().add(eamcet);

        ScaleTransition st8 = new ScaleTransition(javafx.util.Duration.millis(200), hb8);
        hb8.setOnMouseEntered(e -> {
            st8.setToX(1.05);
            st8.setToY(1.05);
            st8.playFromStart();
        });
        hb8.setOnMouseExited(e -> {
            st8.setToX(1);
            st8.setToY(1);
            st8.playFromStart();
        });


        hb1.setOnMouseClicked(new EventHandler<Event>() {

            @Override
            public void handle(Event arg0) {
                  initializeFormpage();
                enggcourse.setScene(formScene);
                
            }
            
        });

        hb2.setOnMouseClicked(new EventHandler<Event>() {

            @Override
            public void handle(Event arg0) {
                  initializeFormpage();
                enggcourse.setScene(formScene);
                
            }
            
        });

        hb3.setOnMouseClicked(new EventHandler<Event>() {

            @Override
            public void handle(Event arg0) {
                  initializeFormpage();
                enggcourse.setScene(formScene);
                
            }
            
        });

        hb4.setOnMouseClicked(new EventHandler<Event>() {

            @Override
            public void handle(Event arg0) {
                  initializeFormpage();
                enggcourse.setScene(formScene);
                
            }
            
        });

        hb5.setOnMouseClicked(new EventHandler<Event>() {

            @Override
            public void handle(Event arg0) {
                  initializeFormpage();
            enggcourse.setScene(formScene);
                
            }
            
        });

        hb6.setOnMouseClicked(new EventHandler<Event>() {

            @Override
            public void handle(Event arg0) {
                  initializeFormpage();
            enggcourse.setScene(formScene);
                
            }
            
        });

        hb7.setOnMouseClicked(new EventHandler<Event>() {

            @Override
            public void handle(Event arg0) {
                  initializeFormpage();
                enggcourse.setScene(formScene);
                
            }
            
        });

        hb8.setOnMouseClicked(new EventHandler<Event>() {

            @Override
            public void handle(Event arg0) {
                  initializeFormpage();
                enggcourse.setScene(formScene);
                
            } 
            
        });

        Button backButton = new Button("Back");
        backButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
             back.run();   
            }
            
        });



        HBox set1 = new HBox(110,hb1,hb2,hb3,hb4,hb5,hb6);
        HBox set2 = new HBox(110,hb7,hb8);
        VBox backbt = new VBox(backButton);
       // backbt.setPadding(new Insets(10));

        VBox root = new VBox(60,tx,set1,set2);
        //root.setPadding(new Insets(20));
    
        VBox main1 = new VBox(topRow,backbt,root);
        return main1;
       
    }
      private void initializeFormpage(){
        Form fm = new Form();
        fm.setFormstage(enggcourse);
    formScene= new Scene(fm.createform(this::handel),1500,800);
    fm.setFormScene(formScene);

    }
        private void InitializerHome(){
        Home home = new Home();
        home.setPrimaryStage(enggcourse);
        homepaScene = new Scene(home.createHomepage(),1500,800);
    }
    private void initialializeaboutus(){
        Aboutus aboutus = new Aboutus();
        aboutus.setAboutusStage(enggcourse);
        aboutuScene = new Scene(aboutus.aboutus(this::handel2),1500,800);
    }
    private void handel2(){
        enggcourse.setScene(Home.home1Scene);

    }
    private void handel(){
        enggcourse.setScene(Enggcourse);
    }
}
