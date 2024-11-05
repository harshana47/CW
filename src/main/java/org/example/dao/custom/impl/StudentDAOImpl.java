package org.example.dao.custom.impl;

import org.example.config.FactoryConfiguration;
import org.example.dao.DAOFactory;
import org.example.dao.custom.StudentDAO;
import org.example.dao.custom.courseStudentDetailsDAO; // Correct naming as per your requirement
import org.example.entity.Student;
import org.example.entity.courseStudentDetails;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    private courseStudentDetailsDAO courseStudentDetailsDAO =
            (courseStudentDetailsDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.courseStudentDetails);

    @Override
    public boolean save(Student entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Student student) {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(student);
            transaction.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean delete(int sId, Session session) {
        try {
            Student student = session.get(Student.class, sId);
            if (student != null) {
                session.delete(student);
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public List<Student> getAll() {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            List<Student> students = session.createQuery("FROM Student", Student.class).list();
            transaction.commit();
            session.close();
            return students;
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }


    @Override
    public Student search(int id) {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            Student student = session.get(Student.class, id);
            transaction.commit();
            session.close();
            return student;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public boolean save(Student student, Session session) {
        try {
            session.save(student);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(Student student, Session session) {
        return false;
    }

    @Override
    public int getNextId() {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            Transaction transaction = session.beginTransaction();
            Query<Integer> query = session.createQuery("SELECT MAX(sId) FROM Student", Integer.class);
            Integer maxId = query.uniqueResult();
            transaction.commit();
            session.close();
            return (maxId != null) ? maxId + 1 : 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }
}
