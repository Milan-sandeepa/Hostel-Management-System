package lk.ijse.hostel.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CrudDAO <T,ID> extends SuperDAO{
    public List<T> getAll();

    public T get(ID id);

    public List<T> search(ID id);

    public boolean save(T entity);

    public boolean update(T entity);

    public boolean delete(ID id);
}
