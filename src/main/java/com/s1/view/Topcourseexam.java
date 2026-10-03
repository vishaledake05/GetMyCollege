package com.s1.view;

import com.s1.Controller.Controllerexam;
import com.s1.Model.*;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Topcourseexam {
    public Controllerexam controller = new Controllerexam();
        public TextArea outputArea;
Scene collegScene;
public void set(Scene collegScene) {
    this.collegScene = collegScene;
}

public void setCollegestage(Stage collegestage) {
    this.collegestage = collegestage;
}

Stage collegestage;

    public VBox collegemethode(Runnable back){


    
    Label header = new Label("Firestore Caste Filter Results");
    header.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #2c3e50;");


    outputArea = new TextArea();
    outputArea.setEditable(false);
    outputArea.setWrapText(true);
    outputArea.setStyle("-fx-background-color: radial-gradient(center 50% 60%, radius 80%, white 0%, #e0e8ff 40%, #a0b8ff 80%, #7080ff 100% )");
    // outputArea.setStyle(
    //     "-fx-font-family: 'Consolas';" +
    //     "-fx-font-size: 14px;" +
    //     "-fx-control-inner-background: #ffffff;" +
    //     "-fx-border-color: #dcdcdc;" +
    //     "-fx-border-radius: 5;" +
    //     "-fx-padding: 10;" +
    //     "-fx-focus-color: transparent;"
    // );
    VBox.setVgrow(outputArea, Priority.ALWAYS);
    Button backButton = new Button();
     backButton.setPadding(new Insets(10));
    backButton.setOnAction(new EventHandler<ActionEvent>() {

        @Override
        public void handle(ActionEvent arg0) {
         back.run();
        }
        
    });
    
    VBox root = new VBox(15, header,backButton, outputArea);
    root.setPadding(new Insets(20));
    root.setStyle("-fx-background-color: #f7f9fb;");

root.setStyle("-fx-background-color: radial-gradient(center 50% 60%, radius 80%, white 0%, #e0e8ff 40%, #a0b8ff 80%, #7080ff 100% )");    


    controller = new Controllerexam();
    controller.fetchAndDisplay(Form.cast, Form.branch,99.99, outputArea); // Corrected call

    return root;
}

}