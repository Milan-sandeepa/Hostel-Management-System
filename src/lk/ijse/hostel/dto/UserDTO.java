package lk.ijse.hostel.dto;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserDTO {
    private String userId;
    private String username;
    private String password;
}
