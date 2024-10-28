package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class dashboardFormController {

    public AnchorPane rootNode;
    @FXML
    private Button btnCourse;

    @FXML
    private Button btnStudent;

    @FXML
    private Button btnUser;

    @FXML
    private Label lblCourses;

    @FXML
    private Label lblStudentCount;

    @FXML
    void btnCourseOnAction(ActionEvent event) throws IOException {
        AnchorPane node = FXMLLoader.load(this.getClass().getResource("/courseForm.fxml"));
        Scene scene = new Scene(node);
        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Course Form");
    }

    @FXML
    void btnStudentOnAction(ActionEvent event) throws IOException {
        AnchorPane node = FXMLLoader.load(this.getClass().getResource("/studentForm.fxml"));
        Scene scene = new Scene(node);
        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Course Form");
    }

    @FXML
    void btnUserOnAction(ActionEvent event) {

    }

}
