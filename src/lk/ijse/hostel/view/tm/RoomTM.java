package lk.ijse.hostel.view.tm;
import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Data
public class RoomTM implements Comparable<RoomTM>{
    private String room_Type_id;
    private String type;
    private String key_money;
    private int qty;

    @Override
    public int compareTo(RoomTM o) {
        return room_Type_id.compareTo(o.getRoom_Type_id());
    }
}
