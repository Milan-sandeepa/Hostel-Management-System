package lk.ijse.hostel.dao.custom;

import lk.ijse.hostel.dao.CrudDAO;
import lk.ijse.hostel.entity.Reservation;
import lk.ijse.hostel.entity.Room;

public interface ReservationDAO extends CrudDAO<Reservation,String> {
    boolean updateStatus(String res_id, String status);
}
