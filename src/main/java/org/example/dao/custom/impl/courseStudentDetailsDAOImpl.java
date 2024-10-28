package org.example.dao.custom.impl;

import org.example.dao.custom.courseStudentDetailsDAO;
import org.example.entity.courseStudentDetails;
import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

public class courseStudentDetailsDAOImpl implements courseStudentDetailsDAO {
    @Override
    public boolean save(List<courseStudentDetails> courseStudentDetailsList, Session session) throws SQLException, ClassNotFoundException {
        try {
            for (courseStudentDetails courseStudentDetails : courseStudentDetailsList) {
                boolean isSaved = save(courseStudentDetails, session);
            }
            return true;
        }catch (Exception e) {
            throw e;
        }
    }

    @Override
    public boolean save(courseStudentDetails entity, Session session) throws SQLException, ClassNotFoundException {
        session.save(entity);
        return true;
    }

    @Override
    public boolean save(courseStudentDetails entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(courseStudentDetails entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public List<courseStudentDetails> getAll() {
        return List.of();
    }

    @Override
    public courseStudentDetails search(int id) {
        return null;
    }
}
