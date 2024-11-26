package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.CourseDTO;
import org.example.dto.StudentDTO;
import org.example.dto.courseStudentDetailsDTO;

import java.sql.SQLException;
import java.util.List;

public interface StudentBO extends SuperBO {
    boolean SaveStudent(StudentDTO studentDTO, List<courseStudentDetailsDTO> courseStudentDetailsDTOs)
            throws SQLException, ClassNotFoundException;

    boolean updateStudent(StudentDTO studentDTO);

    boolean deleteStudent(int sId);

    StudentDTO findStudentById(int sId);

    List<StudentDTO> getAllStudents();

    int getNextStudentId() throws SQLException;

    public List<CourseDTO> getRegisteredCourses(int studentId) throws SQLException;

    int getStudentCount();
}
