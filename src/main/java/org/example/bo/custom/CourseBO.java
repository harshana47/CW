package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.CourseDTO;

import java.sql.SQLException;
import java.util.List;

public interface CourseBO extends SuperBO {
    boolean saveCourse(CourseDTO courseDTO)throws SQLException, ClassNotFoundException;
    boolean updateCourse(CourseDTO courseDTO);
    boolean deleteCourse(int id);
    List<CourseDTO> getAllCoursers();
    CourseDTO findCourseById(int id);

    int getNextCourseId();
}
