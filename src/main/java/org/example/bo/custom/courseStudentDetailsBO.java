package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.courseStudentDetailsDTO;

import java.sql.SQLException;
import java.util.List;

public interface courseStudentDetailsBO extends SuperBO {
    boolean save(courseStudentDetailsDTO courseStudentDetailsDTO) throws SQLException, ClassNotFoundException;
    boolean update(courseStudentDetailsDTO courseStudentDetailsDTO) throws SQLException, ClassNotFoundException;
    boolean delete(int id) throws SQLException, ClassNotFoundException;
    List<courseStudentDetailsDTO> getAll() throws Exception;
    courseStudentDetailsDTO findById(int id) throws SQLException, ClassNotFoundException;
    List<courseStudentDetailsDTO> getByCourseId(int courseId) throws Exception;

}
