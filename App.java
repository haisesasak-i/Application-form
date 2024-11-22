package com.example.demo5;

import java.io.File;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class App extends Application {

    private ArrayList<Person> personList = new ArrayList<>();
    private ImageView imageView = new ImageView(); // To display the selected image

    @Override
    public void start(Stage primaryStage) {

        // Banner setup
        Label bannerLabel = new Label("Application Form");
        bannerLabel.setStyle("-fx-font-size: 30px; -fx-font-weight: bold; -fx-text-fill: white;");
        HBox bannerBox = new HBox(bannerLabel);
        bannerBox.setAlignment(Pos.CENTER);
        bannerBox.setPadding(new Insets(20));
        bannerBox.setStyle("-fx-background-color: #282828;");

        // Form fields
        Label nameLabel = createStyledLabel("Name:");
        TextField nameField = new TextField();

        Label fatherNameLabel = createStyledLabel("Father Name:");
        TextField fatherNameField = new TextField();

        Label cnicLabel = createStyledLabel("CNIC:");
        TextField cnicField = new TextField();

        Label dateLabel = createStyledLabel("Date (Picker):");
        DatePicker datePicker = new DatePicker();

        Label genderLabel = createStyledLabel("Gender:");
        ComboBox<String> genderComboBox = new ComboBox<>();
        genderComboBox.getItems().addAll("Male", "Female", "Other");

        Label cityLabel = createStyledLabel("City:");
        TextField cityField = new TextField();

        // Image upload button
        Label imageLabel = createStyledLabel("Upload Image:");
        Button imageButton = new Button("Choose File");
        imageButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-font-weight: bold;");

        imageButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.png"));
            File selectedFile = fileChooser.showOpenDialog(primaryStage);

            if (selectedFile != null) {
                Image image = new Image(selectedFile.toURI().toString());
                if (!image.isError()) {
                    imageView.setImage(image);
                    imageView.setFitHeight(300);  // Set a smaller height
                    imageView.setFitWidth(200);  // Set a smaller width
                    imageView.setPreserveRatio(true);  // Maintain aspect ratio
                } else {
                    System.out.println("Error loading image.");
                }
            } else {
                System.out.println("No file selected.");
            }
        });

        // Submit button
        Button saveButton = new Button("Submit");
        saveButton.setStyle("-fx-background-color: #2196F3; -fx-text-fill: white; -fx-font-weight: bold;");
        saveButton.setOnAction(e -> {
            String name = nameField.getText();
            String fatherName = fatherNameField.getText();
            String cnic = cnicField.getText();
            String date = (datePicker.getValue() != null) ? datePicker.getValue().toString() : "";
            String gender = genderComboBox.getValue();
            String city = cityField.getText();

            Person person = new Person(name, fatherName, cnic, date, gender, city);
            personList.add(person);

            // Clear form
            nameField.clear();
            fatherNameField.clear();
            cnicField.clear();
            datePicker.setValue(null);
            genderComboBox.setValue(null);
            cityField.clear();
            imageView.setImage(null);

            System.out.println("Person added: " + person);
        });

        // Layout for the form fields
        GridPane formLayout = new GridPane();
        formLayout.setHgap(15);
        formLayout.setVgap(20);
        formLayout.setPadding(new Insets(20));
        formLayout.setAlignment(Pos.CENTER);
        formLayout.setStyle("-fx-background-color: #121212; -fx-border-color: #4CAF50; -fx-border-width: 2px;");

        formLayout.add(nameLabel, 0, 0);
        formLayout.add(nameField, 1, 0);
        formLayout.add(imageView, 2, 0); // Image shown next to the name field

        formLayout.add(fatherNameLabel, 0, 1);
        formLayout.add(fatherNameField, 1, 1);

        formLayout.add(cnicLabel, 0, 2);
        formLayout.add(cnicField, 1, 2);

        formLayout.add(dateLabel, 0, 3);
        formLayout.add(datePicker, 1, 3);

        formLayout.add(genderLabel, 0, 4);
        formLayout.add(genderComboBox, 1, 4);

        formLayout.add(cityLabel, 0, 5);
        formLayout.add(cityField, 1, 5);

        formLayout.add(imageLabel, 0, 6);
        formLayout.add(imageButton, 1, 6);

        formLayout.add(saveButton, 1, 7);

        // Main layout
        VBox mainLayout = new VBox(20);
        mainLayout.getChildren().addAll(bannerBox, formLayout);
        mainLayout.setPadding(new Insets(20));
        mainLayout.setStyle("-fx-background-color: black;");

        Scene scene = new Scene(mainLayout, 700, 700);
        primaryStage.setTitle("Application Form");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Label createStyledLabel(String text) {
        Label label = new Label(text);
        label.setStyle("-fx-text-fill: #4CAF50; -fx-font-size: 14px; -fx-font-weight: bold;");
        return label;
    }

    public static void main(String[] args) {
        launch();
    }
}


