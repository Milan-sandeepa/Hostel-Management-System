package lk.ijse.hostel.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Reservation {
    @Id
    private String resId;
    private LocalDate date;
    private Student student;

}
