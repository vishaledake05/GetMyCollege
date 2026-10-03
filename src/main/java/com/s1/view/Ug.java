package com.s1.view;

import javax.print.attribute.standard.MediaSize.Engineering;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Node;
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

public class Ug {
static String Cast ;
 public static String Engineeringug;
 public static String Exam;
static  String Branch;
 static String Percent;
 static double per;
    Scene ugScene, collegScene;
    Stage ugState;
    public void setUgScene(Scene ugScene) {
        this.ugScene = ugScene;
    }
    public void setUgState(Stage primaryStage) {
        this.ugState = primaryStage;
    }

    @SuppressWarnings("unused") 


     VBox ugpage(Runnable back){
            
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
   
      
      Text Stream = new Text("Streams");
      HBox streamBox = new HBox(Stream);
      Stream.setFont(new Font(15));
      streamBox.setMaxWidth(250);
       ComboBox<String> stramcombo = new ComboBox<>();
       stramcombo.setMaxWidth(250);
       stramcombo.setPromptText("Enter Stream");
        stramcombo.getItems().addAll("Engineering", "Medical", "Law","Design");
        stramcombo.setEditable(true);
        stramcombo.setPromptText("Select Your Stream... ");
        stramcombo.setOnAction(e -> {
           Engineeringug = stramcombo.getValue();
        });

      Text exam = new Text("Exam");
       HBox examBox = new HBox(exam);
       exam.setFont(new Font(15));
    
      examBox.setMaxWidth(250);
 ComboBox<String> examcombo = new ComboBox<>();
       examcombo.setMaxWidth(250);
       examcombo.getItems().clear();
       examcombo.setPromptText("Enter Exam");
       examcombo.setOnAction(new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent arg0) {
           Exam=examcombo.getValue();
        }
        
       });
     stramcombo.valueProperty().addListener((obs, oldVal, newVal) -> {
    examcombo.getItems().clear();
    if ("Engineering".equals(newVal)) {
        examcombo.getItems().addAll("JEE", "MHT-CET","WBJEE","KCET","SRMJEE","COMEDK","BITSAT");
        Exam = examcombo.getValue();
    } else if ("Medical".equals(newVal)) {
       examcombo.getItems().addAll("NEET", "MHT-CET(PCM)","GUJCET","BHU-PMT","COMEDK UGET");
       Exam=examcombo.getValue();
    } else if ("Law".equals(newVal)) {
      examcombo.getItems().addAll( "MHT-CET(Law)","AILET","LASAT INDIA","SLAT","CUUET(LAW)");
       Exam=examcombo.getValue();
    } else if ("Design".equals(newVal)) {
      examcombo.getItems().addAll("MHT-CET(Design)","NID","MITID DAT","SEAT","AIEED");
       Exam=examcombo.getValue();
    } else {
        examcombo.setPromptText("First Enter The Stream ...!");
    } {
      
    }{
      
    }
    
});

      Text Percentile= new Text("Percentile");
      Percentile.setFont(new Font(15));
      
       HBox perBox = new HBox(Percentile);
       perBox.setMaxWidth(250);

     ComboBox<String> precentcombo = new ComboBox<>();
       precentcombo.setMaxWidth(250);
       precentcombo.getItems().clear();
       precentcombo.setEditable(true);
        precentcombo.setPromptText("Enter Exam");
       precentcombo.setOnAction(new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent arg0) {
           Percent = precentcombo.getValue();
           per =Double.parseDouble(Percent);
        }
        
       });
     
    
      Text State = new Text("State");
      State.setFont(new Font(15));
       HBox stateBox = new HBox(State);
      stateBox.setMaxWidth(250);
    ComboBox<String> statecombo = new ComboBox<>();
       statecombo.setMaxWidth(250);
       statecombo.setPromptText("Enter State");
        statecombo.getItems().addAll("Maharashtra","karnatak","Tenagana","Assam","Goa","Haryana","kerala","Odisha","Tamil Nadu");
        statecombo.setEditable(true);
        statecombo.setPromptText("Select Your branch... ");
        statecombo.setOnAction(e -> {
            
        });
        Text branch = new Text("Branch");
        HBox brancHBox =  new HBox(branch);
        branch.setFont(new Font(15));
        brancHBox.setMaxWidth(250);
      ComboBox<String> branchcombo = new ComboBox<>();
        branchcombo.setMaxWidth(250);
        branchcombo.setEditable(true);
        branchcombo.setPromptText("Select Your Stream... ");
        branchcombo.setOnAction(e -> {
           Branch=branchcombo.getValue();
        });
          stramcombo.valueProperty().addListener((obs, oldVal, newVal2) -> {
    branchcombo.getItems().clear();
    if ("Engineering".equals(newVal2)) {
       branchcombo.getItems().addAll("CS","IT","Mechanical","Electrical","ENTC","AIDS","Chemical Engg");
        Branch=branchcombo.getValue();
    } else if ("Medical".equals(newVal2)) {
       branchcombo.getItems().addAll("MBBS", "BHMS","BDS","BAMS","B.pharmacy","D.pharmacy","BPT");
        Branch=branchcombo.getValue();
    } else if ("Law".equals(newVal2)) {
      branchcombo.getItems().addAll( "LLB","B.A.LLB","B.Com.LLB","B.Tech.LLB","B.BA.LLB");
       Branch=branchcombo.getValue();
    } else if ("Design".equals(newVal2)) {
      branchcombo.getItems().addAll("MHT-CET(Design)");
       Branch=branchcombo.getValue();
    } else {
        branchcombo.setPromptText("First Enter The Branch ...!");
    } {
      
    }{
      
    }
});
      Text Gender = new Text("Gender");
      Gender.setFont(new Font(15));
      Text Categery = new Text("Categery");
      Categery.setFont(new Font(15));
      ComboBox<String> Gendercombo = new ComboBox<>();
       Gendercombo.setMaxWidth(250);
       Gendercombo.setPromptText("Enter Stream");
        Gendercombo.getItems().addAll("Male","Female","Other");
        Gendercombo.setEditable(true);
        Gendercombo.setPromptText("Select Gender ");

        ComboBox<String> catcombo = new ComboBox<>();
       catcombo.setMaxWidth(250);
       catcombo.setPromptText("Enter Stream");
        catcombo.getItems().addAll("Open","OBC","NT-A","NT-B","NT-C","ST","SC");
        catcombo.setEditable(true);
        catcombo.setPromptText("Select Categery ");
        catcombo.setOnAction(new EventHandler<ActionEvent>() {

          @Override
          public void handle(ActionEvent arg0) {
           Cast = catcombo.getValue();


          }
          
        });

        HBox catgen = new HBox(5,Gender,Gendercombo,Categery,catcombo);
      catgen.setAlignment(Pos.CENTER);
      catgen.setMaxWidth(250);

      Button predictbButton = new Button("Predict College");
      predictbButton.setAlignment(Pos.CENTER);
      predictbButton.setMaxWidth(100);
      predictbButton.setOnAction(new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent arg0) {
           collegeinitializer();
           ugState.setScene(collegScene);
           
        }
        
      });


      Button backButton = new Button("Back");
      backButton.setStyle("-fx-background-color: #f26722; -fx-text-fill: white; -fx-font-weight: bold;");
      backButton.setOnAction(new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent arg0) {
          back.run();
        }
        
      });
      HBox backBox = new HBox(backButton);
      backBox.setAlignment(Pos.BOTTOM_LEFT);
      backBox.setPadding(new Insets(10));
    
      VBox ugBox= new VBox(15,streamBox,stramcombo,brancHBox,branchcombo,examBox,examcombo,catgen,perBox,precentcombo,stateBox,statecombo,predictbButton);
      ugBox.setAlignment(Pos.CENTER);
   HBox hb = new HBox(5,backBox,imageView,vb3,ug);
   hb.setStyle("-fx-border-color: black; -fx-border-width: 2;");
      hb.setMaxHeight(40);

      VBox main = new VBox(topRow,hb,ugBox);
      VBox mainBox = new VBox(140,main);
       mainBox.setStyle("-fx-background-color: radial-gradient(center 50% 60%, radius 80%, white 0%, #e0e8ff 40%, #a0b8ff 80%, #7080ff 100% )");
        return  mainBox;
        
    }
   
    private void collegeinitializer(){
        Colleges colleges = new Colleges();
        colleges.setCollegStage(ugState);
        collegScene = new Scene(colleges.collegemethode(this::handel),1550,800);
        colleges.setCollegScene(collegScene);

    }
    private void handel(){
        ugState.setScene(ugScene);
    }


  
}
