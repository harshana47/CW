package org.example.controller;

import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.ObservableList;
import org.example.bo.BOFactory;
import org.example.bo.custom.CourseBO;
import org.example.bo.custom.StudentBO;
import org.example.bo.custom.courseStudentDetailsBO;
import org.example.dto.CourseDTO;
import org.example.dto.StudentDTO;
import org.example.dto.courseStudentDetailsDTO;
import org.example.entity.Course;
import org.example.entity.Student;
import org.example.entity.courseStudentDetails;
import org.example.dao.custom.courseStudentDetailsDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class studentFormController {

    @FXML
    private Button btnDelete, btnSave, btnSearch, btnUpdate;

    @FXML
    private TableColumn<StudentDTO, String> colContact, colDate, colName;
    @FXML
    private TableColumn<StudentDTO, Integer> colId;
    @FXML
    private TableColumn<StudentDTO, Double> colPayment;

    @FXML
    private Label lblSelection;
    @FXML
    private ListView<CourseDTO> lsCourses; // Corrected naming
    @FXML
    private TableView<StudentDTO> tblStudents; // Corrected naming

    @FXML
    private TextField txtContact, txtDate, txtId, txtName, txtPayment;

    private StudentBO studentBO;
    private CourseBO courseBO;

    public studentFormController() {
        // Initialize the BOs using the factory
        this.studentBO = (StudentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.Student);
        this.courseBO = (CourseBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.Course);
    }

    @FXML
    void initialize() {
        System.out.println("Initializing studentFormController");
        System.out.println("ListView lsCourses: " + lsCourses);
        loadCourses();
        loadStudents();
        configureCourseListView();
    }

    private void configureCourseListView() {
        lsCourses.setCellFactory(param -> new ListCell<CourseDTO>() {
            @Override
            protected void updateItem(CourseDTO course, boolean empty) {
                super.updateItem(course, empty);
                setText(empty || course == null ? null : course.getName());
            }
        });

        lsCourses.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        lsCourses.getSelectionModel().getSelectedItems().addListener((ListChangeListener<CourseDTO>) change -> updateSelectedCourses());
    }

    private void loadCourses() {
        List<CourseDTO> courses = courseBO.getAllCoursers();
        lsCourses.getItems().addAll(courses);
    }

    private void loadStudents() {
        List<StudentDTO> students = studentBO.getAllStudents();
        tblStudents.getItems().setAll(students);
    }

    private void updateSelectedCourses() {
        ObservableList<CourseDTO> selectedCourses = lsCourses.getSelectionModel().getSelectedItems();
        List<String> courseNames = new ArrayList<>();
        for (CourseDTO course : selectedCourses) {
            courseNames.add(course.getName());
        }
        lblSelection.setText(String.join(", ", courseNames));
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) {
        try {
            int sId = Integer.parseInt(txtId.getText());
            if (studentBO.deleteStudent(sId)) {
                loadStudents();
                clearFields();
                showAlert("Success", "Student deleted successfully.");
            } else {
                showAlert("Error", "Failed to delete student.");
            }
        } catch (NumberFormatException e) {
            showAlert("Error", "Invalid Student ID.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String name = txtName.getText();
        String contact = txtContact.getText();
        double payment;
        String registeredDate = txtDate.getText(); // Ensure proper date format

        try {
            payment = Double.parseDouble(txtPayment.getText());
        } catch (NumberFormatException e) {
            showAlert("Error", "Invalid payment amount.");
            return;
        }

        StudentDTO studentDTO = new StudentDTO(0, name, contact, payment, registeredDate);
        List<courseStudentDetailsDTO> courseStudentDetailsDTOs = new ArrayList<>();

        // Gather selected course details
        ObservableList<CourseDTO> selectedCourses = lsCourses.getSelectionModel().getSelectedItems();
        for (CourseDTO courseDTO : selectedCourses) {
            courseStudentDetailsDTO courseStudentDetailDTO = new courseStudentDetailsDTO();
            courseStudentDetailDTO.setCourse(convertToEntity(courseDTO)); // Assuming you have a method to convert CourseDTO to Course
            courseStudentDetailDTO.setFee(courseDTO.getFee());
            courseStudentDetailDTO.setDuration(courseDTO.getDuration());
            courseStudentDetailsDTOs.add(courseStudentDetailDTO);
        }

        try {
            // Attempt to save student and course details in a transaction
            if (studentBO.SaveStudent(studentDTO, courseStudentDetailsDTOs)) {
                loadStudents(); // Refresh the table
                clearFields(); // Clear input fields after saving
                showAlert("Success", "Student saved successfully.");
            }
        } catch (SQLException | ClassNotFoundException e) {
            showAlert("Error", "Failed to save student: " + e.getMessage());
        }
    }

    private Student convertToEntity(StudentDTO studentDTO) {
        return new Student(studentDTO.getsId(), studentDTO.getName(), studentDTO.getContact(), studentDTO.getPayment(), studentDTO.getRegisteredDate());
    }

    private Course convertToEntity(CourseDTO courseDTO) {
        return new Course(courseDTO.getcId(), courseDTO.getName(), courseDTO.getDuration(), courseDTO.getFee());
    }

    @FXML
    void btnSearchOnAction(ActionEvent event) {
        try {
            int sId = Integer.parseInt(txtId.getText());
            StudentDTO studentDTO = studentBO.findStudentById(sId);
            if (studentDTO != null) {
                txtName.setText(studentDTO.getName());
                txtContact.setText(studentDTO.getContact());
                txtPayment.setText(String.valueOf(studentDTO.getPayment()));
                txtDate.setText(studentDTO.getRegisteredDate());
            } else {
                lblSelection.setText("Student not found");
                showAlert("Error", "Student not found.");
            }
        } catch (NumberFormatException e) {
            showAlert("Error", "Invalid Student ID.");
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        try {
            int sId = Integer.parseInt(txtId.getText());
            String name = txtName.getText();
            String contact = txtContact.getText();
            double payment = Double.parseDouble(txtPayment.getText());
            String registeredDate = txtDate.getText();

            StudentDTO studentDTO = new StudentDTO(sId, name, contact, payment, registeredDate);
            if (studentBO.updateStudent(studentDTO)) {
                loadStudents();
                clearFields();
                showAlert("Success", "Student updated successfully.");
            } else {
                showAlert("Error", "Failed to update student.");
            }
        } catch (NumberFormatException e) {
            showAlert("Error", "Invalid input values.");
        }
    }

    private void clearFields() {
        txtId.clear();
        txtName.clear();
        txtContact.clear();
        txtPayment.clear();
        txtDate.clear();
        lsCourses.getSelectionModel().clearSelection();
        lblSelection.setText(""); // Clear the label as well
    }
}
