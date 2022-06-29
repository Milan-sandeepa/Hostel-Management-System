package lk.ijse.hostel.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "reservation")
public class Reservation {
    @Id
    private String resId;
    @Column(columnDefinition = "DATE")
    private String date;

    @ManyToOne(cascade = CascadeType.ALL)
    private Student student;

    @ManyToOne(cascade = CascadeType.ALL)
    private Room room;

}
