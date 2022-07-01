package lk.ijse.hostel.bo.custom;

import lk.ijse.hostel.bo.SuperBO;
import lk.ijse.hostel.dto.ReservationDTO;
import lk.ijse.hostel.dto.RoomDTO;
import lk.ijse.hostel.dto.StudentDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ReservationBO extends SuperBO {
    boolean reservationRoom(ReservationDTO dto) throws SQLException, ClassNotFoundException;

    String generateNewReservationId()throws SQLException, ClassNotFoundException;

    ArrayList<StudentDTO> getAllStudents()throws SQLException, ClassNotFoundException;

    ArrayList<RoomDTO> getAllRooms()throws SQLException, ClassNotFoundException;

    ArrayList<ReservationDTO> getAllReservations() throws SQLException, ClassNotFoundException;

    RoomDTO searchRoom(String code)throws SQLException, ClassNotFoundException;

    boolean ifRoomExist(String code) throws SQLException, ClassNotFoundException;

    boolean ifStudentExist(String id) throws SQLException, ClassNotFoundException;

    StudentDTO searchStudent(String s)throws SQLException, ClassNotFoundException;
}
