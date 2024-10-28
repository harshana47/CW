package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class courseStudentDetailsFormController {

    @FXML
    private Button btnSearch;

    @FXML
    private TableColumn<?, ?> colCourseFee;

    @FXML
    private TableColumn<?, ?> colCourseId;

    @FXML
    private TableColumn<?, ?> colDuration;

    @FXML
    private TableColumn<?, ?> colStudentId;

    @FXML
    private TableView<?> tblRegister;

    @FXML
    private TextField txtStudentId;

    @FXML
    void btnSearchOnAction(ActionEvent event) {

    }

}
