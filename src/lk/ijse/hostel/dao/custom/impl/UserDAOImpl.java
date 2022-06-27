package lk.ijse.hostel.dao.custom.impl;

import lk.ijse.hostel.dao.SuperDAO;
import lk.ijse.hostel.dao.custom.UserDAO;
import lk.ijse.hostel.entity.User;
import lk.ijse.hostel.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class UserDAOImpl implements UserDAO {

    public List<String> getUserName() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("SELECT username FROM User");
        List<String> username = query.list();

        transaction.commit();
        session.close();

        return username;
    }

    public List<String> getPassWord() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("SELECT password FROM User");
        List<String> password = query.list();

        transaction.commit();
        session.close();

        return password;
    }

    @Override
    public List<User> getAll() {
        return null;
    }

    @Override
    public User get(String s) {
        return null;
    }

    @Override
    public List<User> search(String s) {
        return null;
    }

    @Override
    public boolean save(User entity) {
        return false;
    }

    @Override
    public boolean update(User entity) {
        return false;
    }

    @Override
    public boolean delete(String s) {
        return false;
    }
}
