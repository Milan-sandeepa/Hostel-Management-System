package lk.ijse.hostel.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "room",fetch = FetchType.EAGER)
    private List<Reservation> resList = new ArrayList<>();

}
