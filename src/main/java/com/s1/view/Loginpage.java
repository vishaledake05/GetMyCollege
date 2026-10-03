package com.s1.view;

import com.s1.Controller.Logincontroller;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Loginpage extends Application{
    static String str;
    static VBox root;
    Stage loginStage,sign_upStage;
    static String email;
    Signup signup = new Signup();
     
    public void setLoginStage(Stage loginStage) {
        this.loginStage = loginStage;
    }


    public static void setLoginScene(Scene loginScene) {
        Loginpage.loginScene = loginScene;
    }
    static Scene loginScene,Sign_upScene,HomeScene,forgotScene;
    @Override
    public void start(Stage myStage) throws Exception {
        signup.setHostServices(getHostServices());
        
         Image logo = new Image("Assets\\images\\logo-removebg-preview.png");
         ImageView mylogo = new ImageView(logo);
         mylogo.setPreserveRatio(true);
         mylogo.setFitWidth(300);
        
        Image profile = new Image("Assets\\images\\profile_gif.gif");
        ImageView myprofile = new ImageView(profile);
        myprofile.setPreserveRatio(true);
        myprofile.setFitWidth(100);
        myprofile.setFitHeight(80);

        VBox vb1 = new VBox(mylogo,myprofile);
        vb1.setAlignment(Pos.CENTER);

        Text print = new Text();
        print.setFont(new Font(15));
        print.setFill(Color.RED);
        
        Text User = new Text("Email-id");
        
        TextField textField =new TextField();
        
        textField.setMaxWidth(200);
        HBox hb = new HBox(User);
        hb.setMaxWidth(200);
        textField.setPromptText("Enter Email-id");
        email=textField.getText();
        Text password = new Text("Password");
       
        PasswordField pass = new PasswordField();
        HBox hb2 = new HBox(password);
        hb2.setMaxWidth(200);
        pass.setMaxWidth(200);
        
        pass.setPromptText("Password");
        Button login = new Button("Login");
        login.setMaxWidth(100);
        login.setFont(new Font(15));
        login.setStyle("-fx-background-color:white");
        login.setOnAction(new EventHandler<ActionEvent>() {
            @Override
           
            public void handle(ActionEvent arg0) {
                 InitializerHome();
                        loginStage.setScene(HomeScene);
            
            
            }      
        });
        Hyperlink Sign_up = new Hyperlink("Sign up");
        Sign_up.setFont(new Font(15));
        Sign_up.setFocusTraversable(false);
        Sign_up.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                InitializerSignup();
                loginStage.setScene(Sign_upScene);  
            }
            
        });
        Hyperlink forgot = new Hyperlink("forgot password");
        forgot.setFont(new Font(15));
        forgot.setFocusTraversable(false);
        forgot.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent arg0) {
                initializeforgetpage();
                loginStage.setScene(forgotScene);
            }
            
        });

        Logincontroller loginController = new Logincontroller(
            textField,     // Email field
            pass,          // Password field
            print,         // Text used for showing errors
            login,         // Login button
            () -> {        // On success
                InitializerHome();
                loginStage.setScene(HomeScene);
        }
);
        VBox vb = new VBox(10,vb1,hb,textField,hb2,pass,login,print,Sign_up,forgot);
        vb.setAlignment(Pos.CENTER);
        HBox hb1 = new HBox(vb);
        hb1.setMaxHeight(450);
        hb1.setMaxWidth(300);
        hb1.setStyle("-fx-background-color: rgba(255, 255, 255, 0.5);-fx-border-color:black;-fx-border-width: 2;-fx-border-radius: 5; -fx-padding: 60");

        
       
        Image image = new Image("Assets\\images\\login_image.jpg");
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
       
        root = new VBox(hb1);
        root.setAlignment(Pos.CENTER);
        root.setBackground(new Background(bgImage));
        Scene sc =new Scene(root);
        myStage.getIcons().add(logo);
        myStage.setScene(sc);
        myStage.setMaximized(true);
        myStage.setTitle("GetMyCollege");
        myStage.show();
        loginStage=myStage;
        loginScene=sc;
    }


    private void InitializerSignup(){

        signup.setSignupStage(loginStage);
        Sign_upScene = new Scene(signup.createsignup(this::handleback),1550,800);
        signup.setSignupscene(Sign_upScene);
        


    }
    private void InitializerHome(){
        Home home = new Home();
        home.setPrimaryStage(loginStage);
        HomeScene = new Scene(home.createHomepage(),1550,800);
        home.setHome1Scene(HomeScene);
        home.setHostServices(getHostServices());
    }
    private void handleback(){
        loginStage.setScene(loginScene);
        loginStage.setMinHeight(1300);
        loginStage.setMinWidth(1600);
    }
    private void initializeforgetpage(){
        ForgotPasswordUI forgotPasswordUI = new ForgotPasswordUI();
        forgotPasswordUI.setForgotStage(loginStage);
        forgotScene = new Scene(forgotPasswordUI.createforgotpage(this::handleBackButton),1550,800);
    }
    private void handleBackButton(){
        loginStage.setScene(loginScene);
    }
}

        