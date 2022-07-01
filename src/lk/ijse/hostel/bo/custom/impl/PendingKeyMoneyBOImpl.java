package lk.ijse.hostel.bo.custom.impl;

import lk.ijse.hostel.bo.custom.PendingKeyMoneyBO;
import lk.ijse.hostel.dao.DAOFactory;
import lk.ijse.hostel.dao.custom.QueryDAO;

import java.util.List;

public class PendingKeyMoneyBOImpl implements PendingKeyMoneyBO {

    private final QueryDAO queryDAO = (QueryDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.QUERYDAO);

    @Override
    public List<Object[]> getPendingKeyMoney() {
        return queryDAO.getPendingKeyMoney();
    }
}
