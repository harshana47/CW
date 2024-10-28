package org.example.dao.custom.impl;

import org.example.dao.custom.userDAO;
import org.example.entity.user;

import java.sql.SQLException;
import java.util.List;

public class userDAOImpl implements userDAO {
    @Override
    public boolean save(user entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean update(user entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public List<user> getAll() {
        return List.of();
    }

    @Override
    public user search(int id) {
        return null;
    }
}
