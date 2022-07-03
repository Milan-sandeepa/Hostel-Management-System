package lk.ijse.hostel.dto;
import lk.ijse.hostel.entity.Room;
import lk.ijse.hostel.entity.Student;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReservationDTO {
    private String res_id;
    private LocalDate date;
    private String status;
    private Student student;
    private Room room;

}
