package lk.ijse.hostel.view.tm;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentTM implements Comparable<StudentTM>{
    private String id;
    private String name;
    private String address;
    private String contact;
    private String dob;
    private String gender;

    @Override
    public int compareTo(StudentTM o) {
        return id.compareTo(o.getId());
    }
}
