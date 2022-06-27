package lk.ijse.hostel.dao;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO <T,ID> extends SuperDAO{
    public boolean add(T t) throws SQLException, ClassNotFoundException;
    public boolean update(T t) throws SQLException, ClassNotFoundException;
    public boolean delete(ID id) throws SQLException, ClassNotFoundException;
    public T search(ID id) throws SQLException, ClassNotFoundException;
    public ArrayList<T> getAll() throws SQLException, ClassNotFoundException;
    String generateNewID() throws SQLException, ClassNotFoundException;
    boolean exist(ID id) throws SQLException, ClassNotFoundException;
}
