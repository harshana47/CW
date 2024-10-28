package org.example.dao.custom.impl;

import org.example.config.FactoryConfiguration;
import org.example.dao.custom.CourseDAO;
import org.example.entity.Course;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CourseDAOImpl implements CourseDAO {

    @Override
    public boolean save(Course entity) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.save(entity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace(); // Handle exception
            return false;
        }
    }

    @Override
    public boolean update(Course course) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(course);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace(); // Handle exception
            return false;
        }
        // Session will be closed in the calling method
    }

    @Override
    public boolean delete(int cId) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            Course course = session.get(Course.class, cId);
            if (course != null) {
                session.delete(course);
                transaction.commit();
                return true;
            }
            return false; // Course not found
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace(); // Handle exception
            return false;
        }
        // Session will be closed in the calling method
    }

    @Override
    public List<Course> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        try {
            return session.createQuery("FROM Course", Course.class).list();
        } catch (Exception e) {
            e.printStackTrace(); // Handle exception
            return List.of(); // Return an empty list in case of error
        }
        // Session will be closed in the calling method
    }

    @Override
    public Course findCourseById(int cId) {
        Session session = FactoryConfiguration.getInstance().getSession();
        try {
            return session.get(Course.class, cId);
        } catch (Exception e) {
            e.printStackTrace(); // Handle exception
            return null; // Return null if not found
        }
        // Session will be closed in the calling method
    }

    @Override
    public Course search(int id) {
        return findCourseById(id); // Using the same method to find by ID
    }
}
