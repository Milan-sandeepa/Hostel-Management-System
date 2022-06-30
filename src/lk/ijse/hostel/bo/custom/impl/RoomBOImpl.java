package lk.ijse.hostel.bo.custom.impl;

import lk.ijse.hostel.bo.custom.RoomBO;
import lk.ijse.hostel.dao.DAOFactory;
import lk.ijse.hostel.dao.custom.RoomDAO;
import lk.ijse.hostel.dao.custom.StudentDAO;
import lk.ijse.hostel.dto.RoomDTO;
import lk.ijse.hostel.dto.StudentDTO;
import lk.ijse.hostel.entity.Room;
import lk.ijse.hostel.entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;

public class RoomBOImpl implements RoomBO {

    //Depencancy Injection-property injection
    private final RoomDAO roomDAO= (RoomDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ROOM);

    @Override
    public ArrayList<RoomDTO> getAllRooms() throws SQLException, ClassNotFoundException {
        ArrayList<RoomDTO> allRooms = new ArrayList<>();
        ArrayList<Room> all = roomDAO.getAll();
        for (Room list : all) {
            allRooms.add(new RoomDTO(list.getRoom_Type_id(),list.getType(),list.getKey_money(),list.getQty()));
        }
        return allRooms;
    }

    @Override
    public boolean addRoom(RoomDTO roomDTO) throws SQLException, ClassNotFoundException {
        return roomDAO.add(new Room(
                roomDTO.getRoom_Type_id(),
                roomDTO.getType(),
                roomDTO.getKey_money(),
                roomDTO.getQty()
        ));
    }

    @Override
    public boolean updateRoom(RoomDTO roomDTO) throws SQLException, ClassNotFoundException {
        return roomDAO.update(new Room(
                roomDTO.getRoom_Type_id(),
                roomDTO.getType(),
                roomDTO.getKey_money(),
                roomDTO.getQty()
        ));
    }

    @Override
    public boolean ifRoomExist(String id) throws SQLException, ClassNotFoundException {
        return roomDAO.exist(id);
    }

    @Override
    public boolean deleteRoom(String id) throws SQLException, ClassNotFoundException {
        return roomDAO.delete(id);
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return roomDAO.generateNewID();
    }
}
