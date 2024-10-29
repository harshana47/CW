package org.example.dao.custom.impl;

import org.example.config.FactoryConfiguration;
import org.example.dao.custom.courseStudentDetailsDAO;
import org.example.dto.CourseDTO;
import org.example.entity.courseStudentDetails;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
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
    public List<CourseDTO> getCoursesByStudentId(int studentId) throws SQLException {
        List<CourseDTO> courses = new ArrayList<>();
        Session session = null;

        try {
            session = FactoryConfiguration.getInstance().getSession();
            String hql = "SELECT new org.example.dto.CourseDTO(c.cId, c.name, cs.duration, cs.fee) " +
                    "FROM courseStudentDetails cs " +
                    "JOIN cs.course c " +
                    "WHERE cs.student.sId = :studentId"; // Use the student relationship here

            Query<CourseDTO> query = session.createQuery(hql, CourseDTO.class);
            query.setParameter("studentId", studentId);
            courses = query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }

        return courses;
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

    @Override
    public List<courseStudentDetails> getDetailsByStudentId(int studentId, Session session) {
        try {
            return session.createQuery("FROM courseStudentDetails csd WHERE csd.student.sId = :studentId", courseStudentDetails.class)
                    .setParameter("studentId", studentId)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace(); // Handle exception
            return List.of(); // Return empty list in case of error
        }
    }

    @Override
    public boolean delete(int id, Session session) {
        try {
            courseStudentDetails detail = session.get(courseStudentDetails.class, id);
            if (detail != null) {
                session.delete(detail);
                return true;
            }
            return false; // Detail not found
        } catch (Exception e) {
            e.printStackTrace(); // Handle exception
            return false;
        }
    }

    @Override
    public List<courseStudentDetails> getDetailsByCourseId(int cId, Session session) {
        try {
            return session.createQuery("FROM courseStudentDetails csd WHERE csd.course.cId = :courseId", courseStudentDetails.class)
                    .setParameter("courseId", cId)
                    .getResultList();
        } catch (Exception e) {
            e.printStackTrace(); // Handle exception
            return List.of(); // Return empty list in case of error
        }
    }

}
