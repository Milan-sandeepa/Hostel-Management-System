package lk.ijse.hostel.view.tm;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReservationTM {
    String res_id;
    LocalDate date;
    String roomId;
    String studentId;
    String status;
}
