package lk.ijse.hostel.bo.custom.impl;

import lk.ijse.hostel.bo.SuperBO;
import lk.ijse.hostel.bo.custom.UserBO;
import lk.ijse.hostel.dao.DAOFactory;
import lk.ijse.hostel.dao.custom.UserDAO;
import lk.ijse.hostel.dto.UserDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserBOImpl implements UserBO{

    //Dependancy Injection-property injection
    private final UserDAO userDAO = (UserDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.USER);

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
}
