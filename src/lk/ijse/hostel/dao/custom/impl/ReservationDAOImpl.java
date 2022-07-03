package lk.ijse.hostel.dao.custom.impl;

import lk.ijse.hostel.dao.custom.ReservationDAO;
import lk.ijse.hostel.entity.Reservation;
import lk.ijse.hostel.util.FactoryConfiguration;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationDAOImpl implements ReservationDAO {
    @Override
    public ArrayList<Reservation> getAll() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        String hql="FROM reservation";
        Query query = session.createQuery(hql);
        ArrayList<Reservation> reservationList = (ArrayList<Reservation>) query.list();

        transaction.commit();
        session.close();

        return reservationList;
    }

    @Override
    public Reservation get(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public Reservation search(String s) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean add(Reservation entity) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.save(entity);

        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public boolean update(Reservation entity) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        session.update(entity);

        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public boolean delete(String id) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Reservation reservation = session.load(Reservation.class, id);
        session.delete(reservation);

        transaction.commit();
        session.close();

        return true;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();

        Query query = session.createQuery("SELECT resId FROM reservation ORDER BY resId DESC");
        query.setMaxResults(1);
        List<String> list = query.list();

        transaction.commit();
        session.close();

        return list.isEmpty() ? "RE001" : String.format("RE%03d", (Integer.parseInt(list.get(0).replace("RE", "")) + 1));
    }

    @Override
    public boolean exist(String s) throws SQLException, ClassNotFoundException {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("SELECT resId FROM reservation WHERE id=:Id");
        String id1 = (String) query.setParameter("Id", s).uniqueResult();
        if (id1 != null) {
            return true;
        }
        transaction.commit();
        session.close();
        return false;
    }

    @Override
    public boolean updateStatus(String res_id, String status) {
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("UPDATE reservation SET status=:newStatus where resId=:userId ");
        query.setParameter("newStatus", status);
        query.setParameter("userId", res_id);

        query.executeUpdate();

        transaction.commit();
        session.close();
        return true;
    }
}
