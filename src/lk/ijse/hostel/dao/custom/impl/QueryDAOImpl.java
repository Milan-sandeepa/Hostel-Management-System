package lk.ijse.hostel.dao.custom.impl;

import lk.ijse.hostel.dao.custom.QueryDAO;
import lk.ijse.hostel.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public List<Object[]> getPendingKeyMoney() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql="SELECT s.student_id,res.resId,res.status,res.room.room_Type_id FROM reservation res INNER JOIN student s ON res.student = s.student_id WHERE res.status!=:NotPaid";
        Query query = session.createQuery(hql);
        query.setParameter("NotPaid","paid");

        List<Object[]> list = query.list();

        transaction.commit();
        session.close();

        return list;
    }
}
