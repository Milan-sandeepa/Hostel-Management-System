package lk.ijse.hostel.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hostel.bo.BOFactory;
import lk.ijse.hostel.bo.custom.UserBO;
import lk.ijse.hostel.util.SetNavigation;

import java.io.IOException;
import java.util.Optional;

public class ManageUserFormController {
    public AnchorPane root;
    public JFXButton btnUpdate;
    public JFXButton btnDelete;
    public JFXTextField txtUser;
    public JFXPasswordField txtPswd;

    private final UserBO userBO = (UserBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.USER);

    public void initialize(){
        txtUser.setText(userBO.getUserName());
        txtPswd.setText(userBO.getPassWord());
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

    public void btnUpdateOnAction(ActionEvent actionEvent) {

    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {

    }

    public void showPassword(MouseEvent mouseEvent) {

    }

    public void hidePassword(MouseEvent mouseEvent) {

    }
}
