package org.example.dao.custom;

import org.example.dao.CrudDAO;
import org.example.dto.CourseDTO;
import org.example.entity.Student;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public interface StudentDAO extends CrudDAO<Student> {
    boolean save(Student student, Session session);
    boolean update(Student student,Session session);
    public boolean delete(int sId, Session session);
    int getNextId() throws SQLException;

    int getCount();
}
