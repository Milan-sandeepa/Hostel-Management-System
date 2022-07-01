package lk.ijse.hostel.dao;

import lk.ijse.hostel.dao.custom.impl.*;

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
                return new StudentDAOImpl();
            case ROOM:
                return new RoomDAOImpl();
            case RESERVE:
                return new ReservationDAOImpl();
            case QUERYDAO:
                return new QueryDAOImpl();
            default:
                return null;
        }
    }
    public enum DAOTypes {
        USER, STUDENT, ROOM, RESERVE, QUERYDAO
    }
}
