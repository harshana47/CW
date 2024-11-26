package org.example.dao.custom.impl;

import org.example.config.FactoryConfiguration;
import org.example.dao.custom.CourseDAO;
import org.example.entity.Course;
import org.example.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

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
            e.printStackTrace();
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
            e.printStackTrace();
            return false;
        }
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
            return false;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public List<Course> getAll() {
        Session session = FactoryConfiguration.getInstance().getSession();
        try {
            return session.createQuery("FROM Course", Course.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            return List.of();
        }
    }

    @Override
    public Course findCourseById(int cId) {
        Session session = FactoryConfiguration.getInstance().getSession();
        try {
            return session.get(Course.class, cId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int getNextId() {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            Query<Integer> query = session.createQuery("SELECT MAX(cId) FROM Course", Integer.class);
            Integer maxId = query.uniqueResult();
            return (maxId!= null)? maxId + 1 : 1;
        } catch (Exception e) {
            e.printStackTrace();
            return 1;
        }
    }

    @Override
    public boolean delete(int cId, Session session) {
        try {
            Course course = session.get(Course.class, cId);
            if (course != null) {
                session.delete(course);
                return true;
            }
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public int getCount() {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            Query<Long> query = session.createQuery("SELECT COUNT(*) FROM Course", Long.class);
            Long count = query.uniqueResult();
            return (count!= null)? count.intValue() : 0;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public Course search(int id) {
        return findCourseById(id);
    }
}
