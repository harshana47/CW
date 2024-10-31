package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.bo.BOFactory;
import org.example.bo.custom.userBO;
import org.example.dto.userDTO;

import java.sql.SQLException;

public class userFormController {

    public ComboBox cmbPosition;
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
    private TableColumn<?, ?> colID;

    @FXML
    private TableColumn<?, ?> colPassword;

    @FXML
    private TableColumn<?, ?> colPosition;

    @FXML
    private TableColumn<?, ?> colUserName;

    @FXML
    private TableView<?> tblUser;

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

    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }
    @FXML
    void initialize() {
        // Populate the ComboBox with some values
        cmbPosition.getItems().addAll("Admin", "Coordinator", "Teacher");

    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String password = txtPassword.getText();
        String position = (String) cmbPosition.getValue();
        String username = txtUserName.getText();

        userDTO UserDTO = new userDTO(0,username ,password, position);

        if (UserBO.save(UserDTO)) {
            showAlert("Success","User saved successfully");
        }else {
            showAlert("Error","Failed to save user");
        }

    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }
    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

}
