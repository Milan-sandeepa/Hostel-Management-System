package lk.ijse.hostel.dao.custom;

import lk.ijse.hostel.dao.SuperDAO;

import java.util.List;

public interface QueryDAO extends SuperDAO {
    List<Object[]> getPendingKeyMoney();
}
