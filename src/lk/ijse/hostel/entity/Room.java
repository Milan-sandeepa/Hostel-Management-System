package lk.ijse.hostel.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity(name = "room")
public class Room {
    @Id
    private String room_Type_id;
    private String type;
    private String key_money;
    private int qty;

    @OneToMany(mappedBy = "room",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Reservation> resList = new ArrayList<>();

    public Room(String room_Type_id, String type, String key_money, int qty) {
        this.room_Type_id = room_Type_id;
        this.type = type;
        this.key_money = key_money;
        this.qty = qty;
    }
}
