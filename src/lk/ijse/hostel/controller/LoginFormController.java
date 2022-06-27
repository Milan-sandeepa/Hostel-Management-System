package lk.ijse.hostel.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import lk.ijse.hostel.bo.BOFactory;
import lk.ijse.hostel.bo.custom.UserBO;
import lk.ijse.hostel.entity.Room;
import lk.ijse.hostel.entity.Student;
import lk.ijse.hostel.util.FactoryConfiguration;
import lk.ijse.hostel.util.SetNavigation;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.swing.*;
import java.io.IOException;

public class LoginFormController {

    public AnchorPane context;
    public TextField txtUserName;
    public PasswordField txtPassword;
    public Label lblError;

    //Dependancy Injection-property injection
    private final UserBO userBO = (UserBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.USER);

    public void LoginOnAction(ActionEvent actionEvent) throws IOException {
        String uname=txtUserName.getText();
        String psw=txtPassword.getText();

        if (uname.equalsIgnoreCase(userBO.getUserName()) & psw.equalsIgnoreCase(userBO.getPassWord())){
            SetNavigation.setUI("DashboardForm","Dashboard",this.context);
        }else {
            lblError.setText("Enter Correct Login Details");
        }
    }

    public void RegisterOnAction(ActionEvent actionEvent) throws IOException {

    }
}
