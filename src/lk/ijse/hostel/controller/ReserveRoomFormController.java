package lk.ijse.hostel.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class ReserveRoomFormController {
    public AnchorPane root;
    public JFXButton btnSave;
    public TableView tblReserveDetails;
    public TableColumn colRoomCode;
    public TableColumn colType;
    public TableColumn colKeyMoney;
    public TableColumn colQty;
    public TableColumn colTotal;
    public TableColumn colOption;
    public Label lblDate;
    public Label lblId;
    public JFXComboBox cmbStudentId;
    public JFXTextField txtStudentName;
    public JFXComboBox cmbRoomId;
    public JFXTextField txtKeyMoney;
    public JFXTextField txtRoomType;
    public JFXTextField txtQty;
    public Label lblTotal;
    public JFXButton btnReserveRoom;

    public void signOutPressed(MouseEvent mouseEvent) {

    }

    public void navigateToHome(MouseEvent mouseEvent) {

    }

    public void btnAdd_OnAction(ActionEvent actionEvent) {

    }

    public void btnReserveRoom_OnAction(ActionEvent actionEvent) {

    }

    public void textFields_Key_Released(KeyEvent keyEvent) {

    }
}
