package com.example;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class TaskDemo extends Application {

    @Override
    public void start(Stage primaryStage) {
        Label statusLabel = new Label("Ready");
        Button startButton = new Button("Start Background Task");

        startButton.setOnAction(e -> {
            statusLabel.setText("Processing...");
            startButton.setDisable(true); // Prevent double-clicking

            // 1. Create a Background Task
            Task<Void> task = new Task<>() {
                @Override
                protected Void call() throws Exception {
                    // THIS RUNS IN THE BACKGROUND (off the UI thread)
                    Thread.sleep(2500);

                    // CRITICAL RULE: never touch UI nodes directly from call().
                    // Platform.runLater() sends the update to the UI thread.
                    Platform.runLater(() -> statusLabel.setText("Halfway there..."));

                    Thread.sleep(2500);
                    return null;
                }
            };

            // 2. setOnSucceeded / setOnFailed are already delivered on the
            //    JavaFX Application Thread, so the UI can be updated directly here.
            task.setOnSucceeded(event -> {
                statusLabel.setText("Task Complete!");
                startButton.setDisable(false);
            });
            task.setOnFailed(event -> {
                statusLabel.setText("Task failed: " + task.getException().getMessage());
                startButton.setDisable(false);
            });

            // 3. Start the task on a new (daemon) thread
            Thread thread = new Thread(task);
            thread.setDaemon(true);
            thread.start();
        });

        VBox root = new VBox(20, startButton, statusLabel);
        root.setStyle("-fx-padding: 20; -fx-alignment: center;");

        Scene scene = new Scene(root, 400, 200);
        primaryStage.setTitle("Task Demo - The Fix");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
