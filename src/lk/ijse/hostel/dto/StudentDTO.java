package lk.ijse.hostel.dto;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class StudentDTO {
    private String id;
    private String fullName;
    private String address;
    private String contactNo;
    private String dob;
    private String gender;
}
