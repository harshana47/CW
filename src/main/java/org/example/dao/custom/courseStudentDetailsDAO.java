package org.example.dao.custom;

import org.example.dao.CrudDAO;
import org.example.dto.CourseDTO;
import org.example.entity.courseStudentDetails;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public interface courseStudentDetailsDAO extends CrudDAO<courseStudentDetails> {

    public boolean save(List<courseStudentDetails> courseStudentDetailsList, Session session) throws SQLException, ClassNotFoundException;
    public boolean save(courseStudentDetails entity, Session session) throws SQLException, ClassNotFoundException;
    List<CourseDTO> getCoursesByStudentId(int studentId) throws SQLException;
    List<courseStudentDetails> getDetailsByStudentId(int studentId, Session session);
    boolean delete(int id, Session session);

    List<courseStudentDetails> getDetailsByCourseId(int cId, Session session);

    List<courseStudentDetails> getDetailsByCourseIds(int courseId);
}
