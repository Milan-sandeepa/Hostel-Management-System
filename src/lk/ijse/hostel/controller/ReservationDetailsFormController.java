package lk.ijse.hostel.controller;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hostel.bo.BOFactory;
import lk.ijse.hostel.bo.custom.impl.PendingKeyMoneyBOImpl;
import lk.ijse.hostel.util.SetNavigation;
import lk.ijse.hostel.view.tm.KeyMoneyTM;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class ReservationDetailsFormController {
    public AnchorPane root;
    public TableView<KeyMoneyTM> tblReservation;
    public TableColumn colResId;
    public TableColumn colStudentId;
    public TableColumn colStudentName;
    public TableColumn colRoomID;
    public TableColumn colKeyMoney;
    public TableColumn colStatus;

    private final PendingKeyMoneyBOImpl pendingKeyMoneyBO= (PendingKeyMoneyBOImpl) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.QUERYBO);

    public void initialize(){
        tblReservation.getColumns().get(0).setCellValueFactory(new PropertyValueFactory("res_Id"));
        tblReservation.getColumns().get(1).setCellValueFactory(new PropertyValueFactory("studentId"));
        tblReservation.getColumns().get(2).setCellValueFactory(new PropertyValueFactory("studentName"));
        tblReservation.getColumns().get(3).setCellValueFactory(new PropertyValueFactory("roomId"));
        tblReservation.getColumns().get(4).setCellValueFactory(new PropertyValueFactory("keyMoney"));
        tblReservation.getColumns().get(5).setCellValueFactory(new PropertyValueFactory("status"));

        loadAllReservertions();
    }

    private void loadAllReservertions() {
        List<Object[]> list = pendingKeyMoneyBO.getPaidKeyMoney();
        for (Object[] objects : list) {
            String res_Id= (String) objects[2];
            String Student_id= (String) objects[0];
            String student_name=(String) objects[1];
            String roomId= (String) objects[4];
            String keyMoney= (String) objects[5];
            String status= (String) objects[3];
            tblReservation.getItems().add(new KeyMoneyTM(res_Id,Student_id,student_name,roomId,keyMoney,status));
        }
    }

    public void signOutPressed(MouseEvent mouseEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are You Sure LogOut?", ButtonType.YES,ButtonType.NO);

        Optional<ButtonType> buttonType = alert.showAndWait();

        if (buttonType.get().equals(ButtonType.YES)){
            SetNavigation.setUI("LoginForm","Login",this.root);
        }
    }

    public void navigateToHome(MouseEvent mouseEvent) throws IOException {
        SetNavigation.setUI("DashboardForm","Home",this.root);
    }
}
