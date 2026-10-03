package com.s1.view;

import java.lang.Thread.State;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Pg {
  public static String Exam;
  public static  String Engineeringpg;
  static String Branch;
  static String Cast;
  static String percet;
  static double per;

    Scene pgScene,collegeScene;
     Stage pgStage;
    public void setPgScene(Scene pgScene){
        this.pgScene = pgScene;
    }
    public void setCollegeScene(Scene collegeScene) {
        this.collegeScene = collegeScene;
    }
    public void setPgState(Stage pgStage) {
        this.pgStage = pgStage;
    }
    @SuppressWarnings("unused")
     VBox pgpage(Runnable back){
               HBox menubar = new HBox();

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



     //  menubar.setStyle("-fx-background-color: orange;");
      menubar.setMaxHeight(77);
       Text pgLabel = new Text("PG");
     pgLabel.setStyle("-fx-stroke: black");
     VBox pg = new VBox(pgLabel);
     pg.setPadding(new Insets(15));


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
      streamBox.setMaxWidth(250);
       ComboBox<String> stramcombo = new ComboBox<>();
       stramcombo.setMaxWidth(250);
       stramcombo.setPromptText("Enter Stream");
        stramcombo.getItems().addAll("Engineering", "Medical", "Law","Design");
        stramcombo.setEditable(true);
        stramcombo.setPromptText("Select Your Stream... ");
        stramcombo.setOnAction(e -> {
           Engineeringpg = stramcombo.getValue();
        });

      Text exam = new Text("Exam");
       HBox examBox = new HBox(exam);
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
        examcombo.getItems().addAll("GATE", "MIT-WPU(M.Tech)","CEED","JAM","CUET-PG");
          Exam=examcombo.getValue();
    } else if ("Medical".equals(newVal)) {
       examcombo.getItems().addAll("NEET-PG", "INI-CET(AIIMS-PG)","NEET-SS");
         Exam=examcombo.getValue();
    } else if ("Law".equals(newVal)) {
      examcombo.getItems().addAll( "CLAT-PG","AILET-PG","DU LL.M","PU-CET-PG");
        Exam=examcombo.getValue();
    } else if ("Design".equals(newVal)) {
      examcombo.getItems().addAll("CEED","NID-DAT","AIEED","UID-DAT");
        Exam=examcombo.getValue();
    } else {
        examcombo.setPromptText("First Enter The Stream ...!");
          Exam=examcombo.getValue();
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
           percet= precentcombo.getValue();
           per =Double.parseDouble(percet);
        }
        
       });
     
    

      Text State = new Text("State");
       HBox stateBox = new HBox(State);
      stateBox.setMaxWidth(250);
      ComboBox<String> statecombo = new ComboBox<>();
       statecombo.setMaxWidth(250);
       statecombo.setPromptText("Enter State");
        statecombo.getItems().addAll("Maharashtra","karnatak","Tenagana","Assam","Goa","Haryana","kerala","Odisha","Tamil Nadu");
        statecombo.setEditable(true);
        statecombo.setPromptText("Select Your Stream... ");
        statecombo.setOnAction(e -> {
            
        });
         Text branch = new Text("Branch");
        HBox brancHBox =  new HBox(branch);
        brancHBox.setMaxWidth(250);
      ComboBox<String> branchcombo = new ComboBox<>();
        branchcombo.setMaxWidth(250);
       branchcombo.setPromptText("Enter State");
        branchcombo.setEditable(true);
        branchcombo.setPromptText("Select Your Stream... ");
        branchcombo.setOnAction(e -> {
          Branch = branchcombo.getValue();
            
        });
        Runnable updateBranchOptions = () -> {
            branchcombo.getItems().clear();
            if ("Engineering".equals(stramcombo.getValue())
                    && "GATE".equals(examcombo.getValue())) {
                branchcombo.getItems().addAll(
                        "CSE", "Civil Engineering", "Data Analytics", "ECE", "VLSI Design"
                );
            } else if ("Engineering".equals(stramcombo.getValue())) {
                branchcombo.getItems().addAll(
                        "CS", "Information Technology", "Mechanical", "Electrical",
                        "ENATC", "AIDS", "Chemical Engineering"
                );
            } else if ("Medical".equals(stramcombo.getValue())) {
                branchcombo.getItems().addAll(
                        "MD(Surgeon)", "Orthopedics", "dematology", "MS(Surgeon)", "Anesthesia(Diploma)"
                );
            } else if ("Law".equals(stramcombo.getValue())) {
                branchcombo.getItems().addAll("Constitution Law", "Criminal law", "International Law");
            } else if ("Design".equals(stramcombo.getValue())) {
                branchcombo.getItems().addAll(
                        "Graphis Design", "Fashion Design", "Interior Design,", "Product Design"
                );
            }
            branchcombo.setValue(null);
            Branch = null;
        };
        stramcombo.valueProperty().addListener((obs, oldVal, newVal) -> updateBranchOptions.run());
        examcombo.valueProperty().addListener((obs, oldVal, newVal) -> updateBranchOptions.run());

 Text Gender = new Text("Gender");
      Text Categery = new Text("Categery");
      ComboBox<String> Gendercombo = new ComboBox<>();
       Gendercombo.setMaxWidth(250);
       Gendercombo.setPromptText("Enter Stream");
        Gendercombo.getItems().addAll("Male","Female","Other");
        Gendercombo.setEditable(true);
        Gendercombo.setPromptText("Select Gender ");

        ComboBox<String> catcombo = new ComboBox<>();
       catcombo.setMaxWidth(250);
       catcombo.setPromptText("Enter Stream");
        catcombo.getItems().addAll("OPEN", "OBC", "NT-A", "NT-B", "NT-c", "ST", "St-c");
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
          Engineeringpg = stramcombo.getValue();
          Exam = examcombo.getValue();
          Branch = branchcombo.getValue();
          Cast = catcombo.getValue();
          String scoreText = precentcombo.getEditor().getText().trim();
          if (Engineeringpg == null || Exam == null || Branch == null || Cast == null || scoreText.isEmpty()) {
            new Alert(Alert.AlertType.WARNING,
                    "Select a stream, exam, branch, and category, and enter your score.").showAndWait();
            return;
          }
          try {
            per = Double.parseDouble(scoreText);
          } catch (NumberFormatException ex) {
            new Alert(Alert.AlertType.WARNING, "Enter a valid numeric score.").showAndWait();
            return;
          }
        collegeinitializer();
        pgStage.setScene(collegeScene);
           
        }
        
      });
       Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS); 

      Button backButton = new Button("Back");
      backButton.setOnAction(new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent arg0) {
          back.run();
        }
        
      });
      VBox backBox = new VBox(backButton);
      backBox.setPadding(new Insets(10));
      
      
      VBox ugBox= new VBox(10,streamBox,stramcombo,brancHBox,branchcombo,examBox,examcombo,catgen,perBox,precentcombo,stateBox,statecombo,predictbButton);
      ugBox.setAlignment(Pos.CENTER);
       HBox hb = new HBox(5,backBox,imageView,vb3 ,pg);
      hb.setMaxHeight(40);
      hb.setStyle("-fx-border-color:black; -fx-border-width: 2;");
      VBox main = new VBox(topRow,hb,ugBox);
      Region spacerRegion = new Region();
      VBox.setVgrow(spacer, Priority.ALWAYS);

     VBox mainBox = new VBox(105,main,spacerRegion);
     mainBox.setStyle("-fx-background-color: radial-gradient(center 50% 60%, radius 80%, white 0%, #e0e8ff 40%, #a0b8ff 80%, #7080ff 100% )");
        
        return mainBox;

    }
    @SuppressWarnings("unused")
    private void collegeinitializer(){
        collegepg collegepg = new collegepg();
        collegepg.setCollegpgStage(pgStage);
        collegeScene = new Scene(collegepg.collegemethode(this::handel),1550,800);
        collegepg.setCollegpgScene(pgScene);
    }
    private void handel(){
    pgStage.setScene(pgScene);
    }

}
