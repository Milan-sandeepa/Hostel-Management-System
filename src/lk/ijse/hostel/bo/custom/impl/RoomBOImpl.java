package lk.ijse.hostel.bo.custom.impl;

import lk.ijse.hostel.bo.custom.RoomBO;
import lk.ijse.hostel.dto.RoomDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public class RoomBOImpl implements RoomBO {
    @Override
    public ArrayList<RoomDTO> getAllRooms() throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean addRoom(RoomDTO roomDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean updateRoom(RoomDTO roomDTO) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean ifRoomExist(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean deleteRoom(String id) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public String generateNewID() throws SQLException, ClassNotFoundException {
        return null;
    }
}
