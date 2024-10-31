package org.example.dao.custom;

import org.example.dao.CrudDAO;
import org.example.entity.user;

public interface userDAO extends CrudDAO<user> {
    user findByUn(String un);

    user findByUsername(String username);
}
