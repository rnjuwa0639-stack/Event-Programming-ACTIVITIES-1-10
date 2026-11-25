package com.university;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MainController {

    @FXML private StackPane centerPane;

    // The dashboard label is bound to this property (data binding, LO4)
    private final StringProperty dashboardText =
            new SimpleStringProperty("Welcome to the University Dashboard!\nLoading...");

    private ScheduledExecutorService scheduler;

    @FXML
    public void initialize() {
        showDashboard();
        startAutoRefresh();
    }

    @FXML
    private void showDashboard() {
        Label dashboard = new Label();
        dashboard.setStyle("-fx-font-size: 18px;");
        dashboard.textProperty().bind(dashboardText);
        centerPane.getChildren().setAll(dashboard);
    }

    @FXML
    private void showStudents() {
        try {
            Parent studentView = FXMLLoader.load(getClass().getResource("StudentView.fxml"));
            centerPane.getChildren().setAll(studentView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /** LO3: a ScheduledExecutorService refreshes the dashboard every 5 seconds. */
    private void startAutoRefresh() {
        scheduler = Executors.newScheduledThreadPool(1, r -> {
            Thread t = new Thread(r, "dashboard-refresh");
            t.setDaemon(true);
            return t;
        });

        scheduler.scheduleAtFixedRate(() -> {
            try {
                System.out.println("System Heartbeat: Checking for updates at " + new java.util.Date());
                String text = buildDashboardText();
                // UI changes must happen on the JavaFX Application Thread
                Platform.runLater(() -> dashboardText.set(text));
            } catch (Throwable t) {
                t.printStackTrace(); // never let an exception cancel the schedule
            }
        }, 0, 5, TimeUnit.SECONDS);
    }

    private String buildDashboardText() {
        String header = "Welcome to the University Dashboard!\n";
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT COUNT(*) FROM students");
             ResultSet rs = stmt.executeQuery()) {
            rs.next();
            return header + "Total students: " + rs.getInt(1)
                    + "\nLast refreshed: " + LocalTime.now().withNano(0);
        } catch (Exception e) {
            return header + "Database unavailable: " + e.getMessage();
        }
    }

    /** Called by App.stop() so the scheduler is shut down cleanly. */
    public void shutdown() {
        if (scheduler != null) {
            scheduler.shutdownNow();
        }
    }
}
