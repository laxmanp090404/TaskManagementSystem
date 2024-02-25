package com.example.task;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class regController {
    public Button loginButton;
    public Label welcomeText;
    public TextField tasknameTextField;
    public TextField taskidtypeTextField;
    public TextField descriptionTextField;
    public TextField deadlineTextField;
    public TextField statusTextField;
    public Button confirmButton;

    public void onmenuButtonClick(ActionEvent actionEvent) {
        try {
            // Load the login.fxml file
            FXMLLoader loader = new FXMLLoader(getClass().getResource("menu.fxml"));
            Parent root = loader.load();

            // Create a new Scene
            Scene loginScene = new Scene(root);

            // Get the current stage (assuming your signup.fxml is in the same stage)
            Stage stage = (Stage) welcomeText.getScene().getWindow();

            // Set the new scene on the stage
            stage.setScene(loginScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void onconfirmButtonclick(ActionEvent actionEvent) {
        try {
            String tname = tasknameTextField.getText();
            String  tid = taskidtypeTextField.getText();
            String description = descriptionTextField.getText();
            String deadline = deadlineTextField.getText();
            String status=statusTextField.getText();

            // Check if any of the fields are empty
            if (tname.isEmpty() || tid.isEmpty() || description.isEmpty() || deadline.isEmpty() || status.isEmpty()) {
                System.out.println("Please fill in all fields.");
                return;
            }
            TaskApp.addevent(tname,tid,description,deadline,status);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
