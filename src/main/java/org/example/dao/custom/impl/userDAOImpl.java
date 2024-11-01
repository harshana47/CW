package org.example.dao.custom.impl;

import org.example.config.FactoryConfiguration;
import org.example.dao.custom.userDAO;
import org.example.entity.user;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class userDAOImpl implements userDAO {

    @Override
    public boolean save(user entity) throws SQLException, ClassNotFoundException {
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
    public boolean update(user entity) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            session.update(entity);
            transaction.commit();
            return true;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        try {
            user entity = session.get(user.class, id);
            if (entity != null) {
                session.delete(entity);
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
    public List<user> getAll() {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            Query<user> query = session.createQuery("FROM user", user.class);
            return query.list();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>(); // Return an empty list on error
        }
    }


    @Override
    public user search(int id) {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            return session.get(user.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public user findByUn(String un) {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            Query<user> query = session.createQuery("FROM user WHERE username = :username", user.class);
            query.setParameter("username", un);
            return query.uniqueResult(); // Returns a single result or null
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    @Override
    public user findByUsername(String username) {
        try (Session session = FactoryConfiguration.getInstance().getSession()) {
            Query<user> query = session.createQuery("FROM user WHERE username = :username", user.class);
            query.setParameter("username", username);
            return query.uniqueResult(); // Returns a single result or null
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
