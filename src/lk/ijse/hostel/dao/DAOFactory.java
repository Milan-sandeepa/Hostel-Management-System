package lk.ijse.hostel.dao;

import lk.ijse.hostel.dao.custom.impl.UserDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getDAOFactory() {
        if (daoFactory == null) {
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    //factory method
    public SuperDAO getDAO(DAOTypes types) {
        switch (types) {
            case USER:
                return new UserDAOImpl();
            case STUDENT:
                return null;
            case ROOM:
                return null;
            case RESERVE:
                return null;
            case QUERYDAO:
                return null;
            default:
                return null;
        }
    }
    public enum DAOTypes {
        USER, STUDENT, ROOM, RESERVE, QUERYDAO
    }
}
