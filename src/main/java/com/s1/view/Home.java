package com.s1.view;

import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.s1.Controller.Controller;
import com.s1.Controller.MystorageSDK;
import com.s1.Controller.chatbotcontroller;

public class Home {
    public static String Eng;
    public static ImageView profileImageView;

      public static String ugpg = null;
      private List<nextpage> selectedColleges = new ArrayList<>();

    Loginpage loginpage = new Loginpage();
   static Scene home1Scene, searchScene,designtopcos,engtopcos,medicaltopcos,lawtopcos,topengScene,topmedScene,
    topdesigScene,toplawScene,abautScene,ugScene,pgScene,chatScene,profileScene,mylistScene,contactScene;
    public void setHome1Scene(Scene home1Scene) {
        this.home1Scene = home1Scene;
    }

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    static Stage primaryStage;
    public HostServices hostServices;
     public void setHostServices(HostServices hostServices) {
        this.hostServices = hostServices;
    }

    
    public final List<Image> backgroundImages = new ArrayList<>();
    public int currentBgIndex = 0;

    public VBox createHomepage() {
       
        ImageView logo = new ImageView(new Image("Assets/Images/getmycollegelogoyellow-removebg-preview.png"));
        logo.setFitHeight(80);
        logo.setFitWidth(200);

        TextField searchBar = new TextField();
        searchBar.setPromptText("Search Colleges");
        searchBar.setPrefWidth(300);
        searchBar.setStyle("-fx-background-radius: 5; -fx-padding: 8 10; -fx-font-size: 14px;");

        Button searchBtn = new Button("Search");
        final String searchBtnStyle = "-fx-background-color: #f26722; -fx-text-fill: white; -fx-font-weight: bold;";
        final String searchBtnPressedStyle = "-fx-background-color: #d95b1e; -fx-text-fill: white; -fx-font-weight: bold;";
        searchBtn.setStyle(searchBtnStyle);
        searchBtn.setPrefHeight(30);
        searchBtn.setOnMousePressed(event -> searchBtn.setStyle(searchBtnPressedStyle));
        searchBtn.setOnMouseReleased(event -> searchBtn.setStyle(searchBtnStyle));

        HBox searchBox = new HBox(10, searchBar, searchBtn);
        searchBox.setAlignment(Pos.CENTER_LEFT);

        if (Controller.email1 != null && !Controller.email1.isBlank()) {
            try {
                profileside.url = new MystorageSDK().loadImageUrl(Controller.email1);
            } catch (java.io.IOException e) {
                System.err.println("Could not load the local profile picture: " + e.getMessage());
            }
        }
        String profileImageUrl = profileside.url;
        profileImageView = new ImageView(new Image(
                profileImageUrl == null ? "Assets/Images/asash.png" : profileImageUrl
        ));
        profileImageView.setFitHeight(45);
        profileImageView.setFitWidth(45);
        profileImageView.setOnMouseClicked(new EventHandler<Event>() {

            @Override
            public void handle(Event arg0) {
                initializeprofilepage();
                primaryStage.setScene(profileScene);

            }
            
        });
        

        Label threeline = new Label("☰");
        threeline.setStyle("-fx-background-radius: 50%; -fx-font-size:25;-fx-text-fill: black;");
        threeline.setPrefSize(50, 50);
        MenuItem myListItem = new MenuItem("📄 My List");
        MenuItem predictCollegeItem = new MenuItem("📊 Predict My College");
        MenuItem contactUsItem = new MenuItem("📞 Contact Us");
        MenuItem aboutUsItem = new MenuItem("ℹ️ About Us");


        aboutUsItem.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                initialializeaboutus();
                primaryStage.setScene(abautScene);
            
            }
            
        });

        myListItem.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
            initializelistpage();
            primaryStage.setScene(mylistScene);    }
            
        });
        predictCollegeItem.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                initizlizeugpage();
                primaryStage.setScene(ugScene); 
        
            }
            
        });
        contactUsItem.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                initializecontact();
                primaryStage.setScene(contactScene);
            }
            
        }); 

        ContextMenu contextMenu = new ContextMenu(
            myListItem,
            predictCollegeItem,
            contactUsItem,
            aboutUsItem
        );
        threeline.setOnMouseClicked(event ->contextMenu.show(threeline, Side.BOTTOM, 0, 0));
        Region spacer1 = new Region();
        spacer1.setPrefWidth(300);
        Region spacer2 = new Region();
        spacer2.setPrefWidth(400);

        HBox topRow = new HBox(20, threeline, logo, spacer1, searchBox, spacer2, profileImageView);
        topRow.setAlignment(Pos.CENTER_LEFT);
        topRow.setPadding(new Insets(10, 50, 10, 0));
        topRow.setBackground(new Background(new BackgroundFill(Color.web("#1379eeff"), CornerRadii.EMPTY, Insets.EMPTY)));

        final String menuButtonStyle = "-fx-text-fill: white; -fx-font-weight: bold; -fx-background-color: transparent;";
        final String menuButtonHoverStyle = "-fx-text-fill: white; -fx-font-weight: bold; -fx-background-color: transparent; -fx-underline: true;";

        MenuButton engineeringMenu = new MenuButton("ENGINEERING");
       MenuItem topEngineeringColleges = new MenuItem("Top Engineering Colleges");
MenuItem topengCourses = new MenuItem("Popular Exam");
engineeringMenu.getItems().addAll(topEngineeringColleges, topengCourses);

engineeringMenu.setStyle(menuButtonStyle);
engineeringMenu.setPopupSide(Side.BOTTOM);

topEngineeringColleges.setOnAction(event -> {
 initialializtopeng();
 primaryStage.setScene(topengScene);
   
});


topengCourses.setOnAction(event -> {
    Eng = "Engineering";
    initializeEngcourse();
    primaryStage.setScene(engtopcos);   
});



        MenuButton medicalMenu = new MenuButton("MEDICAL");
                    MenuItem topmedicalColleges = new MenuItem("Top Medical Colleges");
            MenuItem topmedCourses = new MenuItem("Popular Exam");
                medicalMenu.getItems().addAll(topmedicalColleges, topmedCourses);

            medicalMenu.setStyle(menuButtonStyle);
            medicalMenu.setPopupSide(Side.BOTTOM);

            topmedicalColleges.setOnAction(event -> {
            initialializetopmed();
            primaryStage.setScene(topmedScene);
            
            });


            topmedCourses.setOnAction(event -> {
              initializemedcourse();
              primaryStage.setScene(medicaltopcos);
            });

        MenuButton lawMenu = new MenuButton("LAW");
                MenuItem toplawColleges = new MenuItem("Top Law Colleges");
        MenuItem toplawCourses = new MenuItem("Popular Exam");
        lawMenu.getItems().addAll(toplawColleges, toplawCourses);

        lawMenu.setStyle(menuButtonStyle);
        lawMenu.setPopupSide(Side.BOTTOM);

        toplawColleges.setOnAction(event -> {
        initialializetoplaw(); 
        primaryStage.setScene(toplawScene);
        
        });


      toplawCourses.setOnAction(event -> {
        initializelawcourse();
        primaryStage.setScene(lawtopcos);
      
         
});
       MenuButton designMenu = new MenuButton("DESIGN");


