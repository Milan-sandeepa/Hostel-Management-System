package lk.ijse.hostel.bo.custom;

import lk.ijse.hostel.bo.SuperBO;

import java.util.List;

public interface PendingKeyMoneyBO extends SuperBO {
    List<Object[]> getPendingKeyMoney();
    List<Object[]> getPaidKeyMoney();
}
