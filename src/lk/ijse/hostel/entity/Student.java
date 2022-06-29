package lk.ijse.hostel.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "student")
public class Student {
    @Id
    private String student_id;
    private String name;
    @Column(columnDefinition = "TEXT")
    private String address;
    private String ContactNo;
    @Column(columnDefinition = "DATE")
    private String dob;
    private String gender;

    @OneToMany(mappedBy = "student", fetch = FetchType.EAGER)
    private List<Reservation> resList = new ArrayList<>();

}
