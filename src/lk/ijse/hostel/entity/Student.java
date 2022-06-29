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

    public Student(String student_id, String name, String address, String contactNo, String dob, String gender) {
        this.student_id = student_id;
        this.name = name;
        this.address = address;
        ContactNo = contactNo;
        this.dob = dob;
        this.gender = gender;
    }
}
