package lk.ijse.hostel.bo.custom;

import lk.ijse.hostel.bo.SuperBO;
import lk.ijse.hostel.dto.StudentDTO;

import java.sql.SQLException;
import java.util.ArrayList;

public interface StudentBO extends SuperBO {
    ArrayList<StudentDTO> getAllStudent() throws SQLException, ClassNotFoundException;

    boolean addStudent(StudentDTO studentDTO) throws SQLException, ClassNotFoundException;

    boolean updateStudent(StudentDTO studentDTO) throws SQLException, ClassNotFoundException;

    boolean ifStudentExist(String id) throws SQLException, ClassNotFoundException;

    boolean deleteStudent(String id) throws SQLException, ClassNotFoundException;

    String generateNewID() throws SQLException, ClassNotFoundException;
}
