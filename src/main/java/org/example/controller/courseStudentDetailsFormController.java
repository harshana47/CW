package org.example.controller;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import org.example.bo.BOFactory;
import org.example.bo.custom.courseStudentDetailsBO;
import org.example.dto.courseStudentDetailsDTO;
import org.example.util.Regex;

import java.sql.SQLException;
import java.util.List;

public class courseStudentDetailsFormController {

    @FXML
    private Button btnSearch;

    @FXML
    private TableColumn<courseStudentDetailsDTO, Integer> colCourseId;

    @FXML
    private TableColumn<courseStudentDetailsDTO, Integer> colStudentId;

    @FXML
    private TableColumn<courseStudentDetailsDTO, Double> colCourseFee;

    @FXML
    private TableColumn<courseStudentDetailsDTO, String> colDuration;

    @FXML
    private TableView<courseStudentDetailsDTO> tblRegister;

    @FXML
    private TextField txtCourseId;

    private courseStudentDetailsBO courseStudentDetailsBOs = (org.example.bo.custom.courseStudentDetailsBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.courseStudentDetails);

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String courseIdText = txtCourseId.getText().trim();

        if (courseIdText.isEmpty()) {
            System.out.println("Please enter a Course ID.");
            return;
        }

        try {
            int courseId = Integer.parseInt(courseIdText);

            List<courseStudentDetailsDTO> courseStudentDetailsDTOList = courseStudentDetailsBOs.getByCourseId(courseId);

            if (courseStudentDetailsDTOList.isEmpty()) {
                System.out.println("No details found for Course ID: " + courseId);
            } else {
                System.out.println("Course details loaded for Course ID: " + courseId);
            }

            ObservableList<courseStudentDetailsDTO> courseStudentDetailsDTOS = FXCollections.observableArrayList(courseStudentDetailsDTOList);
            tblRegister.setItems(courseStudentDetailsDTOS);

        } catch (NumberFormatException e) {
            System.out.println("Invalid Course Id. Please enter a number .");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("error occurred while searching for course details.");
        }
    }



    @FXML
    void initialize() {
        configureTableColumns();
        try {
            loadData();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private void loadData() throws Exception {
        List<courseStudentDetailsDTO> courseStudentDetailsDTOList = courseStudentDetailsBOs.getAll();
        System.out.println("Course details loaded: " + courseStudentDetailsDTOList); // Debugging line
        ObservableList<courseStudentDetailsDTO> courseStudentDetailsDTOS = FXCollections.observableArrayList(courseStudentDetailsDTOList);
        tblRegister.setItems(courseStudentDetailsDTOS);
    }

    private void configureTableColumns() {
        colCourseId.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getcId()).asObject());
        colStudentId.setCellValueFactory(cellData -> new SimpleIntegerProperty(cellData.getValue().getsId()).asObject());
        colCourseFee.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getFee()).asObject());
        colDuration.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDuration()));
    }

    public boolean isValid(){
        if (!Regex.setTextColor(org.example.util.TextField.ID, txtCourseId)) return false;
        return true;
    }

    public void txtIdOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(org.example.util.TextField.ID, txtCourseId);
    }
}
