package org.example.bo.custom.impl;

import org.example.bo.custom.CourseBO;
import org.example.dao.DAOFactory;
import org.example.dao.custom.CourseDAO;
import org.example.dto.CourseDTO;
import org.example.entity.Course;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseBOImpl implements CourseBO {

    CourseDAO courseDAO = (CourseDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.Course);

    @Override
    public boolean saveCourse(CourseDTO courseDTO) throws SQLException, ClassNotFoundException {
        return courseDAO.save(new Course(courseDTO.getcId(), courseDTO.getName(), courseDTO.getDuration(), courseDTO.getFee()));
    }

    @Override
    public boolean updateCourse(CourseDTO courseDTO) {
        return courseDAO.update(new Course(courseDTO.getcId(), courseDTO.getName(), courseDTO.getDuration(), courseDTO.getFee()));
    }

    @Override
    public boolean deleteCourse(int id) {
        return courseDAO.delete(id);
    }

    @Override
    public List<CourseDTO> getAllCoursers() {
        List<CourseDTO> courseDTOS = new ArrayList<>();
        List<Course> courses = courseDAO.getAll();
        for (Course course : courses) {
            CourseDTO courseDTO = new CourseDTO(course.getcId(),course.getName(),course.getDuration(),course.getFee());
            courseDTOS.add(courseDTO);
        }
        return courseDTOS;
    }

    @Override
    public CourseDTO findCourseById(int id) {
        Course course = courseDAO.search(id);
        CourseDTO courseDTO = new CourseDTO(course.getcId(), course.getName(), course.getDuration(), course.getFee());
        return courseDTO;
    }

    public int getNextCourseId(){
        return courseDAO.getNextId();
    }
}
