package org.example.controller;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.bo.BOFactory;
import org.example.bo.custom.userBO;
import org.example.dto.userDTO;
import org.example.util.Regex;
import org.mindrot.jbcrypt.BCrypt;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class userFormController {

    public ComboBox<String> cmbPosition;
    public Button btnBack;
    public AnchorPane rootNode;
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
                btnClearOnAction(event);
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
        cmbPosition.getItems().addAll("Admin", "Coordinator", "Teacher");

        colID.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getId()).asObject());
        colUserName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getUsername()));
        colPassword.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPassword()));
        colPosition.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPosition()));

        loadUsers();
    }

    private void loadUsers() {
        try {
            List<userDTO> userList = UserBO.getAllUsers();
            tblUser.getItems().setAll(userList);
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

        String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        userDTO UserDTO = new userDTO(0, username, hashedPassword, position);

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

            String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

            userDTO UserDTO = new userDTO(id, username, hashedPassword, position);

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

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane node = FXMLLoader.load(this.getClass().getResource("/dashboardForm.fxml"));
        Scene scene = new Scene(node);
        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Course Form");
    }

    public boolean isValid(){
        if (!Regex.setTextColor(org.example.util.TextField.ID, txtId)) return false;
        if (!Regex.setTextColor(org.example.util.TextField.NAME, txtUserName)) return false;
        return true;
    }

    public void txtNameOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(org.example.util.TextField.NAME, txtUserName);
    }

    public void txtIdOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(org.example.util.TextField.ID, txtId);
    }
}
