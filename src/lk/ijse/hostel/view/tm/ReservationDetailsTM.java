package lk.ijse.hostel.view.tm;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ReservationDetailsTM {
    private String code;
    private String  type;
    private double keyMoney;
    private int qty;
    private double total;
    private String status;


    public ReservationDetailsTM(String code, double keyMoney, int qty, double total, String status) {
        this.code = code;
        this.keyMoney = keyMoney;
        this.qty = qty;
        this.total = total;
        this.status = status;
    }
}
