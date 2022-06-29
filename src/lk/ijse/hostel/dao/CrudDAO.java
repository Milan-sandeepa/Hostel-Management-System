package lk.ijse.hostel.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CrudDAO <T,ID> extends SuperDAO{
    public ArrayList<T> getAll()throws SQLException, ClassNotFoundException;

    public T get(ID id)throws SQLException, ClassNotFoundException;

    public List<T> search(ID id)throws SQLException, ClassNotFoundException;

    public boolean add(T entity)throws SQLException, ClassNotFoundException;

    public boolean update(T entity)throws SQLException, ClassNotFoundException;

    public boolean delete(ID id)throws SQLException, ClassNotFoundException;

    String generateNewID() throws SQLException, ClassNotFoundException;

    boolean exist(ID id) throws SQLException, ClassNotFoundException;
}
