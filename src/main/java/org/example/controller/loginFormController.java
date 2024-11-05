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
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;

public class loginFormController {

    @FXML
    public AnchorPane rootNode;

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnTogglePassword;  // Show/Hide password button

    @FXML
    private PasswordField txtPassword;

    @FXML
    private TextField txtPasswordVisible;  // A new text field to show password

    @FXML
    private TextField txtUserName;

    private userBO UserBO = (userBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.User);

    @FXML
    void btnLoginOnAction(ActionEvent event) {
        String username = txtUserName.getText();
        String password = getPassword();  //visible one

        try {
            userDTO user = UserBO.findUserByUsername(username);
            if (user != null && verifyPassword(password, user.getPassword())) {
                showAlert("Login Successful", "Welcome, " + username + "!");
                userDTO position = UserBO.findPositionByUserName(username);
                if (position != null) {
                    switch (position.getPosition().toLowerCase()) {
                        case "admin":
                            adminGoes();
                            break;
                        case "coordinator":
                            coordinatorGoes();
                            break;
                        case "teacher":
                            teacherGoes();
                            break;
                        default:
                            showAlert("Access Denied", "You do not have permission to access the dashboard.");
                    }
                } else {
                    showAlert("Login Failed", "User does not exist.");
                }
            } else {
                showAlert("Login Failed", "Incorrect password or username.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "An error occurred during login.");
        }
    }

    void adminGoes() throws IOException {
        loadScene("/dashboardForm.fxml", "Dashboard");
    }

    void coordinatorGoes() throws IOException {
        loadScene("/studentForm.fxml", "Course Student Details");
    }

    void teacherGoes() throws IOException {
        loadScene("/courseStudentDetailsForm.fxml", "Course Form");
    }

    private void loadScene(String fxmlPath, String title) throws IOException {
        AnchorPane node = FXMLLoader.load(this.getClass().getResource(fxmlPath));
        Scene scene = new Scene(node);
        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle(title);
    }

    private boolean verifyPassword(String plainPassword, String hashedPassword) {
        if (hashedPassword == null || !hashedPassword.startsWith("$2a$") && !hashedPassword.startsWith("$2b$") && !hashedPassword.startsWith("$2y$")) {
            throw new IllegalArgumentException("Invalid hashed password format");
        }
        return BCrypt.checkpw(plainPassword, hashedPassword);//in built method
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Toggle password visibility
    @FXML
    private void togglePasswordVisibility(ActionEvent event) {
        if (txtPassword.isVisible()) {
            txtPassword.setVisible(false);
            txtPasswordVisible.setText(txtPassword.getText());
            txtPasswordVisible.setVisible(true);
            btnTogglePassword.setText("Hide");
        } else {
            txtPasswordVisible.setVisible(false);
            txtPassword.setText(txtPasswordVisible.getText());
            txtPassword.setVisible(true);
            btnTogglePassword.setText("Show");
        }
    }

    private String getPassword() {
        if (txtPassword.isVisible()) {
            return txtPassword.getText();
        } else {
            return txtPasswordVisible.getText();
        }
    }
}
