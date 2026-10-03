package com.s1.view;
import com.s1.Model.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
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
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

public class SelectedCollegesPage {
    Stage listStage;

    public void setListStage(Stage primaryStage) {
        this.listStage = primaryStage;
    }

    //Receives selected colleges + back callback
    public VBox Createdlist(List<nextpage> selectedColleges, Runnable back) {

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

        Text mylist = new Text("MyList");
        mylist.setFont(new Font(20));
        mylist.setStyle("-fx-stroke: black");
        VBox vb = new VBox(mylist);
        vb.setPadding(new Insets(10));
        Image imagemylist = new Image("Assets\\Images\\predict.png");
        ImageView imageView = new ImageView(imagemylist);
        imageView.setPreserveRatio(true);
        imageView.setFitWidth(50);
        
        Label header = new Label("Selected Colleges");
        header.setFont(new Font(20));
        header.setPadding(new Insets(10));
        header.setStyle("-fx-stroke: black");
         
        VBox selectedBox = new VBox(10);
        selectedBox.setPadding(new Insets(10));

        List<CheckBox> checkBoxes = new java.util.ArrayList<>();
        List<nextpage> currentList = new java.util.ArrayList<>(selectedColleges);

        if (currentList.isEmpty()) {
            selectedBox.getChildren().add(new Label("No colleges selected."));
        } else {
            for (nextpage info : currentList) {
                CheckBox checkBox = new CheckBox(info.toString());
                //checkBox.setStyle("-fx-background-color:orange");
                checkBoxes.add(checkBox);
                selectedBox.getChildren().add(checkBox);
            }
        }
            Button deleteSelectedBtn = new Button("Delete Selected");
            deleteSelectedBtn.setStyle("-fx-background-color: red; -fx-text-fill: white;");
            deleteSelectedBtn.setOnAction(e -> {
                Alert confirm = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to delete selected colleges?", ButtonType.YES, ButtonType.NO);
                        confirm.setTitle("Getmycollege");
                        
                confirm.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.YES) {
                        for (int i = 0; i < checkBoxes.size(); i++) {
                            if (checkBoxes.get(i).isSelected()) {
                                selectedColleges.remove(currentList.get(i));
                                
                            }
                        }
                        // Refresh the UI
                        VBox newView = Createdlist(selectedColleges, back);
                        //newView.setStyle("-fx-background-color:orange");
                        listStage.getScene().setRoot(newView);
                    }
                });
            });

       Button backButton = new Button("Back");   
        backButton.setOnAction(e -> back.run());
       // backButton.setPadding(new Insets(10));
        Button downloadPdfButton = new Button("Download PDF");
        downloadPdfButton.setStyle("-fx-background-color: orange");
downloadPdfButton.setOnAction(e -> {
    try {
        createPdf(selectedColleges);
    } catch (Exception ex) {
        ex.printStackTrace();

    }
});
        VBox backBox = new VBox(backButton);
        backBox.setPadding(new Insets(10));
 HBox myllistBox = new HBox(10,backBox,imageView,vb,header);
       

    HBox Buttonde = new HBox(5,deleteSelectedBtn,downloadPdfButton);
    Buttonde.setPadding(new Insets(10));
   HBox topbarBox = new HBox(800,myllistBox,Buttonde);
    topbarBox.setStyle("-fx-border-color: black; -fx-border-width: 2;");

 HBox topRow = new HBox(20, logo, spacer1, searchBox, spacer2, profileImageView);
        topRow.setAlignment(Pos.CENTER_LEFT);
        topRow.setPadding(new Insets(10, 50, 10, 0));
        topRow.setBackground(new Background(new BackgroundFill(Color.web("#eb9d27ff"), CornerRadii.EMPTY, Insets.EMPTY)));

        VBox root = new VBox(15, selectedBox);
        VBox main = new VBox(topRow,topbarBox,root);
        main.setStyle("-fx-background-color: radial-gradient(center 50% 60%, radius 80%, white 0%, #e0e8ff 40%, #a0b8ff 80%, #7080ff 100% )");
        root.setPadding(new Insets(20));
        return main;
    }
    
    private void createPdf(List<nextpage> colleges) throws IOException {
    FileChooser fileChooser = new FileChooser();
    fileChooser.setInitialFileName("Selected_Colleges_List.pdf");
    fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("PDF Files", "*.pdf"));
    File file = fileChooser.showSaveDialog(listStage);

    if (file == null) return; 


    PdfWriter writer = new PdfWriter(new FileOutputStream(file));
    PdfDocument pdfDoc = new PdfDocument(writer);
    Document document = new Document(pdfDoc);

    document.add(new Paragraph("\t\t\t\t\t\t\t\t\t\tGetMyCollege\n\t\t\t\t\t\t\t\t\t\tSelected Colleges List\n\n"));

    if (colleges.isEmpty()) {
        document.add(new Paragraph("No colleges selected."));
    } else {
        for (nextpage info : colleges) {
            document.add(new Paragraph(info.toString()));
            document.add(new Paragraph("\n"));
        }
    }

    document.close();
    System.out.println("✅ PDF created: " + file.getAbsolutePath());
}

    
}
