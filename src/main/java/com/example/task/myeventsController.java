package com.example.task;

import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.stage.Stage;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class myeventsController {

    public Button homebutton;
    @FXML
    private Label welcomeText;

    @FXML
    private TableView<Events> EventsTable;

    @FXML
    private TableColumn<Events, String> tnameColumn;

    @FXML
    private TableColumn<Events, String> tidColumn;

    @FXML
    private TableColumn<Events, String> descriptionColumn;

    @FXML
    private TableColumn<Events, String> deadlineColumn;

    @FXML
    private TableColumn<Events, String> statusColumn;

    private static MongoClient mongoClient;

    static {
        try {
            // Initialize MongoDB connection once
            mongoClient = MongoClients.create("mongodb://localhost:27017");
        } catch (Exception e) {
            System.err.println("Error connecting to MongoDB: " + e.getMessage());
        }
    }

    public void initialize() {
        // Initialize the table columns
        tnameColumn.setCellValueFactory(new PropertyValueFactory<>("tname"));
        tidColumn.setCellValueFactory(new PropertyValueFactory<>("tid"));
        descriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        deadlineColumn.setCellValueFactory(new PropertyValueFactory<>("deadline"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        String username=UserContext.getInstance().getCurrentUsername();
        List<Events> userEvents = retrieveUserEvents(username);
        displayUserEvents(userEvents);
    }

    private List<Events> retrieveUserEvents(String username) {
        try {

            String userEventsCollectionName = username + "tasks";

            // Retrieve user events from MongoDB
            MongoDatabase database = mongoClient.getDatabase("task1");
            MongoCollection<Document> userEventsCollection = database.getCollection(userEventsCollectionName);

            List<Events> userEvents = new ArrayList<>();
            for (Document document : userEventsCollection.find()) {
                // Map MongoDB document to Events object
                Events event = new Events(
                        document.getString("tname"),
                        document.getString("tid"),
                        document.getString("description"),
                        document.getString("deadline"),
                        document.getString("status")
                );
                userEvents.add(event);
            }

            return userEvents;
        } catch (Exception e) {
            System.err.println("Error retrieving user events from MongoDB: " + e.getMessage());
            return new ArrayList<>(); // Return an empty list in case of an error
        }
    }

    private void displayUserEvents(List<Events> userEvents) {
        // Populate the TableView with the retrieved user events
        EventsTable.getItems().addAll(userEvents);
    }

    @FXML
    private void onmenuButtonClick() {
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
}
