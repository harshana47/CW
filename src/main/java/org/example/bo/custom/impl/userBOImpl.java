package org.example.bo.custom.impl;

import org.example.bo.custom.userBO;
import org.example.dao.DAOFactory;
import org.example.dao.custom.userDAO;
import org.example.dto.userDTO;
import org.example.entity.user;

import java.sql.SQLException;

public class userBOImpl implements userBO {

    private userDAO UserDAO = (userDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.User);
    @Override
    public boolean save(userDTO UserDTO) throws SQLException, ClassNotFoundException {
        return UserDAO.save(new user(UserDTO.getId(),UserDTO.getUsername(),UserDTO.getPassword(),UserDTO.getPosition()));
    }

    @Override
    public userDTO findPositionByUserName(String un) {
        user User = UserDAO.findByUn(un);
        if (User != null) {
            return new userDTO(User.getId(), User.getUsername(), User.getPassword(), User.getPosition());
        }
        return null; // Return null if the user is not found
    }


    @Override
    public userDTO findUserByUsername(String username) throws Exception {
        user User = UserDAO.findByUsername(username); // Implement this method in userDAO
        if (User != null) {
            return new userDTO(User.getId(), User.getUsername(), User.getPassword(), User.getPosition());
        }
        return null; // Return null if no user found
    }
}
