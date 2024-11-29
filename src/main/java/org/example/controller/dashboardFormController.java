package org.example.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.example.bo.BOFactory;
import org.example.bo.custom.CourseBO;
import org.example.bo.custom.StudentBO;

import java.io.IOException;
import java.time.LocalDate;

public class dashboardFormController {

    public AnchorPane rootNode;
    public Button btnDetails;
    public DatePicker datePicker;
    public Label lblRegisteredStudents;
    public Label lblAvailableCourses;

    private StudentBO studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.Student);
    private CourseBO courseBO = (CourseBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.Course);

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
    public void initialize() {
        datePicker.setValue(LocalDate.now()); // Set the current date
        fillRegisteredStudents();
        fillCourseCount();
    }

    @FXML
    void btnCourseOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/courseForm.fxml"));
        AnchorPane detailsPane = loader.load();
        Stage detailsStage = new Stage();
        detailsStage.setTitle("Course Form");
        detailsStage.setScene(new Scene(detailsPane));
        detailsStage.show();
    }

    @FXML
    void btnStudentOnAction(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/studentForm.fxml"));
        AnchorPane detailsPane = loader.load();
        Stage detailsStage = new Stage();
        detailsStage.setTitle("Student Form");
        detailsStage.setScene(new Scene(detailsPane));
        detailsStage.show();
    }

    @FXML
    void btnUserOnAction(ActionEvent event) throws IOException {
        AnchorPane node = FXMLLoader.load(this.getClass().getResource("/userform.fxml"));
        Scene scene = new Scene(node);
        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("User Form");
    }

    @FXML
    void btnDetailsOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/courseStudentDetailsForm.fxml"));
        AnchorPane detailsPane = loader.load();
        Stage detailsStage = new Stage();
        detailsStage.setTitle("Course Student Details");
        detailsStage.setScene(new Scene(detailsPane));
        detailsStage.show();
    }

    @FXML
    void datePickerOnAction(ActionEvent actionEvent) {
        LocalDate selectedDate = datePicker.getValue();
        System.out.println("Selected date: " + selectedDate);
    }

    void fillRegisteredStudents(){
        int count = studentBO.getStudentCount();
        lblRegisteredStudents.setText(String.valueOf(count));
    }
    void fillCourseCount(){
        int count = courseBO.getCourseCount();
        lblAvailableCourses.setText(String.valueOf(count));
    }
}
