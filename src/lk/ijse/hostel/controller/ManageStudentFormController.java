package lk.ijse.hostel.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hostel.util.SetNavigation;

import java.io.IOException;
import java.util.Optional;

public class ManageStudentFormController {
    public AnchorPane root;
    public JFXButton btnSave;
    public JFXButton btnUpdate;
    public JFXButton btnDelete;
    public JFXTextField txtStudentId;
    public JFXTextField txtStudentName;
    public JFXTextField txtStudentAddress;
    public JFXTextField txtStudentContact;
    public JFXTextField txtStudentBirthDay;
    public JFXTextField txtStudentGender;
    public TableView tblStudent;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colContact;
    public TableColumn colDay;
    public TableColumn colGender;

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

    public void btnSaveOnAction(ActionEvent actionEvent) {

    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {

    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {

    }

    public void textFields_Key_Released(KeyEvent keyEvent) {

    }
}
