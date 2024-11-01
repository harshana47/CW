package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.userDTO;

import java.sql.SQLException;
import java.util.List;

public interface userBO extends SuperBO {
    boolean save(userDTO UserDTO) throws SQLException, ClassNotFoundException;
    boolean update(userDTO UserDTO) throws SQLException, ClassNotFoundException;
    boolean delete(int id) throws SQLException, ClassNotFoundException;
    userDTO findUserById(int id) throws Exception; // Assuming you need this for search
    userDTO findPositionByUserName(String un);
    userDTO findUserByUsername(String username) throws Exception;
    List<userDTO> getAllUsers() throws SQLException, ClassNotFoundException;

}
