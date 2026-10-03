package com.s1.view;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.ScaleTransition;
 import javafx.scene.control.OverrunStyle;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Populardesign {
    Scene topdesigScene,abouScene,homepagScene,predictScene;
    public void setTopdesigScene(Scene topdesigScene) {
        this.topdesigScene = topdesigScene;
    }


    public void setTopdesignStage(Stage topdesignStage) {
        this.topdesignStage = topdesignStage;
    }


    Stage topdesignStage;

    public ScrollPane topdesign(Runnable back){

      List<String> designUrls = new ArrayList<>();
        designUrls.add("https://en.wikipedia.org/wiki/National_Institute_of_Design");                  // NID Ahmedabad
        designUrls.add("https://en.wikipedia.org/wiki/National_Institute_of_Design,_Bhopal");         // NID Bhopal
        designUrls.add("https://en.wikipedia.org/wiki/National_Institute_of_Design,_Kurukshetra");    // NID Kurukshetra
        designUrls.add("https://en.wikipedia.org/wiki/Indian_Institute_of_Technology_Bombay#IDC_School_of_Design"); // IIT Bombay IDC
        designUrls.add("https://en.wikipedia.org/wiki/Indian_Institute_of_Technology_Delhi#Department_of_Design");     // IIT Delhi Design
        designUrls.add("https://en.wikipedia.org/wiki/Indian_Institute_of_Technology_Guwahati#Design_School");        // IIT Guwahati
        designUrls.add("https://en.wikipedia.org/wiki/Indian_Institute_of_Technology_Hyderabad#School_of_Design");    // IIT Hyderabad
        designUrls.add("https://en.wikipedia.org/wiki/National_Institute_of_Fashion_Technology_Delhi");              // NIFT Delhi
        designUrls.add("https://en.wikipedia.org/wiki/Indian_Institute_of_Information_Technology_Design_and_Manufacturing,_Jabalpur"); // IIITDM Jabalpur
        designUrls.add("https://en.wikipedia.org/wiki/Indian_Institute_of_Art_and_Design");                          // IIAD Delhi
 


                List<String> designNames = Arrays.asList(
            "National Institute of Design (NID)  Ahmedabad",
                "National Institute of Design (NID)  Bhopal",
                "National Institute of Design (NID)  Kurukshetra",
                "IIT Bombay  IDC School of Design",
                "IIT Delhi  Department of Design",
                "IIT Guwahati  Design School",
                "IIT Hyderabad  School of Design",
                "National Institute of Fashion Technology (NIFT)  Delhi",
                "IIITDM Jabalpur  Design School",
                "Indian Institute of Art and Design (IIAD)  Delhi"
        );
        List<String> designLocations = Arrays.asList(
            "Ahmedabad\tGujarat",
            "Bhopal\tMadhya Pradesh",
            "Kurukshetra\tHaryana",
            "Mumbai\tMaharashtra",
            "New Delhi\tDelhi",
            "Guwahati\tAssam",
            "Hyderabad\tTelangana",
            "New Delhi\tDelhi",
            "Jabalpur\tMadhya Pradesh",
            "New Delhi\tDelhi"
        );
        
        

        ImageView logo = new ImageView(new Image("Assets\\images\\logo-removebg-preview.png"));
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

        Image profileImage = new Image("Assets\\images\\home profie.png");
        ImageView profileImageView = new ImageView(profileImage);
        profileImageView.setFitHeight(45);
        profileImageView.setFitWidth(45);
        profileImageView.setOnMouseClicked(e -> System.out.println("Profile pressed"));

        Circle clip = new Circle(40, 40, 40); // centerX, centerY, radius = half the size
        profileImageView.setClip(clip);

        Circle border = new Circle(40, 40, 40);
        border.setStroke(Color.LIGHTGRAY);
        border.setStrokeWidth(2);
        border.setFill(null);

        StackPane avatar = new StackPane(border, profileImageView);
        avatar.setEffect(new DropShadow(4, Color.gray(0, 0.4)));

        Button threeline = new Button("☰");
        threeline.setStyle("-fx-background-radius: 50%; -fx-font-size: 15; -fx-background-color: #ff4081; -fx-text-fill: white;");
        threeline.setPrefSize(50, 50);

        MenuItem homeItem = new MenuItem("🏠 Home");
MenuItem myListItem = new MenuItem("📄 My List");
MenuItem predictCollegeItem = new MenuItem("📊 Predict My College");
MenuItem contactUsItem = new MenuItem("📞 Contact Us");
MenuItem aboutUsItem = new MenuItem("ℹ️ About Us");
MenuItem popularCollegesItem = new MenuItem("🔥 Popular Colleges");

aboutUsItem.setOnAction(new EventHandler<ActionEvent>() {

    @Override
    public void handle(ActionEvent arg0) {
        initialializeaboutus();
        topdesignStage.setScene(abouScene);
      
       
    }
    
});
homeItem.setOnAction(new EventHandler<ActionEvent>() {

    @Override
    public void handle(ActionEvent arg0) {
        InitializerHome();
        topdesignStage.setScene(homepagScene);
    }
    
});
myListItem.setOnAction(new EventHandler<ActionEvent>() {

    @Override
    public void handle(ActionEvent arg0) {
     
    }
    
});
popularCollegesItem.setOnAction(new EventHandler<ActionEvent>() {

    @Override
    public void handle(ActionEvent arg0) {
   
    }
    
});
contactUsItem.setOnAction(new EventHandler<ActionEvent>() {

    @Override
    public void handle(ActionEvent arg0) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'handle'");
    }
    
}); popularCollegesItem.setOnAction(new EventHandler<ActionEvent>() {

    @Override
    public void handle(ActionEvent arg0) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'handle'");
    }
    
});

