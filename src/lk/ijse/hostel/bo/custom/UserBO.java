package lk.ijse.hostel.bo.custom;

import lk.ijse.hostel.bo.SuperBO;
import lk.ijse.hostel.dto.StudentDTO;
import lk.ijse.hostel.dto.UserDTO;
import lk.ijse.hostel.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface UserBO extends SuperBO {
    boolean addUser(UserDTO userDTO) throws SQLException, ClassNotFoundException;
    public String getUserID();
    public String getUserName();
    public String getPassWord();
    boolean updateUser(UserDTO userDTO) throws SQLException, ClassNotFoundException;
    public String generateNewID() throws SQLException, ClassNotFoundException;
    public UserDTO getUser(String s) throws SQLException, ClassNotFoundException;
    ArrayList<UserDTO> getAllUsers() throws SQLException, ClassNotFoundException;

}
