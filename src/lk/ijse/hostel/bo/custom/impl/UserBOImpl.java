package lk.ijse.hostel.bo.custom.impl;

import lk.ijse.hostel.bo.SuperBO;
import lk.ijse.hostel.bo.custom.UserBO;
import lk.ijse.hostel.dao.DAOFactory;
import lk.ijse.hostel.dao.custom.UserDAO;
import lk.ijse.hostel.dto.RoomDTO;
import lk.ijse.hostel.dto.StudentDTO;
import lk.ijse.hostel.dto.UserDTO;
import lk.ijse.hostel.entity.Room;
import lk.ijse.hostel.entity.Student;
import lk.ijse.hostel.entity.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserBOImpl implements UserBO{

    //Dependancy Injection-property injection
    private final UserDAO userDAO = (UserDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.USER);

    @Override
    public boolean addUser(UserDTO userDTO) throws SQLException, ClassNotFoundException {
        return userDAO.add(new User(
                userDTO.getUserId(),
                userDTO.getUsername(),
                userDTO.getPassword()
        ));
    }

    @Override
    public String getUserID() {
        String uId=null;
        for(String userId : userDAO.getUserID()){
            uId = userId;
        }
        return uId;
    }

    @Override
    public String getUserName() {
        String uname=null;
        for(String userName : userDAO.getUserName()){
            uname = userName;
        }
        return uname;
    }

    @Override
    public String getPassWord() {
        String psw=null;
        for(String p: userDAO.getPassWord()){
            psw = p;
        }
        return psw;
    }

    @Override
    public boolean updateUser(UserDTO userDTO) throws SQLException, ClassNotFoundException {
        return userDAO.update(new User(
                userDTO.getUserId(),
                userDTO.getUsername(),
                userDTO.getPassword()));
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return userDAO.generateNewID();
    }

    @Override
    public UserDTO getUser(String s) throws SQLException, ClassNotFoundException {
        User user = userDAO.get(s);
        return new UserDTO(user.getUserId(),user.getUsername(),user.getPassword());
    }

    @Override
    public ArrayList<UserDTO> getAllUsers() throws SQLException, ClassNotFoundException {
        ArrayList<UserDTO> allUser = new ArrayList<>();
        ArrayList<User> all = userDAO.getAll();
        for (User list : all) {
            allUser.add(new UserDTO(list.getUserId(),list.getUsername(),list.getPassword()));
        }
        return allUser;
    }
}
