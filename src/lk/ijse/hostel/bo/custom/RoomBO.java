package lk.ijse.hostel.bo.custom;

import lk.ijse.hostel.bo.SuperBO;
import lk.ijse.hostel.dto.RoomDTO;
import lk.ijse.hostel.dto.StudentDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface RoomBO extends SuperBO {
    ArrayList<RoomDTO> getAllRooms() throws SQLException, ClassNotFoundException;

    boolean addRoom(RoomDTO roomDTO) throws SQLException, ClassNotFoundException;

    boolean updateRoom(RoomDTO roomDTO) throws SQLException, ClassNotFoundException;

    boolean ifRoomExist(String id) throws SQLException, ClassNotFoundException;

    boolean deleteRoom(String id) throws SQLException, ClassNotFoundException;

    String generateNewID() throws SQLException, ClassNotFoundException;
}
