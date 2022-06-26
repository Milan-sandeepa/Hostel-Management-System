package lk.ijse.hostel.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Room {
    @Id
    private String room_Type_id;
    private String type;
    private String key_money;
    private int qty;

}
