package org.example.dao.custom;

import org.example.dao.CrudDAO;
import org.example.entity.Course;
import org.example.entity.Student;
import org.hibernate.Session;

import java.util.List;

public interface CourseDAO extends CrudDAO<Course> {
    boolean save(Course course);
    boolean update(Course course);
    boolean delete(int cId);
    List<Course> getAll();
    Course findCourseById(int cId);

    int getNextId();

    boolean delete(int cId, Session session);
}