// 3. Create the ContextMenu with the configured items
ContextMenu contextMenu = new ContextMenu(
    homeItem,
    myListItem,
    predictCollegeItem,
    contactUsItem,
    aboutUsItem,
    popularCollegesItem
);
        threeline.setOnAction(e -> contextMenu.show(threeline, Side.BOTTOM, 0, 0));

        Region spacer1 = new Region();
        spacer1.setPrefWidth(300);

        Region spacer2 = new Region();
        spacer2.setPrefWidth(400);

        HBox topRow = new HBox(20, threeline, logo, spacer1, searchBox, spacer2, avatar);
        topRow.setAlignment(Pos.CENTER_LEFT);
        topRow.setPadding(new Insets(10, 50, 10, 0));
        topRow.setBackground(new Background(new BackgroundFill(Color.rgb(63, 81, 181), CornerRadii.EMPTY, Insets.EMPTY)));
        Text tx = new Text("Top 10 Design Colleges");
        tx.setFont(new Font(30));
        tx.setTextAlignment(TextAlignment.CENTER);
      Button baackButton = new Button("Back");
        baackButton.setPadding(new Insets(10));
        baackButton.setStyle("-fx-background-color: .rgb(63, 81, 181)");
        baackButton.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
            back.run();
            }
           
        });
           
        HBox main1 = new HBox(10,baackButton,tx);
        VBox vb = new VBox(topRow,main1);
        vb.setSpacing(15);
        
        for(int i=0;i<designUrls.size();i++){
                final int index =i;
                VBox vb2 = new VBox();
                VBox vb1 = new VBox();
                
               Label nameLabel = new Label(designNames.get(index));
                nameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
                nameLabel.setStyle("-fx-text-fill: #2c3e50; -fx-padding: 5 0 3 5;");
                nameLabel.setWrapText(true);
                nameLabel.setMaxWidth(Double.MAX_VALUE);
                nameLabel.setTextOverrun(OverrunStyle.CLIP);

                Label locationLabel = new Label(designLocations.get(index));
                locationLabel.setStyle("-fx-text-fill: #555;");
                locationLabel.setFont(Font.font("Arial", 11)); 
            

                nameLabel.setOnMouseClicked(e->{
                    try {
                            Desktop.getDesktop().browse(new URI(designUrls.get(index)));
                        } catch (IOException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        } catch (URISyntaxException e1) {
                            // TODO Auto-generated catch block
                            e1.printStackTrace();
                        }
                    });
                
               
                
                vb2.setBackground(new Background(new BackgroundFill(
                 Color.WHITE, new CornerRadii(20), Insets.EMPTY)));
                vb2.setPadding(new Insets(20));
                vb2.setEffect(new DropShadow(8, 0, 2, Color.rgb(0,0,0,0.5)));

                Rectangle clip1 = new Rectangle();
                clip1.widthProperty().bind(vb2.widthProperty());
                clip1.heightProperty().bind(vb2.heightProperty());
                clip1.setArcWidth(20);
                clip1.setArcHeight(20);
                vb2.setClip(clip1);


                // vb2.setPrefWidth(700);
                // vb2.setPrefHeight(80);
                vb2.setSpacing(15);
                vb1.setMaxWidth(700);
                vb1.getChildren().add(nameLabel);
                vb1.getChildren().add(locationLabel);
                
                vb2.getChildren().add(vb1); 
                vb2.setStyle("-fx-background-color: rgba(255,255,255,0.2);"); 
                // GaussianBlur blur = new GaussianBlur(10); // adjust radius to taste
                // hb1.setEffect(blur);  
                ScaleTransition st5 = new ScaleTransition(javafx.util.Duration.millis(200), vb2);
                vb2.setOnMouseEntered(e -> {
                    st5.setToX(1.05);
                    st5.setToY(1.05);
                    st5.playFromStart();
                });
                vb2.setOnMouseExited(e -> {
                    st5.setToX(1);
                    st5.setToY(1);
                    st5.playFromStart();
                });
                                           
                             
                vb.getChildren().add(vb2);
                vb.setStyle("-fx-background-color: radial-gradient(center 50% 60%, radius 80%, white 0%, #e0e8ff 40%, #a0b8ff 80%,#7080ff 100%)"); 
                   

        } 
        //vb.setPadding(new Insets(40));
        
        ScrollPane scrollPane = new ScrollPane(vb);

        return scrollPane;
    }
    private void InitializerHome(){
        Home home = new Home();
        home.setPrimaryStage(topdesignStage);
    homepagScene = new Scene(home.createHomepage(),1500,800);
    }
    private void initialializeaboutus(){
        Aboutus aboutus = new Aboutus();
        aboutus.setAboutusStage(topdesignStage);
        abouScene = new Scene(aboutus.aboutus(this::handel),1500,800);
    }
    private void handel(){
        topdesignStage.setScene(Home.home1Scene);

    }
    

} 