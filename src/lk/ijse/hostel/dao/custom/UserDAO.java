package lk.ijse.hostel.dao.custom;

import lk.ijse.hostel.dao.CrudDAO;
import lk.ijse.hostel.entity.User;

import java.util.List;

public interface UserDAO extends CrudDAO<User,String> {
    public List<String> getUserName();
    public List<String> getPassWord();
}
