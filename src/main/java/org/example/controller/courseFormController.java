package org.example.controller;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.example.bo.BOFactory;
import org.example.bo.custom.CourseBO;
import org.example.dto.CourseDTO;

import java.sql.SQLException;
import java.util.List;

public class courseFormController {

    @FXML
    private Button btnDelete;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnSearch;

    @FXML
    private Button btnUpdate;

    @FXML
    private TableColumn<CourseDTO, Integer> colId;

    @FXML
    private TableColumn<CourseDTO, String> colName;

    @FXML
    private TableColumn<CourseDTO, String> colDuration;

    @FXML
    private TableColumn<CourseDTO, Double> colFee;

    @FXML
    private TableView<CourseDTO> tblCourse;

    @FXML
    private TextField txtDuration;

    @FXML
    private TextField txtFee;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    private CourseBO courseBO = (CourseBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.Course);

    @FXML
    void initialize() {
        configureTableColumns();
        loadAllCourses(); // Load all courses when the controller is initialized
        int nextId = courseBO.getNextCourseId();
        txtId.setText(String.valueOf(nextId));
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        int id = Integer.parseInt(txtId.getText());
        if (courseBO.deleteCourse(id)) {
            showAlert("Success", "Course deleted successfully!");
            clearFields();
            loadAllCourses(); // Refresh the table view
        } else {
            showAlert("Error", "Failed to delete course!");
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        CourseDTO courseDTO = new CourseDTO(
                Integer.parseInt(txtId.getText()),
                txtName.getText(),
                txtDuration.getText(),
                Double.parseDouble(txtFee.getText())
        );

        if (courseBO.saveCourse(courseDTO)) {
            showAlert("Success", "Course saved successfully!");
            clearFields();
            loadAllCourses(); // Refresh the table view
        } else {
            showAlert("Error", "Failed to save course!");
        }
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        int id = Integer.parseInt(txtId.getText());
        CourseDTO courseDTO = courseBO.findCourseById(id);

        if (courseDTO != null) {
            txtName.setText(courseDTO.getName());
            txtDuration.setText(courseDTO.getDuration());
            txtFee.setText(String.valueOf(courseDTO.getFee()));
        } else {
            showAlert("Error", "Course not found!");
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        CourseDTO courseDTO = new CourseDTO(
                Integer.parseInt(txtId.getText()),
                txtName.getText(),
                txtDuration.getText(),
                Double.parseDouble(txtFee.getText())
        );

        if (courseBO.updateCourse(courseDTO)) {
            showAlert("Success", "Course updated successfully!");
            clearFields();
            loadAllCourses(); // Refresh the table view
        } else {
            showAlert("Error", "Failed to update course!");
        }
    }

    private void loadAllCourses() {
        List<CourseDTO> courseList = courseBO.getAllCoursers();
        ObservableList<CourseDTO> observableList = FXCollections.observableArrayList(courseList);
        tblCourse.setItems(observableList);
    }

    private void clearFields() {
        txtId.clear();
        txtName.clear();
        txtDuration.clear();
        txtFee.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void configureTableColumns() {
        colId.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getcId()).asObject());
        colName.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getName()));
        colDuration.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDuration()));
        colFee.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getFee()).asObject());
    }
}

