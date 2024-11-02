package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.bo.BOFactory;
import org.example.bo.custom.userBO;
import org.example.dto.userDTO;

import java.io.IOException;

public class loginFormController {

    public AnchorPane rootNode;
    @FXML
    private Button btnLogin;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtUserName;

    private userBO UserBO = (userBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.User);

    @FXML
    void btnLoginOnAction(ActionEvent event) {
        String username = txtUserName.getText();
        String password = txtPassword.getText();

        try {
            userDTO user = UserBO.findUserByUsername(username);
            if (user != null && user.getPassword().equals(password)) {
                showAlert("Login Successful", "Welcome, " + username + "!");

                userDTO position = UserBO.findPositionByUserName(username);
                if (position != null) {
                    if ("Admin".equalsIgnoreCase(position.getPosition())) {
                        adminGoes();
                    } else if ("Coordinator".equalsIgnoreCase(position.getPosition())) {
                        coordinatorGoes();
                    } else if ("Teacher".equalsIgnoreCase(position.getPosition())){
                        teacherGoes();
                } else {
                        showAlert("Access Denied", "You do not have permission to access the dashboard.");
                    }
                } else {
                    showAlert("Login Failed", "User does not exist.");
                }
            }
            } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "An error occurred during login.");
        }
    }

    void adminGoes() throws IOException {
        AnchorPane node = FXMLLoader.load(this.getClass().getResource("/dashboardForm.fxml"));
        Scene scene = new Scene(node);
        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Dashboard");
    }

    void coordinatorGoes() throws IOException {
        AnchorPane node = FXMLLoader.load(this.getClass().getResource("/studentForm.fxml"));
        Scene scene = new Scene(node);
        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Course Student Details");
    }

    void teacherGoes() throws IOException {
        AnchorPane node = FXMLLoader.load(this.getClass().getResource("/courseStudentDetailsForm.fxml"));
        Scene scene = new Scene(node);
        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Course Form");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}