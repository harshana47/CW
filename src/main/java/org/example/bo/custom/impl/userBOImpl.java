package org.example.bo.custom.impl;

import org.example.bo.custom.userBO;
import org.example.dao.DAOFactory;
import org.example.dao.custom.userDAO;
import org.example.dto.userDTO;
import org.example.entity.user;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class userBOImpl implements userBO {

    private userDAO UserDAO = (userDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.User);

    @Override
    public boolean save(userDTO UserDTO) throws SQLException, ClassNotFoundException {
        return UserDAO.save(new user(UserDTO.getId(), UserDTO.getUsername(), UserDTO.getPassword(), UserDTO.getPosition()));
    }

    @Override
    public boolean update(userDTO UserDTO) throws SQLException, ClassNotFoundException {
        return UserDAO.update(new user(UserDTO.getId(), UserDTO.getUsername(), UserDTO.getPassword(), UserDTO.getPosition()));
    }

    @Override
    public boolean delete(int id) throws SQLException, ClassNotFoundException {
        return UserDAO.delete(id);
    }

    @Override
    public userDTO findUserById(int id) throws Exception {
        user User = UserDAO.search(id);
        if (User != null) {
            return new userDTO(User.getId(), User.getUsername(), User.getPassword(), User.getPosition());
        }
        return null; // Return null if no user found
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
        user User = UserDAO.findByUsername(username);
        if (User != null) {
            return new userDTO(User.getId(), User.getUsername(), User.getPassword(), User.getPosition());
        }
        return null; // Return null if no user found
    }

    @Override
    public List<userDTO> getAllUsers() throws SQLException, ClassNotFoundException {
        List<user> users = UserDAO.getAll(); // Assuming this method is available
        List<userDTO> userDTOs = new ArrayList<>();
        for (user User : users) {
            userDTOs.add(new userDTO(User.getId(), User.getUsername(), User.getPassword(), User.getPosition()));
        }
        return userDTOs;
    }

}
