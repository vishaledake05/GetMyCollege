package com.s1.view;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.*;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.controlsfx.control.SearchableComboBox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Dummy {

    Scene searchScene;
    Stage Searchstage;

    public void setSearchScene(Scene searchScene) {
        this.searchScene = searchScene;
    }

    public void setSearchstage(Stage searchstage) {
        Searchstage = searchstage;
    }

    public VBox createSearch(Runnable backRunnable) {

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

        Image profileImage = new Image("Assets/Images/asash.png");
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

        class College {
            private final SimpleStringProperty name, state, district, type;
            public College(String name, String state, String district, String type) {
                this.name = new SimpleStringProperty(name);
                this.state = new SimpleStringProperty(state);
                this.district = new SimpleStringProperty(district);
                this.type = new SimpleStringProperty(type);
            }
            public String getName() { return name.get(); }
            public String getState() { return state.get(); }
            public String getDistrict() { return district.get(); }
            public String getType() { return type.get(); }
        }

        var stateFilter = new SearchableComboBox<String>();
        var districtFilter = new SearchableComboBox<String>();
        var typeFilter = new SearchableComboBox<String>();
        var searchField = new TextField();
        searchField.setPromptText("Search college name...");

        Button backButton = new Button("← BACK");
        backButton.setStyle("-fx-background-color: #f26722; -fx-text-fill: white; -fx-font-weight: bold;");
        backButton.setOnAction(e -> backRunnable.run());

        var filters = new HBox(10,
                backButton,
                new Label("State:"), stateFilter,
                new Label("District:"), districtFilter,
                new Label("Type:"), typeFilter,
                new Label("Search:"), searchField
        );
        filters.setPadding(new Insets(10));
        filters.setAlignment(Pos.CENTER_LEFT);

        var table = new TableView<College>();
        var masterData = FXCollections.<College>observableArrayList();

        try (InputStream csv = Dummy.class.getResourceAsStream("/college-directory.csv")) {
            if (csv == null) {
                throw new IOException("college-directory.csv is missing from the application resources.");
            }

            try (var br = new BufferedReader(new InputStreamReader(csv, StandardCharsets.UTF_8))) {
                br.readLine();
                br.readLine();
                String header = br.readLine();
                if (header == null) {
                    throw new IOException("The college directory CSV does not contain a header row.");
                }

                String[] cols = parseCsvLine(header);
                int iState = -1, iName = -1, iDist = -1, iType = -1;
                for (int i = 0; i < cols.length; i++) {
                    String c = cols[i].replace("\uFEFF", "").trim();
                    if ("State".equalsIgnoreCase(c)) iState = i;
                    else if ("Name".equalsIgnoreCase(c)) iName = i;
                    else if ("District".equalsIgnoreCase(c)) iDist = i;
                    else if ("College Type".equalsIgnoreCase(c)) iType = i;
                }

                if (iState < 0 || iName < 0 || iDist < 0 || iType < 0) {
                    throw new IOException("The college directory CSV is missing a required column.");
                }

                int requiredColumns = Math.max(Math.max(iState, iName), Math.max(iDist, iType));
                String line;
                int rowNumber = 3;
                while ((line = br.readLine()) != null) {
                    rowNumber++;
                    if (line.isBlank()) {
                        continue;
                    }

                    String[] p = parseCsvLine(line);
                    if (p.length <= requiredColumns) {
                        throw new IOException("Invalid CSV row " + rowNumber + ": not enough columns.");
                    }

                    masterData.add(new College(
                            p[iName].trim(),
                            p[iState].trim(),
                            p[iDist].trim(),
                            p[iType].trim()
                    ));
                }
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Data load error:\n" + e.getMessage()).showAndWait();
        }

        stateFilter.getItems().setAll(masterData
                .stream()
                .map(College::getState)
                .distinct()
                .sorted()
                .toList()
        );
        stateFilter.getItems().add(0, "All States");
        stateFilter.setValue("All States");

        districtFilter.getItems().setAll(masterData
                .stream()
                .map(College::getDistrict)
                .distinct()
                .sorted()
                .toList()
        );
        districtFilter.getItems().add(0, "All Districts");
        districtFilter.setValue("All Districts");

        typeFilter.getItems().setAll(masterData
                .stream()
                .map(College::getType)
                .distinct()
                .sorted()
                .toList()
        );
        typeFilter.getItems().add(0, "All Types");
        typeFilter.setValue("All Types");

        FilteredList<College> filtered = new FilteredList<>(masterData, p -> true);
        Label resultCount = new Label();

        Runnable refresh = () -> {
            filtered.setPredicate(c ->
                    (stateFilter.getValue().equals("All States") ||
                            c.getState().equalsIgnoreCase(stateFilter.getValue())) &&
                            (districtFilter.getValue().equals("All Districts") ||
                        c.getDistrict().equalsIgnoreCase(districtFilter.getValue())) &&
                        (typeFilter.getValue().equals("All Types") ||
                                c.getType().equalsIgnoreCase(typeFilter.getValue())) &&
                        (searchField.getText().isBlank() ||
                                c.getName().toLowerCase().contains(searchField.getText().toLowerCase()))
            );
            resultCount.setText("Showing " + filtered.size() + " of " + masterData.size() + " colleges");
        };

        stateFilter.valueProperty().addListener((o, ov, nv) -> refresh.run());
        districtFilter.valueProperty().addListener((o, ov, nv) -> refresh.run());
        typeFilter.valueProperty().addListener((o, ov, nv) -> refresh.run());
        searchField.textProperty().addListener((o, ov, nv) -> refresh.run());
        refresh.run();

        var nameCol = new TableColumn<College, String>("College Name");
        nameCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getName()));
        nameCol.setPrefWidth(350);

        var distCol = new TableColumn<College, String>("District");
        distCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getDistrict()));
        distCol.setPrefWidth(150);

        var typeCol = new TableColumn<College, String>("Type");
        typeCol.setCellValueFactory(c -> new SimpleStringProperty(c.getValue().getType()));
        typeCol.setPrefWidth(200);

        table.getColumns().addAll(nameCol, distCol, typeCol);
        table.setItems(filtered);
        resultCount.setStyle("-fx-font-weight: bold;");
        table.setStyle("-fx-background-color: radial-gradient(center 50% 60%, radius 80%, white 0%, #e0e8ff 40%, #a0b8ff 80%, #7080ff 100%);");

        VBox root = new VBox();
        VBox.setVgrow(table, Priority.ALWAYS);
        root.getChildren().addAll(topRow, filters, resultCount, table);
        root.setStyle("-fx-background-color: radial-gradient(center 50% 60%, radius 80%, white 0%, #e0e8ff 40%, #a0b8ff 80%, #7080ff 100%);");

        return root;
    }

    private static String[] parseCsvLine(String line) throws IOException {
        List<String> fields = new ArrayList<>();
        StringBuilder field = new StringBuilder();
        boolean insideQuotes = false;

        for (int i = 0; i < line.length(); i++) {
            char character = line.charAt(i);
            if (character == '"') {
                if (insideQuotes && i + 1 < line.length() && line.charAt(i + 1) == '"') {
                    field.append('"');
                    i++;
                } else {
                    insideQuotes = !insideQuotes;
                }
            } else if (character == ',' && !insideQuotes) {
                fields.add(field.toString());
                field.setLength(0);
            } else {
                field.append(character);
            }
        }

        if (insideQuotes) {
            throw new IOException("The college directory CSV contains an unterminated quoted field.");
        }

        fields.add(field.toString());
        return fields.toArray(String[]::new);
    }
}
