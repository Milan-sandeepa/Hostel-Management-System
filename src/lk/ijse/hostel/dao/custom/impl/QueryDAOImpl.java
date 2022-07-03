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

        String hql="SELECT s.student_id,s.name,res.resId,res.status,res.room.room_Type_id,res.room.key_money FROM reservation res INNER JOIN student s ON res.student = s.student_id WHERE res.status!=:NotPaid";
        Query query = session.createQuery(hql);
        query.setParameter("NotPaid","paid");

        List<Object[]> list = query.list();

        transaction.commit();
        session.close();

        return list;
    }

    @Override
    public List<Object[]> getPaidMoney() {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql="SELECT s.student_id,s.name,res.resId,res.status,res.room.room_Type_id,res.room.key_money FROM reservation res INNER JOIN student s ON res.student = s.student_id WHERE res.status =:Paid";
        Query query = session.createQuery(hql);
        query.setParameter("Paid","Paid");

        List<Object[]> list = query.list();

        transaction.commit();
        session.close();

        return list;
    }
}
