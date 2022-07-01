package lk.ijse.hostel.bo;

import lk.ijse.hostel.bo.custom.impl.ReservationBOImpl;
import lk.ijse.hostel.bo.custom.impl.RoomBOImpl;
import lk.ijse.hostel.bo.custom.impl.StudentBOImpl;
import lk.ijse.hostel.bo.custom.impl.UserBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {
    }

    public static BOFactory getBOFactory() {
        if (boFactory == null) {
            boFactory = new BOFactory();
        }
        return boFactory;
    }

    public SuperBO getBO(BOTypes types) {
        switch (types) {
            case USER:
                return new UserBOImpl();
            case STUDENT:
                return new StudentBOImpl();
            case ROOM:
                return new RoomBOImpl();
            case RESERVE:
                return new ReservationBOImpl();
            case QUERYDAO:
                return null;
            default:
                return null;
        }
    }

    public enum BOTypes {
        USER, STUDENT, ROOM, RESERVE, QUERYDAO
    }
}
