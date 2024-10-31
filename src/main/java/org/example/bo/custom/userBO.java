package org.example.bo.custom;

import org.example.bo.SuperBO;
import org.example.dto.userDTO;

import java.sql.SQLException;

public interface userBO extends SuperBO {
    boolean save(userDTO UserDTO) throws SQLException, ClassNotFoundException;

    userDTO findPositionByUserName(String un);

    userDTO findUserByUsername(String username) throws Exception;
}
