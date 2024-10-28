package org.example.dao.custom.impl;

import org.example.config.FactoryConfiguration;
import org.example.dao.custom.StudentDAO;
import org.example.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.SQLException;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {

    public boolean save(Student student, Session session) {
        try {
            session.save(student);
            return true;
        } catch (Exception e) {
            e.printStackTrace(); // Handle exception
            return false;
        }
    }

    @Override
    public boolean save(Student entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(Student entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean update(Student student,Session session) {
        Transaction transaction = session.beginTransaction();
        try {
            session.update(student);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace(); // Handle exception
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean delete(int sId,Session session) {
        Transaction transaction = session.beginTransaction();
        try {
            Student student = session.get(Student.class, sId);
            if (student != null) {
                session.delete(student);
                transaction.commit();
                return true;
            }
            return false; // Student not found
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace(); // Handle exception
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public List<Student> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        try {
            return session.createQuery("FROM Student", Student.class).list();
        } catch (Exception e) {
            e.printStackTrace(); // Handle exception
            return List.of(); // Return an empty list in case of error
        } finally {
            session.close();
        }
    }

    @Override
    public Student search(int id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        try {
            return session.get(Student.class, id);
        } catch (Exception e) {
            e.printStackTrace(); // Handle exception
            return null; // Return null if not found
        } finally {
            session.close();
        }
    }
}