MenuItem topDesignColleges = new MenuItem("Top Design Colleges");
MenuItem topCourses = new MenuItem("Popular Exam");
designMenu.getItems().addAll(topDesignColleges, topCourses);


designMenu.setStyle(menuButtonStyle);
designMenu.setPopupSide(Side.BOTTOM);

topDesignColleges.setOnAction(event -> {
 topdesign();
 primaryStage.setScene(topdesigScene);
   
});

topCourses.setOnAction(event -> {
     initializedesignmenu();
    primaryStage.setScene(designtopcos);
   
});

        HBox navBar = new HBox(60, engineeringMenu, medicalMenu, lawMenu, designMenu);
        navBar.setPadding(new Insets(10, 0, 10, 480));
        navBar.setAlignment(Pos.CENTER_LEFT);
        navBar.setBackground(new Background(new BackgroundFill(Color.web("#d5d8e1ff"), CornerRadii.EMPTY, Insets.EMPTY)));

        VBox appBar = new VBox(topRow, navBar);

        VBox sidebar = new VBox(30);
        sidebar.setPadding(new Insets(50, 10, 10, 10));
        sidebar.setStyle("-fx-background-color: #e4f2f4ff; -fx-border-color: #090909ff;");
        sidebar.setPrefWidth(200);

        MenuButton menuBtn = new MenuButton("PREDICT COLLEGE");
        MenuItem ugmenu = new MenuItem("UnderGraduate");
        ugmenu.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                initizlizeugpage();
                primaryStage.setScene(ugScene);
                ugpg = "UG";  
            }   
        });
        MenuItem pgmenu = new MenuItem("PostGraduate");
        pgmenu.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                initialializepgpage();
                primaryStage.setScene(pgScene);
                ugpg = "PG";
            }
            
        });

        menuBtn.getItems().addAll(ugmenu, pgmenu);
        menuBtn.setPrefSize(220, 40);
        final String sidebarMenuBtnStyle = "-fx-background-color: #ffffff; -fx-border-color: #cccccc; -fx-border-radius: 20; -fx-background-radius: 20; -fx-font-size: 14px; -fx-text-fill: #333;";
        final String sidebarMenuBtnHoverStyle = "-fx-background-color: #f0f0f0; -fx-border-color: #f26722; -fx-border-radius: 20; -fx-background-radius: 20; -fx-font-size: 14px; -fx-text-fill: #333;";
        menuBtn.setStyle(sidebarMenuBtnStyle);
        menuBtn.setOnMouseEntered(event -> menuBtn.setStyle(sidebarMenuBtnHoverStyle));
        menuBtn.setOnMouseExited(event -> menuBtn.setStyle(sidebarMenuBtnStyle));

        Button counselingBtn = new Button("🎓 Get Free Counseling");
        counselingBtn.setPrefSize(220, 40);
        final String sidebarBtnStyle = "-fx-background-color: #ffffff; -fx-border-color: #cccccc; -fx-border-radius: 20; -fx-background-radius: 20; -fx-font-size: 14px; -fx-padding: 8 16; -fx-text-fill: #333;";
        final String sidebarBtnPressedStyle = "-fx-background-color: #e0e0e0; -fx-border-color: #f26722; -fx-border-radius: 20; -fx-background-radius: 20; -fx-font-size: 14px; -fx-padding: 8 16; -fx-text-fill: #333;";
        counselingBtn.setStyle(sidebarBtnStyle);
        counselingBtn.setOnMousePressed(event -> counselingBtn.setStyle(sidebarBtnPressedStyle));
        counselingBtn.setOnMouseReleased(event -> counselingBtn.setStyle(sidebarBtnStyle));
        counselingBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                initializechat();
                primaryStage.setScene(chatScene);
            }
            
        });

        Button whatsappBtn = new Button("💬 Join WhatsApp Group");
        whatsappBtn.setPrefSize(220, 40);
        whatsappBtn.setStyle(sidebarBtnStyle);
        whatsappBtn.setOnAction(event -> hostServices.showDocument("https://whatsapp.com/channel/0029Vb67hxU8PgsG21sUAA3H"));
        whatsappBtn.setOnMousePressed(event -> whatsappBtn.setStyle(sidebarBtnPressedStyle));
        whatsappBtn.setOnMouseReleased(event -> whatsappBtn.setStyle(sidebarBtnStyle));

        sidebar.getChildren().addAll(menuBtn, counselingBtn, whatsappBtn);

        StackPane backgroundPane = new StackPane();
        backgroundPane.setPrefSize(1366, 768);

        startBackgroundAnimation(backgroundPane);

        Text tx = new Text("Search Colleges by Filters  ---->");

        Button centerSearchBtn = new Button();
        centerSearchBtn.setFont(Font.font("System", FontWeight.BOLD, 16));
        centerSearchBtn.setTextFill(Color.WHITE);
        centerSearchBtn.setGraphic(tx);

        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE); 
        timeline.setAutoReverse(true); 
        KeyValue kv = new KeyValue(tx.translateXProperty(), 25);
        KeyFrame kf = new KeyFrame(Duration.seconds(1.5), kv);

        timeline.getKeyFrames().add(kf);
        timeline.play();
        centerSearchBtn.setPrefWidth(300);
        final String centerBtnStyle = "-fx-background-color: #b776dfff; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 20; -fx-padding: 10 20;";
        final String centerBtnPressedStyle = "-fx-background-color: #a269c4; -fx-text-fill: white; -fx-font-weight: bold; -fx-background-radius: 20; -fx-padding: 10 20;";
        centerSearchBtn.setStyle(centerBtnStyle);
        centerSearchBtn.setOnAction(event -> {
            initialializeseachpage();
            primaryStage.setScene(searchScene);
        });
        centerSearchBtn.setOnMousePressed(event -> centerSearchBtn.setStyle(centerBtnPressedStyle));
        centerSearchBtn.setOnMouseReleased(event -> centerSearchBtn.setStyle(centerBtnStyle));

        HBox centerSearchBox = new HBox(10, centerSearchBtn);
        centerSearchBox.setAlignment(Pos.TOP_CENTER);
        centerSearchBox.setPadding(new Insets(20));

        FadeTransition fadeIn = new FadeTransition(Duration.seconds(2), centerSearchBox);
        fadeIn.setFromValue(0);
        fadeIn.setToValue(1);
        fadeIn.play();

        StackPane.setAlignment(centerSearchBox, Pos.CENTER);
        backgroundPane.getChildren().add(centerSearchBox);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setFitToWidth(true);

        VBox collegeCardsBox = new VBox(20);
        collegeCardsBox.setPadding(new Insets(20));
        collegeCardsBox.setStyle("-fx-background-color: #f4f4f4;");
        collegeCardsBox.setAlignment(Pos.CENTER);

        final String cardStyle = "-fx-background-color: white; -fx-border-color: #ccc; -fx-background-radius: 10; -fx-border-radius: 10; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 10, 0, 0, 5);";
        Font cardTitleFont = Font.font("Arial", 20);

        Text engineeringText = new Text("TOP 10 ENGINEERING COLLEGES IN INDIA");
        engineeringText.setFont(new Font(25));

        VBox engCard1 = new VBox(10);
        engCard1.setPadding(new Insets(15));
        engCard1.setStyle(cardStyle);
        engCard1.setPrefWidth(1000);
        Label engName1 = new Label("Indian Institute of Technology Madras (IITM)");
        engName1.setFont(cardTitleFont);
        engCard1.getChildren().addAll(engName1, new Label("Location: Chennai, Tamil Nadu"));
        engCard1.setOnMouseEntered(event -> engCard1.setCursor(Cursor.HAND));
        engCard1.setOnMouseExited(event -> engCard1.setCursor(Cursor.DEFAULT));
        engCard1.setOnMouseClicked(event -> hostServices.showDocument("https://en.wikipedia.org/wiki/IIT_Madras"));

        VBox engCard2 = new VBox(10);
        engCard2.setPadding(new Insets(15));
        engCard2.setStyle(cardStyle);
        engCard2.setPrefWidth(1000);
        Label engName2 = new Label("Indian Institute of Technology Delhi (IITD)");
        engName2.setFont(cardTitleFont);
        engCard2.getChildren().addAll(engName2, new Label("Location: New Delhi, Delhi"));
        engCard2.setOnMouseEntered(event -> engCard2.setCursor(Cursor.HAND));
        engCard2.setOnMouseExited(event -> engCard2.setCursor(Cursor.DEFAULT));
        engCard2.setOnMouseClicked(event -> hostServices.showDocument("https://en.wikipedia.org/wiki/IIT_Delhi"));

        VBox engCard3 = new VBox(10);
        engCard3.setPadding(new Insets(15));
        engCard3.setStyle(cardStyle);
        engCard3.setPrefWidth(1000);
        Label engName3 = new Label("Indian Institute of Technology Bombay (IITB)");
        engName3.setFont(cardTitleFont);
        engCard3.getChildren().addAll(engName3, new Label("Location: Mumbai, Maharashtra"));
        engCard3.setOnMouseEntered(event -> engCard3.setCursor(Cursor.HAND));
        engCard3.setOnMouseExited(event -> engCard3.setCursor(Cursor.DEFAULT));
        engCard3.setOnMouseClicked(event -> hostServices.showDocument("https://en.wikipedia.org/wiki/IIT_Bombay"));

        VBox engCard4 = new VBox(10);
        engCard4.setPadding(new Insets(15));
        engCard4.setStyle(cardStyle);
        engCard4.setPrefWidth(1000);
        Label engName4 = new Label("Indian Institute of Technology Kanpur (IITK)");
        engName4.setFont(cardTitleFont);
        engCard4.getChildren().addAll(engName4, new Label("Location: Kanpur, Uttar Pradesh"));
        engCard4.setOnMouseEntered(event -> engCard4.setCursor(Cursor.HAND));
        engCard4.setOnMouseExited(event -> engCard4.setCursor(Cursor.DEFAULT));
        engCard4.setOnMouseClicked(event -> hostServices.showDocument("https://en.wikipedia.org/wiki/IIT_Kanpur"));

        VBox engCard5 = new VBox(10);
        engCard5.setPadding(new Insets(15));
        engCard5.setStyle(cardStyle);
        engCard5.setPrefWidth(1000);
        Label engName5 = new Label("Indian Institute of Technology Kharagpur (IIT-KGP)");
        engName5.setFont(cardTitleFont);
        engCard5.getChildren().addAll(engName5, new Label("Location: Kharagpur, West Bengal"));
        engCard5.setOnMouseEntered(event -> engCard5.setCursor(Cursor.HAND));
        engCard5.setOnMouseExited(event -> engCard5.setCursor(Cursor.DEFAULT));
        engCard5.setOnMouseClicked(event -> hostServices.showDocument("https://en.wikipedia.org/wiki/IIT_Kharagpur"));

        VBox engCard6 = new VBox(10);
        engCard6.setPadding(new Insets(15));
        engCard6.setStyle(cardStyle);
        engCard6.setPrefWidth(1000);
        Label engName6 = new Label("National Institute of Technology Tiruchirappalli (NITT)");
        engName6.setFont(cardTitleFont);
        engCard6.getChildren().addAll(engName6, new Label("Location: Tiruchirappalli, Tamil Nadu"));
        engCard6.setOnMouseEntered(event -> engCard6.setCursor(Cursor.HAND));
        engCard6.setOnMouseExited(event -> engCard6.setCursor(Cursor.DEFAULT));
        engCard6.setOnMouseClicked(event -> hostServices.showDocument("https://en.wikipedia.org/wiki/National_Institute_of_Technology,_Tiruchirappalli"));

        VBox engCard7 = new VBox(10);
        engCard7.setPadding(new Insets(15));
        engCard7.setStyle(cardStyle);
        engCard7.setPrefWidth(1000);
        Label engName7 = new Label("National Institute of Technology Karnataka, Surathkal (NITK)");
        engName7.setFont(cardTitleFont);
        engCard7.getChildren().addAll(engName7, new Label("Location: Mangalore, Karnataka"));
        engCard7.setOnMouseEntered(event -> engCard7.setCursor(Cursor.HAND));
        engCard7.setOnMouseExited(event -> engCard7.setCursor(Cursor.DEFAULT));
        engCard7.setOnMouseClicked(event -> hostServices.showDocument("https://en.wikipedia.org/wiki/National_Institute_of_Technology,_Karnataka"));

        VBox engCard8 = new VBox(10);
        engCard8.setPadding(new Insets(15));
        engCard8.setStyle(cardStyle);
        engCard8.setPrefWidth(1000);
        Label engName8 = new Label("National Institute of Technology Rourkela (NITR)");
        engName8.setFont(cardTitleFont);
        engCard8.getChildren().addAll(engName8, new Label("Location: Rourkela, Odisha"));
        engCard8.setOnMouseEntered(event -> engCard8.setCursor(Cursor.HAND));
        engCard8.setOnMouseExited(event -> engCard8.setCursor(Cursor.DEFAULT));
        engCard8.setOnMouseClicked(event -> hostServices.showDocument("https://en.wikipedia.org/wiki/National_Institute_of_Technology,_Rourkela"));

        VBox engCard9 = new VBox(10);
        engCard9.setPadding(new Insets(15));
        engCard9.setStyle(cardStyle);
        engCard9.setPrefWidth(1000);
        Label engName9 = new Label("National Institute of Technology Warangal (NITW)");
        engName9.setFont(cardTitleFont);
        engCard9.getChildren().addAll(engName9, new Label("Location: Warangal, Telangana"));
        engCard9.setOnMouseEntered(event -> engCard9.setCursor(Cursor.HAND));
        engCard9.setOnMouseExited(event -> engCard9.setCursor(Cursor.DEFAULT));
        engCard9.setOnMouseClicked(event -> hostServices.showDocument("https://en.wikipedia.org/wiki/National_Institute_of_Technology,_Warangal"));

        VBox engCard10 = new VBox(10);
        engCard10.setPadding(new Insets(15));
        engCard10.setStyle(cardStyle);
        engCard10.setPrefWidth(1000);
        Label engName10 = new Label("National Institute of Technology Calicut (NITC)");
        engName10.setFont(cardTitleFont);
        engCard10.getChildren().addAll(engName10, new Label("Location: Kozhikode, Kerala"));
        engCard10.setOnMouseEntered(event -> engCard10.setCursor(Cursor.HAND));
        engCard10.setOnMouseExited(event -> engCard10.setCursor(Cursor.DEFAULT));
        engCard10.setOnMouseClicked(event -> hostServices.showDocument("https://en.wikipedia.org/wiki/National_Institute_of_Technology,_Calicut"));

        Text medicalText = new Text("TOP 10 MEDICAL COLLEGES IN INDIA");
        medicalText.setFont(new Font(25));

        VBox medCard1 = new VBox(10);
        medCard1.setPadding(new Insets(15));
        medCard1.setStyle(cardStyle);
        medCard1.setPrefWidth(1000);
        Label medName1 = new Label("All India Institute of Medical Sciences, Delhi");
        medName1.setFont(cardTitleFont);
        medCard1.getChildren().addAll(medName1, new Label("Location: New Delhi, Delhi"));
        medCard1.setOnMouseEntered(event -> medCard1.setCursor(Cursor.HAND));
        medCard1.setOnMouseExited(event -> medCard1.setCursor(Cursor.DEFAULT));
        medCard1.setOnMouseClicked(event -> hostServices.showDocument("https://en.wikipedia.org/wiki/All_India_Institute_of_Medical_Sciences,_New_Delhi"));

        VBox medCard2 = new VBox(10);
        medCard2.setPadding(new Insets(15));
        medCard2.setStyle(cardStyle);
        medCard2.setPrefWidth(1000);
        Label medName2 = new Label("Post Graduate Institute of Medical Education and Research");
        medName2.setFont(cardTitleFont);
        medCard2.getChildren().addAll(medName2, new Label("Location: Chandigarh"));
        medCard2.setOnMouseEntered(event -> medCard2.setCursor(Cursor.HAND));
        medCard2.setOnMouseExited(event -> medCard2.setCursor(Cursor.DEFAULT));
        medCard2.setOnMouseClicked(event -> hostServices.showDocument("https://en.wikipedia.org/wiki/Postgraduate_Institute_of_Medical_Education_and_Research"));

        VBox medCard3 = new VBox(10);
        medCard3.setPadding(new Insets(15));
        medCard3.setStyle(cardStyle);
        medCard3.setPrefWidth(1000);
        Label medName3 = new Label("Christian Medical College, Vellore");
        medName3.setFont(cardTitleFont);
        medCard3.getChildren().addAll(medName3, new Label("Location: Vellore, Tamil Nadu"));
        medCard3.setOnMouseEntered(event -> medCard3.setCursor(Cursor.HAND));
        medCard3.setOnMouseExited(event -> medCard3.setCursor(Cursor.DEFAULT));
        medCard3.setOnMouseClicked(event -> hostServices.showDocument("https://en.wikipedia.org/wiki/Christian_Medical_College_%26_Hospital,_Vellore"));

        VBox medCard4 = new VBox(10);
        medCard4.setPadding(new Insets(15));
        medCard4.setStyle(cardStyle);
        medCard4.setPrefWidth(1000);
        Label medName4 = new Label("National Institute of Mental Health and Neurosciences");
        medName4.setFont(cardTitleFont);
        medCard4.getChildren().addAll(medName4, new Label("Location: Bengaluru, Karnataka"));
        medCard4.setOnMouseEntered(event -> medCard4.setCursor(Cursor.HAND));
        medCard4.setOnMouseExited(event -> medCard4.setCursor(Cursor.DEFAULT));
        medCard4.setOnMouseClicked(event -> hostServices.showDocument("https://en.wikipedia.org/wiki/National_Institute_of_Mental_Health_and_Neurosciences"));

        VBox medCard5 = new VBox(10);
        medCard5.setPadding(new Insets(15));
        medCard5.setStyle(cardStyle);
        medCard5.setPrefWidth(1000);
        Label medName5 = new Label("Jawaharlal Institute of Postgraduate Medical Education & Research");
        medName5.setFont(cardTitleFont);
        medCard5.getChildren().addAll(medName5, new Label("Location: Puducherry"));
        medCard5.setOnMouseEntered(event -> medCard5.setCursor(Cursor.HAND));
        medCard5.setOnMouseExited(event -> medCard5.setCursor(Cursor.DEFAULT));
        medCard5.setOnMouseClicked(event -> hostServices.showDocument("https://en.wikipedia.org/wiki/Jawaharlal_Institute_of_Postgraduate_Medical_Education_and_Research"));

        VBox medCard6 = new VBox(10);
        medCard6.setPadding(new Insets(15));
        medCard6.setStyle(cardStyle);
        medCard6.setPrefWidth(1000);
        Label medName6 = new Label("Amrita Vishwa Vidyapeetham");
        medName6.setFont(cardTitleFont);
        medCard6.getChildren().addAll(medName6, new Label("Location: Coimbatore, Tamil Nadu"));
        medCard6.setOnMouseEntered(event -> medCard6.setCursor(Cursor.HAND));
        medCard6.setOnMouseExited(event -> medCard6.setCursor(Cursor.DEFAULT));
        medCard6.setOnMouseClicked(event -> hostServices.showDocument("https://en.wikipedia.org/wiki/Amrita_Vishwa_Vidyapeetham"));

        VBox medCard7 = new VBox(10);
        medCard7.setPadding(new Insets(15));
        medCard7.setStyle(cardStyle);
        medCard7.setPrefWidth(1000);
        Label medName7 = new Label("Sanjay Gandhi Postgraduate Institute of Medical Sciences");
        medName7.setFont(cardTitleFont);
        medCard7.getChildren().addAll(medName7, new Label("Location: Lucknow, Uttar Pradesh"));
        medCard7.setOnMouseEntered(event -> medCard7.setCursor(Cursor.HAND));
        medCard7.setOnMouseExited(event -> medCard7.setCursor(Cursor.DEFAULT));
        medCard7.setOnMouseClicked(event -> hostServices.showDocument("https://en.wikipedia.org/wiki/Sanjay_Gandhi_Postgraduate_Institute_of_Medical_Sciences"));

        VBox medCard8 = new VBox(10);
        medCard8.setPadding(new Insets(15));
        medCard8.setStyle(cardStyle);
        medCard8.setPrefWidth(1000);
        Label medName8 = new Label("Banaras Hindu University");
        medName8.setFont(cardTitleFont);
        medCard8.getChildren().addAll(medName8, new Label("Location: Varanasi, Uttar Pradesh"));
        medCard8.setOnMouseEntered(event -> medCard8.setCursor(Cursor.HAND));
        medCard8.setOnMouseExited(event -> medCard8.setCursor(Cursor.DEFAULT));
        medCard8.setOnMouseClicked(event -> hostServices.showDocument("https://en.wikipedia.org/wiki/Banaras_Hindu_University"));

        VBox medCard9 = new VBox(10);
        medCard9.setPadding(new Insets(15));
        medCard9.setStyle(cardStyle);
        medCard9.setPrefWidth(1000);
        Label medName9 = new Label("Kasturba Medical College, Manipal");
        medName9.setFont(cardTitleFont);
        medCard9.getChildren().addAll(medName9, new Label("Location: Manipal, Karnataka"));
        medCard9.setOnMouseEntered(event -> medCard9.setCursor(Cursor.HAND));
        medCard9.setOnMouseExited(event -> medCard9.setCursor(Cursor.DEFAULT));
        medCard9.setOnMouseClicked(event -> hostServices.showDocument("https://en.wikipedia.org/wiki/Kasturba_Medical_College,_Manipal"));

        VBox medCard10 = new VBox(10);
        medCard10.setPadding(new Insets(15));
        medCard10.setStyle(cardStyle);
        medCard10.setPrefWidth(1000);
        Label medName10 = new Label("Sree Chitra Tirunal Institute for Medical Sciences and Technology");
        medName10.setFont(cardTitleFont);
        medCard10.getChildren().addAll(medName10, new Label("Location: Thiruvananthapuram, Kerala"));
        medCard10.setOnMouseEntered(event -> medCard10.setCursor(Cursor.HAND));
        medCard10.setOnMouseExited(event -> medCard10.setCursor(Cursor.DEFAULT));
        medCard10.setOnMouseClicked(event -> hostServices.showDocument("https://en.wikipedia.org/wiki/Sree_Chitra_Tirunal_Institute_for_Medical_Sciences_and_Technology"));

        Text lawText = new Text("TOP 10 LAW COLLEGES IN INDIA");
        lawText.setFont(new Font(25));

        VBox lawCard1 = new VBox(10);
        lawCard1.setPadding(new Insets(15));
        lawCard1.setStyle(cardStyle);
        lawCard1.setPrefWidth(1000);
        Label lawName1 = new Label("National Law School of India University");
        lawName1.setFont(cardTitleFont);
        lawCard1.getChildren().addAll(lawName1, new Label("Location: Bengaluru, Karnataka"));
        lawCard1.setOnMouseEntered(event -> lawCard1.setCursor(Cursor.HAND));
        lawCard1.setOnMouseExited(event -> lawCard1.setCursor(Cursor.DEFAULT));
        lawCard1.setOnMouseClicked(event -> hostServices.showDocument("https://en.wikipedia.org/wiki/National_Law_School_of_India_University"));

        VBox lawCard2 = new VBox(10);
        lawCard2.setPadding(new Insets(15));
        lawCard2.setStyle(cardStyle);
        lawCard2.setPrefWidth(1000);
        Label lawName2 = new Label("National Law University, Delhi");
        lawName2.setFont(cardTitleFont);
        lawCard2.getChildren().addAll(lawName2, new Label("Location: New Delhi, Delhi"));
        lawCard2.setOnMouseEntered(event -> lawCard2.setCursor(Cursor.HAND));
        lawCard2.setOnMouseExited(event -> lawCard2.setCursor(Cursor.DEFAULT));
        lawCard2.setOnMouseClicked(event -> hostServices.showDocument("https://en.wikipedia.org/wiki/National_Law_University,_Delhi"));

        VBox lawCard3 = new VBox(10);
        lawCard3.setPadding(new Insets(15));
        lawCard3.setStyle(cardStyle);
        lawCard3.setPrefWidth(1000);
        Label lawName3 = new Label("Nalsar University of Law");
        lawName3.setFont(cardTitleFont);
        lawCard3.getChildren().addAll(lawName3, new Label("Location: Hyderabad, Telangana"));
        lawCard3.setOnMouseEntered(event -> lawCard3.setCursor(Cursor.HAND));
        lawCard3.setOnMouseExited(event -> lawCard3.setCursor(Cursor.DEFAULT));
        lawCard3.setOnMouseClicked(event -> hostServices.showDocument("https://en.wikipedia.org/wiki/Nalsar_University_of_Law"));

        VBox lawCard4 = new VBox(10);
        lawCard4.setPadding(new Insets(15));
        lawCard4.setStyle(cardStyle);
        lawCard4.setPrefWidth(1000);
        Label lawName4 = new Label("The West Bengal National University of Juridical Sciences");
        lawName4.setFont(cardTitleFont);
        lawCard4.getChildren().addAll(lawName4, new Label("Location: Kolkata, West Bengal"));
        lawCard4.setOnMouseEntered(event -> lawCard4.setCursor(Cursor.HAND));
        lawCard4.setOnMouseExited(event -> lawCard4.setCursor(Cursor.DEFAULT));
        lawCard4.setOnMouseClicked(event -> hostServices.showDocument("https://en.wikipedia.org/wiki/West_Bengal_National_University_of_Juridical_Sciences"));

        VBox lawCard5 = new VBox(10);
        lawCard5.setPadding(new Insets(15));
        lawCard5.setStyle(cardStyle);
        lawCard5.setPrefWidth(1000);
        Label lawName5 = new Label("Jamia Millia Islamia");
        lawName5.setFont(cardTitleFont);
        lawCard5.getChildren().addAll(lawName5, new Label("Location: New Delhi, Delhi"));
        lawCard5.setOnMouseEntered(event -> lawCard5.setCursor(Cursor.HAND));
        lawCard5.setOnMouseExited(event -> lawCard5.setCursor(Cursor.DEFAULT));
        lawCard5.setOnMouseClicked(event -> hostServices.showDocument("https://en.wikipedia.org/wiki/Jamia_Millia_Islamia"));

        VBox lawCard6 = new VBox(10);
        lawCard6.setPadding(new Insets(15));
        lawCard6.setStyle(cardStyle);
        lawCard6.setPrefWidth(1000);
        Label lawName6 = new Label("Symbiosis Law School");
        lawName6.setFont(cardTitleFont);
        lawCard6.getChildren().addAll(lawName6, new Label("Location: Pune, Maharashtra"));
        lawCard6.setOnMouseEntered(event -> lawCard6.setCursor(Cursor.HAND));
        lawCard6.setOnMouseExited(event -> lawCard6.setCursor(Cursor.DEFAULT));
        lawCard6.setOnMouseClicked(event -> hostServices.showDocument("https://en.wikipedia.org/wiki/Symbiosis_Law_School"));

        VBox lawCard7 = new VBox(10);
        lawCard7.setPadding(new Insets(15));
        lawCard7.setStyle(cardStyle);
        lawCard7.setPrefWidth(1000);
        Label lawName7 = new Label("Gujarat National Law University");
        lawName7.setFont(cardTitleFont);
        lawCard7.getChildren().addAll(lawName7, new Label("Location: Gandhinagar, Gujarat"));
        lawCard7.setOnMouseEntered(event -> lawCard7.setCursor(Cursor.HAND));
        lawCard7.setOnMouseExited(event -> lawCard7.setCursor(Cursor.DEFAULT));
        lawCard7.setOnMouseClicked(event -> hostServices.showDocument("https://en.wikipedia.org/wiki/Gujarat_National_Law_University"));

        VBox lawCard8 = new VBox(10);
        lawCard8.setPadding(new Insets(15));
        lawCard8.setStyle(cardStyle);
        lawCard8.setPrefWidth(1000);
        Label lawName8 = new Label("Siksha 'O' Anusandhan");
        lawName8.setFont(cardTitleFont);
        lawCard8.getChildren().addAll(lawName8, new Label("Location: Bhubaneswar, Odisha"));
        lawCard8.setOnMouseEntered(event -> lawCard8.setCursor(Cursor.HAND));
        lawCard8.setOnMouseExited(event -> lawCard8.setCursor(Cursor.DEFAULT));
        lawCard8.setOnMouseClicked(event -> hostServices.showDocument("https://en.wikipedia.org/wiki/Siksha_%27O%27_Anusandhan"));

        VBox lawCard9 = new VBox(10);
        lawCard9.setPadding(new Insets(15));
        lawCard9.setStyle(cardStyle);
        lawCard9.setPrefWidth(1000);
        Label lawName9 = new Label("Indian Institute of Technology Kharagpur");
        lawName9.setFont(cardTitleFont);
        lawCard9.getChildren().addAll(lawName9, new Label("Location: Kharagpur, West Bengal"));
        lawCard9.setOnMouseEntered(event -> lawCard9.setCursor(Cursor.HAND));
        lawCard9.setOnMouseExited(event -> lawCard9.setCursor(Cursor.DEFAULT));
        lawCard9.setOnMouseClicked(event -> hostServices.showDocument("https://en.wikipedia.org/wiki/Rajiv_Gandhi_School_of_Intellectual_Property_Law,_IIT_Kharagpur"));

        VBox lawCard10 = new VBox(10);
        lawCard10.setPadding(new Insets(15));
        lawCard10.setStyle(cardStyle);
        lawCard10.setPrefWidth(1000);
        Label lawName10 = new Label("Babasaheb Bhimrao Ambedkar University");
        lawName10.setFont(cardTitleFont);
        lawCard10.getChildren().addAll(lawName10, new Label("Location: Lucknow, Uttar Pradesh"));
        lawCard10.setOnMouseEntered(event -> lawCard10.setCursor(Cursor.HAND));
        lawCard10.setOnMouseExited(event -> lawCard10.setCursor(Cursor.DEFAULT));
        lawCard10.setOnMouseClicked(event -> hostServices.showDocument("https://en.wikipedia.org/wiki/Babasaheb_Bhimrao_Ambedkar_University"));

        Text designText = new Text("TOP 10 DESIGN COLLEGES IN INDIA");
        designText.setFont(new Font(25));

        VBox desCard1 = new VBox(10);
        desCard1.setPadding(new Insets(15));
        desCard1.setStyle(cardStyle);
        desCard1.setPrefWidth(1000);
        Label desName1 = new Label("National Institute of Design");
        desName1.setFont(cardTitleFont);
        desCard1.getChildren().addAll(desName1, new Label("Location: Ahmedabad, Gujarat"));
        desCard1.setOnMouseEntered(event -> desCard1.setCursor(Cursor.HAND));
        desCard1.setOnMouseExited(event -> desCard1.setCursor(Cursor.DEFAULT));
        desCard1.setOnMouseClicked(event -> hostServices.showDocument("https://en.wikipedia.org/wiki/National_Institute_of_Design"));

        VBox desCard2 = new VBox(10);
        desCard2.setPadding(new Insets(15));
        desCard2.setStyle(cardStyle);
        desCard2.setPrefWidth(1000);
        Label desName2 = new Label("Industrial Design Centre, IIT Bombay");
        desName2.setFont(cardTitleFont);
        desCard2.getChildren().addAll(desName2, new Label("Location: Mumbai, Maharashtra"));
        desCard2.setOnMouseEntered(event -> desCard2.setCursor(Cursor.HAND));
        desCard2.setOnMouseExited(event -> desCard2.setCursor(Cursor.DEFAULT));
        desCard2.setOnMouseClicked(event -> hostServices.showDocument("https://en.wikipedia.org/wiki/IDC_School_of_Design"));

        VBox desCard3 = new VBox(10);
        desCard3.setPadding(new Insets(15));
        desCard3.setStyle(cardStyle);
        desCard3.setPrefWidth(1000);
        Label desName3 = new Label("National Institute of Fashion Technology");
        desName3.setFont(cardTitleFont);
        desCard3.getChildren().addAll(desName3, new Label("Location: New Delhi, Delhi"));
        desCard3.setOnMouseEntered(event -> desCard3.setCursor(Cursor.HAND));
        desCard3.setOnMouseExited(event -> desCard3.setCursor(Cursor.DEFAULT));
        desCard3.setOnMouseClicked(event -> hostServices.showDocument("https://en.wikipedia.org/wiki/National_Institute_of_Fashion_Technology"));

        VBox desCard4 = new VBox(10);
        desCard4.setPadding(new Insets(15));
        desCard4.setStyle(cardStyle);
        desCard4.setPrefWidth(1000);
        Label desName4 = new Label("Symbiosis Institute of Design");
        desName4.setFont(cardTitleFont);
        desCard4.getChildren().addAll(desName4, new Label("Location: Pune, Maharashtra"));
        desCard4.setOnMouseEntered(event -> desCard4.setCursor(Cursor.HAND));
        desCard4.setOnMouseExited(event -> desCard4.setCursor(Cursor.DEFAULT));
        desCard4.setOnMouseClicked(event -> hostServices.showDocument("https://en.wikipedia.org/wiki/Symbiosis_Institute_of_Design"));

        VBox desCard5 = new VBox(10);
        desCard5.setPadding(new Insets(15));
        desCard5.setStyle(cardStyle);
        desCard5.setPrefWidth(1000);
        Label desName5 = new Label("Pearl Academy");
        desName5.setFont(cardTitleFont);
        desCard5.getChildren().addAll(desName5, new Label("Location: New Delhi, Delhi"));
        desCard5.setOnMouseEntered(event -> desCard5.setCursor(Cursor.HAND));
        desCard5.setOnMouseExited(event -> desCard5.setCursor(Cursor.DEFAULT));
        desCard5.setOnMouseClicked(event -> hostServices.showDocument("https://en.wikipedia.org/wiki/Pearl_Academy"));

        VBox desCard6 = new VBox(10);
        desCard6.setPadding(new Insets(15));
        desCard6.setStyle(cardStyle);
        desCard6.setPrefWidth(1000);
        Label desName6 = new Label("MIT Institute of Design");
        desName6.setFont(cardTitleFont);
        desCard6.getChildren().addAll(desName6, new Label("Location: Pune, Maharashtra"));
        desCard6.setOnMouseEntered(event -> desCard6.setCursor(Cursor.HAND));
        desCard6.setOnMouseExited(event -> desCard6.setCursor(Cursor.DEFAULT));
        desCard6.setOnMouseClicked(event -> hostServices.showDocument("https://en.wikipedia.org/wiki/MIT_Institute_of_Design"));

        VBox desCard7 = new VBox(10);
        desCard7.setPadding(new Insets(15));
        desCard7.setStyle(cardStyle);
        desCard7.setPrefWidth(1000);
        Label desName7 = new Label("Department of Design, IIT Guwahati");
        desName7.setFont(cardTitleFont);
        desCard7.getChildren().addAll(desName7, new Label("Location: Guwahati, Assam"));
        desCard7.setOnMouseEntered(event -> desCard7.setCursor(Cursor.HAND));
        desCard7.setOnMouseExited(event -> desCard7.setCursor(Cursor.DEFAULT));
        desCard7.setOnMouseClicked(event -> hostServices.showDocument("https://en.wikipedia.org/wiki/Department_of_Design,_IIT_Guwahati"));

        VBox desCard8 = new VBox(10);
        desCard8.setPadding(new Insets(15));
        desCard8.setStyle(cardStyle);
        desCard8.setPrefWidth(1000);
        Label desName8 = new Label("Srishti Manipal Institute of Art, Design and Technology");
        desName8.setFont(cardTitleFont);
        desCard8.getChildren().addAll(desName8, new Label("Location: Bengaluru, Karnataka"));
        desCard8.setOnMouseEntered(event -> desCard8.setCursor(Cursor.HAND));
        desCard8.setOnMouseExited(event -> desCard8.setCursor(Cursor.DEFAULT));
        desCard8.setOnMouseClicked(event -> hostServices.showDocument("https://en.wikipedia.org/wiki/Srishti_Institute_of_Art,_Design_and_Technology"));

        VBox desCard9 = new VBox(10);
        desCard9.setPadding(new Insets(15));
        desCard9.setStyle(cardStyle);
        desCard9.setPrefWidth(1000);
        Label desName9 = new Label("Indian School of Design and Innovation (ISDI)");
        desName9.setFont(cardTitleFont);
        desCard9.getChildren().addAll(desName9, new Label("Location: Mumbai, Maharashtra"));
        desCard9.setOnMouseEntered(event -> desCard9.setCursor(Cursor.HAND));
        desCard9.setOnMouseExited(event -> desCard9.setCursor(Cursor.DEFAULT));
        desCard9.setOnMouseClicked(event -> hostServices.showDocument("https://en.wikipedia.org/wiki/ISDI"));

        VBox desCard10 = new VBox(10);
        desCard10.setPadding(new Insets(15));
        desCard10.setStyle(cardStyle);
        desCard10.setPrefWidth(1000);
        Label desName10 = new Label("Arch College of Design & Business");
        desName10.setFont(cardTitleFont);
        desCard10.getChildren().addAll(desName10, new Label("Location: Jaipur, Rajasthan"));
        desCard10.setOnMouseEntered(event -> desCard10.setCursor(Cursor.HAND));
        desCard10.setOnMouseExited(event -> desCard10.setCursor(Cursor.DEFAULT));
        desCard10.setOnMouseClicked(event -> hostServices.showDocument("https://en.wikipedia.org/wiki/Arch_College_of_Design_and_Business"));

        collegeCardsBox.getChildren().addAll(
                engineeringText, engCard1, engCard2, engCard3, engCard4, engCard5, engCard6, engCard7, engCard8, engCard9, engCard10,
                medicalText, medCard1, medCard2, medCard3, medCard4, medCard5, medCard6, medCard7, medCard8, medCard9, medCard10,
                lawText, lawCard1, lawCard2, lawCard3, lawCard4, lawCard5, lawCard6, lawCard7, lawCard8, lawCard9, lawCard10,
                designText, desCard1, desCard2, desCard3, desCard4, desCard5, desCard6, desCard7, desCard8, desCard9, desCard10
        );

        VBox bottomContent = new VBox(20, collegeCardsBox);
        bottomContent.setPadding(new Insets(20));
        bottomContent.setAlignment(Pos.CENTER);

        VBox content = new VBox(backgroundPane, bottomContent);

        BorderPane contentPane = new BorderPane();
        contentPane.setLeft(sidebar);
        contentPane.setCenter(content);

        scrollPane.setContent(contentPane);

        VBox root = new VBox(appBar, scrollPane);

        return root;
    }

   

    private void startBackgroundAnimation(StackPane pane) {
        try {
            backgroundImages.add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Assets/Images/c1.jpg"))));
            backgroundImages.add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Assets/Images/c2.jpg"))));
            backgroundImages.add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Assets/Images/c3.jpg"))));
            backgroundImages.add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Assets/Images/c4.jpg"))));
            backgroundImages.add(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Assets/Images/c5.jpg"))));
        } catch (Exception e) {
            System.err.println("Could not load background images. Please check file paths.");
            pane.setStyle("-fx-background-color: #333333;"); 
            return;
        }
        updateBackground(pane);

        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(5), event -> { 
                    currentBgIndex = (currentBgIndex + 1) % backgroundImages.size();
                    updateBackground(pane);
                })
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void updateBackground(Pane pane) {
        if (backgroundImages.isEmpty()) return;

        Image image = backgroundImages.get(currentBgIndex);
        BackgroundImage backgroundImage = new BackgroundImage(
                image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(100, 100, true, true, true, true) 
        );
        pane.setBackground(new Background(backgroundImage));
    }

    private void initialializeseachpage() {
        Dummy dummy = new Dummy();
        dummy.setSearchstage(primaryStage);
        searchScene = new Scene(dummy.createSearch(this::handleBackbutton), 1550, 800);
    }
    private void initializedesignmenu(){
        Designexams designexams = new Designexams();
        designexams.setDesignStage(primaryStage);
        designtopcos = new Scene(designexams.designExam(this::handleBackbutton),1550,800);
        designexams.setDesignScene(designtopcos);

    }
    private void initializeEngcourse(){
        Enggexams enggexams = new Enggexams();
        enggexams.setEnggcourse(primaryStage);
        engtopcos= new Scene(enggexams.enggecource(this::handleBackbutton),1550,800);
        enggexams.setEnggcourse(engtopcos);
    }
    private void initializemedcourse(){

        Medicalexams medicalexams = new Medicalexams();
    medicalexams.setMedicalStage(primaryStage);
    medicaltopcos = new Scene(medicalexams.medicalcourse(this::handleBackbutton),1550,800);
    medicalexams.setMedicalScene(medicaltopcos);
    }
    private void initializelawcourse(){
        Lawexams lawexams = new Lawexams();
        lawexams.setLawStage(primaryStage);
        lawtopcos = new Scene(lawexams.lawcourse(this::handleBackbutton),1550,800);
        lawexams.setLawScene(lawtopcos);

    }
    private void initialializtopeng(){
        Topcollege topcollege = new Topcollege();
        topcollege.setTopendStage(primaryStage);
        topengScene = new Scene(topcollege.Topengcollege(this::handleBackbutton),1550,800);
    }
    private void initialializetopmed(){
        Popularcollege mecical = new Popularcollege();
        mecical.setTopmedStage(primaryStage);
        topmedScene = new Scene(mecical.topmedical(this::handleBackbutton),1550,800);
    }
    private void initialializetoplaw(){
        PopularLawcolleges popularLawcolleges = new PopularLawcolleges();
        popularLawcolleges.setToplawstage(primaryStage);
        toplawScene  = new Scene(popularLawcolleges.toplaw(this::handleBackbutton),1550,800);
    }
    private void topdesign(){
        Populardesign populardesign = new Populardesign();
        populardesign .setTopdesignStage(primaryStage);
        topdesigScene = new Scene(populardesign.topdesign(this::handleBackbutton),1550,800);
    }
    private void initialializeaboutus(){
        Aboutus aboutus = new Aboutus();
        aboutus.setAboutusStage(primaryStage);
        abautScene = new Scene(aboutus.aboutus(this::handleBackbutton),1550,800);
        }
        private void initizlizeugpage(){
            Ug ug = new Ug();
            ug.setUgState(primaryStage);
            ugScene = new Scene(ug.ugpage(this::handleBackbutton),1550,800);
            ug.setUgScene(ugScene);
        }

        private void initialializepgpage(){
            Pg pg = new Pg();
            pg.setPgState(primaryStage);
            pgScene = new Scene(pg.pgpage(this::handleBackbutton),1550,800);
            pg.setPgScene(pgScene);
        }
        private void initializechat(){
            Chatpage chatpage = new Chatpage();
            chatpage.setChatStage(primaryStage);
            chatScene = new Scene(chatpage.createChat(this::handleBackbutton),1550,800);

        }
        private void initializeprofilepage(){
            profileside profileside = new profileside();
            profileside.setProfilStage(primaryStage);
            profileScene = new Scene(profileside.creteprofile(this::handel),1550,800);
        }
        private void initializelistpage(){
            SelectedCollegesPage selectedCollegesPage = new SelectedCollegesPage();
            selectedCollegesPage.setListStage(primaryStage);
            mylistScene = new Scene(selectedCollegesPage.Createdlist(selectedColleges,this::handleBackbutton),1550,800);
        }

        private void initializecontact(){
            ContactUsPage contactUsPage = new ContactUsPage();
            contactUsPage.setContact1Stage(primaryStage);
            contactScene = new Scene(contactUsPage.createcontact(this::handleBackbutton),1550,800);
            
        }

    public void handleBackbutton() {
        primaryStage.setScene(home1Scene);
    }
    public void handel(){
            primaryStage.setScene(Loginpage.loginScene);
            primaryStage.setWidth(1600);
            primaryStage.setHeight(800);
    }
}