package lk.ijse.hostel.bo.custom.impl;

import lk.ijse.hostel.bo.custom.ReservationBO;
import lk.ijse.hostel.dao.DAOFactory;
import lk.ijse.hostel.dao.custom.ReservationDAO;
import lk.ijse.hostel.dao.custom.RoomDAO;
import lk.ijse.hostel.dao.custom.StudentDAO;
import lk.ijse.hostel.dto.ReservationDTO;
import lk.ijse.hostel.dto.RoomDTO;
import lk.ijse.hostel.dto.StudentDTO;
import lk.ijse.hostel.entity.Reservation;
import lk.ijse.hostel.entity.Room;
import lk.ijse.hostel.entity.Student;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReservationBOImpl implements ReservationBO {

    private final ReservationDAO reservationDAO= (ReservationDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.RESERVE);
    private final StudentDAO studentDAO= (StudentDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.STUDENT);
    private final RoomDAO roomDAO= (RoomDAO) DAOFactory.getDAOFactory().getDAO(DAOFactory.DAOTypes.ROOM);

    @Override
    public boolean reservationRoom(ReservationDTO dto) throws SQLException, ClassNotFoundException {
        return reservationDAO.add(new Reservation(
                dto.getRes_id(),
                dto.getDate(),
                dto.getStatus(),
                dto.getStudent(),
                dto.getRoom()));
    }

    @Override
    public String generateNewReservationId() throws SQLException, ClassNotFoundException {
        return reservationDAO.generateNewID();
    }

    @Override
    public ArrayList<StudentDTO> getAllStudents() throws SQLException, ClassNotFoundException {
        ArrayList<Student> all = studentDAO.getAll();
        ArrayList<StudentDTO> allStudents=new ArrayList<>();
        for (Student student : all) {
            allStudents.add(new StudentDTO(student.getStudent_id(),student.getName(),student.getAddress(),student.getContactNo(),student.getDob(),student.getGender()));
        }
        return allStudents;
    }

    @Override
    public ArrayList<RoomDTO> getAllRooms() throws SQLException, ClassNotFoundException {
        ArrayList<Room> all = roomDAO.getAll();
        ArrayList<RoomDTO> allRooms=new ArrayList<>();
        for (Room room : all) {
            allRooms.add(new RoomDTO(room.getRoom_Type_id(),room.getType(),room.getKey_money(),room.getQty()));
        }
        return allRooms;
    }

    @Override
    public ArrayList<ReservationDTO> getAllReservations() throws SQLException, ClassNotFoundException {
        ArrayList<Reservation> all = reservationDAO.getAll();
        ArrayList<ReservationDTO> allReservations=new ArrayList<>();
        for (Reservation reservation : all) {
            allReservations.add(new ReservationDTO(reservation.getResId(),reservation.getDate(),reservation.getStatus(),reservation.getStudent(),reservation.getRoom()));
        }
        return allReservations;
    }

    @Override
    public RoomDTO searchRoom(String code) throws SQLException, ClassNotFoundException {
        Room r = roomDAO.search(code);
        return new RoomDTO(r.getRoom_Type_id(),r.getType(),r.getKey_money(),r.getQty());
    }

    @Override
    public boolean ifRoomExist(String code) throws SQLException, ClassNotFoundException {
        return roomDAO.exist(code);
    }

    @Override
    public boolean ifStudentExist(String id) throws SQLException, ClassNotFoundException {
        return studentDAO.exist(id);
    }

    @Override
    public StudentDTO searchStudent(String id) throws SQLException, ClassNotFoundException {
        Student s = studentDAO.search(id);
        return new StudentDTO(s.getStudent_id(),s.getName(),s.getAddress(),s.getContactNo(),s.getDob(),s.getGender());
    }
}
