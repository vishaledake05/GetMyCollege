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


public class PopularLawcolleges{

    Scene toplawscene,homepagScene,aboutuScene;
    Stage toplawstage;

    public void setToplawscene(Scene topdesigScene) {
        this.toplawscene = topdesigScene;
    }

    public void setToplawstage(Stage topdesignStage) {
        this.toplawstage = topdesignStage;
    }

    public ScrollPane toplaw(Runnable back){

      List<String> lawUrls = new ArrayList<>();
            lawUrls.add("https://en.wikipedia.org/wiki/National_Law_School_of_India_University");       // NLSIU Bengaluru
            lawUrls.add("https://en.wikipedia.org/wiki/National_Law_University,_Delhi");                // NLU Delhi
            lawUrls.add("https://en.wikipedia.org/wiki/NALSAR_University_of_Law");                      // NALSAR, Hyderabad
            lawUrls.add("https://en.wikipedia.org/wiki/West_Bengal_National_University_of_Juridical_Sciences"); // WB NUJS Kolkata
            lawUrls.add("https://en.wikipedia.org/wiki/Symbiosis_International_University#Symbiosis_Law_School"); // Symbiosis Law School Pune
            lawUrls.add("https://en.wikipedia.org/wiki/Jamia_Millia_Islamia#Faculty_of_Law");           // Jamia Millia Islamia – Law
            lawUrls.add("https://en.wikipedia.org/wiki/Government_Law_College,_Mumbai");                // GLC Mumbai
            lawUrls.add("https://en.wikipedia.org/wiki/Jindal_Global_Law_School");                      // Jindal Global Law School
            lawUrls.add("https://en.wikipedia.org/wiki/Army_Institute_of_Law");                         // Army Institute of Law, Mohali
            lawUrls.add("https://en.wikipedia.org/wiki/Government_Law_College,_Pune");                  // Government Law College, Pune
                    
 


                List<String> lawNames = Arrays.asList(
                "National Law School of India University (NLSIU)",
                "National Law University (NLU) Delhi",
                "NALSAR University of Law",
                "West Bengal National University of Juridical Sciences (WB NUJS)",
                "Symbiosis Law School, Pune",
                "Jamia Millia Islamia – Faculty of Law",
                "Government Law College, Mumbai",
                "Jindal Global Law School",
                "Army Institute of Law, Mohali",
                "Government Law College, Pune"
            );

        List<String> lawLocations = Arrays.asList(
            "Bengaluru\tKarnataka",
            "New Delhi\tDelhi",
            "Hyderabad\tTelangana",
            "Kolkata\tWest Bengal",
            "Pune\tMaharashtra",
            "New Delhi\tDelhi",
            "Mumbai\tMaharashtra",
            "Sonipat\tHaryana",
            "Mohali\tPunjab",
            "Pune\tMaharashtra"
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

        Region spacer1 = new Region();
        spacer1.setPrefWidth(300);

        Region spacer2 = new Region();
        spacer2.setPrefWidth(400);

        HBox topRow = new HBox(20, logo, spacer1, searchBox, spacer2, avatar);
        topRow.setAlignment(Pos.CENTER_LEFT);
        topRow.setPadding(new Insets(10, 50, 10, 0));
        topRow.setBackground(new Background(new BackgroundFill(Color.rgb(63, 81, 181), CornerRadii.EMPTY, Insets.EMPTY)));

        Text tx = new Text("Top 10 Law Colleges");
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

        
        for(int i=0;i<lawUrls.size();i++){
                final int index =i;
                VBox vb2 = new VBox();
                VBox vb1 = new VBox();
                
               Label nameLabel = new Label(lawNames.get(index));
                nameLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
                nameLabel.setStyle("-fx-text-fill: #2c3e50; -fx-padding: 5 0 3 5;");
                nameLabel.setWrapText(true);
                nameLabel.setMaxWidth(Double.MAX_VALUE);
                nameLabel.setTextOverrun(OverrunStyle.CLIP);

                Label locationLabel = new Label(lawLocations.get(index));
                locationLabel.setStyle("-fx-text-fill: #555;");
                locationLabel.setFont(Font.font("Arial", 11)); 
            

                nameLabel.setOnMouseClicked(e->{
                    try {
                            Desktop.getDesktop().browse(new URI(lawUrls.get(index)));
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
        home.setPrimaryStage(toplawstage);
        homepagScene = new Scene(home.createHomepage(),1500,800);
    }
    private void initialializeaboutus(){
        Aboutus aboutus = new Aboutus();
        aboutus.setAboutusStage(toplawstage);
        aboutuScene = new Scene(aboutus.aboutus(this::handel2),1500,800);
    }
    private void handel2(){
        toplawstage.setScene(Home.home1Scene);

    }

} 