package com.university;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class StudentController {

    @FXML private TextField regField;
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TableView<Student> studentTable;
    @FXML private TableColumn<Student, String> colReg;
    @FXML private TableColumn<Student, String> colFirst;
    @FXML private TableColumn<Student, String> colLast;
    @FXML private Label statusLabel;
    @FXML private Button saveButton;

    // The data the TableView displays
    private final ObservableList<Student> studentList = FXCollections.observableArrayList();

    // Called automatically when the FXML loads
    @FXML
    public void initialize() {
        // 1. Connect the TableColumns to the Student properties
        colReg.setCellValueFactory(cellData -> cellData.getValue().regNumberProperty());
        colFirst.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        colLast.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());

        // 2. Tell the TableView to use our list
        studentTable.setItems(studentList);

        // 3. Disable Save while any field is empty (binding, no manual checks)
        saveButton.disableProperty().bind(
                Bindings.isEmpty(regField.textProperty())
                        .or(Bindings.isEmpty(firstNameField.textProperty()))
                        .or(Bindings.isEmpty(lastNameField.textProperty()))
        );

        // 4. Load the initial data
        loadStudents();
    }

    @FXML
    private void handleSaveStudent() {
        String reg = regField.getText().trim();
        String fName = firstNameField.getText().trim();
        String lName = lastNameField.getText().trim();

        // Background task so the UI never freezes (Producer-Consumer)
        Task<Void> saveTask = new Task<>() {
            @Override
            protected Void call() throws Exception {
                String sql = "INSERT INTO students (registration_number, first_name, last_name, email) "
                        + "VALUES (?, ?, ?, ?)";
                try (Connection conn = DatabaseUtil.getConnection();
                     PreparedStatement stmt = conn.prepareStatement(sql)) {
                    stmt.setString(1, reg);
                    stmt.setString(2, fName);
                    stmt.setString(3, lName);
                    stmt.setString(4, reg + "@university.edu"); // generated email
                    stmt.executeUpdate();
                }
                return null;
            }
        };

        // These callbacks run on the JavaFX Application Thread
        saveTask.setOnSucceeded(e -> {
            statusLabel.setText("Student saved successfully!");
            regField.clear();
            firstNameField.clear();
            lastNameField.clear();
            loadStudents(); // refresh the table
        });
        saveTask.setOnFailed(e ->
                statusLabel.setText("Save failed: " + saveTask.getException().getMessage()));

        startDaemon(saveTask);
    }

    private void loadStudents() {
        LoadStudentsTask task = new LoadStudentsTask();

        task.setOnSucceeded(e -> {
            studentList.setAll(task.getValue());
            statusLabel.setText("Loaded " + studentList.size() + " students from database.");
        });
        task.setOnFailed(e ->
                statusLabel.setText("Could not load students: " + task.getException().getMessage()));

        startDaemon(task);
    }

    private void startDaemon(Task<?> task) {
        Thread thread = new Thread(task);
        thread.setDaemon(true); // does not keep the JVM alive after the window closes
        thread.start();
    }
}
