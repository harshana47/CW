package org.example.controller;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.bo.BOFactory;
import org.example.bo.custom.userBO;
import org.example.dto.userDTO;

import java.sql.SQLException;
import java.util.List;

public class userFormController {

    public ComboBox<String> cmbPosition;
    @FXML
    private Button btnClear;

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<userDTO, Integer> colID;

    @FXML
    private TableColumn<userDTO, String> colPassword;

    @FXML
    private TableColumn<userDTO, String> colPosition;

    @FXML
    private TableColumn<userDTO, String> colUserName;

    @FXML
    private TableView<userDTO> tblUser;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtPosition;

    @FXML
    private TextField txtUserName;

    @FXML
    private TextField txtPassword;

    private userBO UserBO = (userBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.User);

    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtId.clear();
        txtUserName.clear();
        txtPassword.clear();
        cmbPosition.setValue(null);
        loadUsers();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        try {
            int id = Integer.parseInt(txtId.getText());
            if (UserBO.delete(id)) {
                showAlert("Success", "User deleted successfully");
                btnClearOnAction(event); // Clear fields after deletion
                loadUsers();
            } else {
                showAlert("Error", "Failed to delete user");
            }
        } catch (NumberFormatException e) {
            showAlert("Error", "Invalid ID format");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to delete user: " + e.getMessage());
        }
    }

    @FXML
    void initialize() {
        // Populate the ComboBox with some values
        cmbPosition.getItems().addAll("Admin", "Coordinator", "Teacher");

        // Set up the table columns
        colID.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        colUserName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUsername()));
        colPassword.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPassword()));
        colPosition.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPosition()));

        // Load users into the table
        loadUsers();
    }

    private void loadUsers() {
        try {
            // Fetch all users from the business layer
            List<userDTO> userList = UserBO.getAllUsers(); // You need to implement this method
            tblUser.getItems().setAll(userList); // Set the items in the TableView
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load user data: " + e.getMessage());
        }
    }


    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String password = txtPassword.getText();
        String position = cmbPosition.getValue();
        String username = txtUserName.getText();

        userDTO UserDTO = new userDTO(0, username, password, position);

        if (UserBO.save(UserDTO)) {
            showAlert("Success", "User saved successfully");
            loadUsers();
        } else {
            showAlert("Error", "Failed to save user");
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        try {
            int id = Integer.parseInt(txtId.getText());
            userDTO user = UserBO.findUserById(id);
            if (user != null) {
                txtUserName.setText(user.getUsername());
                txtPassword.setText(user.getPassword());
                cmbPosition.setValue(user.getPosition());
            } else {
                showAlert("Error", "User not found");
            }
        } catch (NumberFormatException e) {
            showAlert("Error", "Invalid ID format");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "Error occurred: " + e.getMessage());
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        try {
            int id = Integer.parseInt(txtId.getText());
            String username = txtUserName.getText();
            String password = txtPassword.getText();
            String position = cmbPosition.getValue();

            userDTO UserDTO = new userDTO(id, username, password, position);

            if (UserBO.update(UserDTO)) {
                showAlert("Success", "User updated successfully");
                loadUsers();
            } else {
                showAlert("Error", "Failed to update user");
            }
        } catch (NumberFormatException e) {
            showAlert("Error", "Invalid ID format");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to update user: " + e.getMessage());
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
