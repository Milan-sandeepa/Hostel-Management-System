package lk.ijse.hostel.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hostel.util.SetNavigation;

import java.io.IOException;
import java.util.Optional;

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
    public JFXComboBox<String> cmbStudentId;
    public JFXTextField txtStudentName;
    public JFXComboBox<String> cmbRoomId;
    public JFXTextField txtKeyMoney;
    public JFXTextField txtRoomType;
    public JFXTextField txtQty;
    public Label lblTotal;
    public JFXButton btnReserveRoom;
    public TableColumn colStatus;
    public JFXComboBox<String> cmbStatus;

    private String orderId;

    public void initialize(){
        ObservableList status = FXCollections.observableArrayList("Paid","Not Paid");
        cmbStatus.getItems().addAll(status);
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

    public void btnAdd_OnAction(ActionEvent actionEvent) {

    }

    public void btnReserveRoom_OnAction(ActionEvent actionEvent) {

    }

    public void textFields_Key_Released(KeyEvent keyEvent) {

    }
}
