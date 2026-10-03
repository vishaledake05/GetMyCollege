package com.s1.view;

import com.s1.Controller.Controller;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.application.HostServices;


public class Signup{
     static Scene signupscene,homepagScene,logiScene;
     static Stage signupStage;
     
    private HostServices hostServices;

    public void setHostServices(HostServices hostServices) {
        this.hostServices = hostServices;
    }


    public void setSignupscene(Scene signupscene) {
        this.signupscene = signupscene;
    }

     public void setSignupStage(Stage signupStage) {
         this.signupStage = signupStage;
     }
    public VBox createsignup(Runnable back){
              
        Image logo = new Image("Assets\\Images\\logo-removebg-preview.png");
        ImageView imageView = new ImageView(logo);
        imageView.setFitWidth(300);
        imageView.setPreserveRatio(true);

        Image profile = new Image("Assets\\Images\\signuplogo.gif");
        ImageView profileview = new ImageView(profile);
        profileview.setFitWidth(100);
        profileview.setFitHeight(80);
        profileview.setPreserveRatio(true);

        Image image = new Image("Assets\\Images\\singupimage.jpeg");
        BackgroundImage bgImage = new BackgroundImage(
            image,
            BackgroundRepeat.NO_REPEAT,
            BackgroundRepeat.NO_REPEAT,
            BackgroundPosition.CENTER,
            new BackgroundSize(
                BackgroundSize.AUTO,
                BackgroundSize.AUTO,
                false, false, true, true
        )
        );

        Label quote = new Label("GET WHAT YOU DESIRE.");
        quote.setTextFill(Color.WHITE);
        quote.setWrapText(true);
        quote.setFont(Font.font("Arial", FontWeight.BOLD, 18));

        Label title = new Label("Create Account");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        TextField nameField = new TextField();
        nameField.setPromptText("Name");
        nameField.setMaxWidth(250);

        TextField emailField = new TextField();
        emailField.setPromptText("Email or Phone no.");
        emailField.setMaxWidth(250);

        PasswordField passwordfield = new PasswordField();
        passwordfield.setPromptText("password");
        passwordfield.setMaxWidth(250);

        PasswordField conpasswordField = new PasswordField();
        conpasswordField.setPromptText("confirm Password");
        conpasswordField.setMaxWidth(250);

        Button signupBtn = new Button("Sign Up");
        signupBtn.setStyle("-fx-font-weight: bold;");
        signupBtn.setMaxWidth(Double.MAX_VALUE);
        signupBtn.setMaxWidth(250);
        signupBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                String name=nameField.getText();
                String email1=emailField.getText();
                String pass=passwordfield.getText();

                System.out.println("Name : "+name);
                System.out.println("Email : "+email1);
                System.out.println("Pass : "+pass);
                InitializerSignup();
                signupStage.setScene(homepagScene);
                
            }
            
        });

        Controller controller = new Controller(
                    nameField, emailField, passwordfield, conpasswordField, signupBtn
        );


        Hyperlink login = new Hyperlink("Already have an Account ? Login ");
        login.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                back.run();
            }
            
        });

        VBox vb1 = new VBox(15,imageView,profileview,title, nameField, emailField, passwordfield,conpasswordField, signupBtn,login);
        vb1.setPadding(new Insets(5));
        vb1.setAlignment(Pos.CENTER);

        HBox hb1 = new HBox(vb1);
        hb1.setAlignment(Pos.CENTER);
        hb1.setMaxHeight(450);
        hb1.setMaxWidth(300);
        hb1.setStyle("-fx-background-color: rgba(255, 255, 255, 0.5);-fx-border-color:black;-fx-border-width: 2;-fx-border-radius: 5; -fx-padding: 60");

        VBox root = new VBox(hb1);
        root.setAlignment(Pos.CENTER);
        root.setBackground(new Background(bgImage));


        return root;
    }
    public void InitializerSignup(){
    Home home = new Home();
    home.setPrimaryStage(signupStage);
    home.setHostServices(hostServices);
    homepagScene = new Scene(home.createHomepage(), 1600, 800);
    home.setHome1Scene(homepagScene);
}
public static void InitializerLogin(){
    Loginpage loginpage = new Loginpage();
    loginpage.setLoginStage(signupStage);
    logiScene = new Scene(Loginpage.root,1550,800);
}

}