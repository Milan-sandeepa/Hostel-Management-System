package lk.ijse.hostel.controller;

import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.Window;
import lk.ijse.hostel.bo.BOFactory;
import lk.ijse.hostel.bo.custom.UserBO;
import lk.ijse.hostel.dto.UserDTO;
import lk.ijse.hostel.entity.Room;
import lk.ijse.hostel.entity.Student;
import lk.ijse.hostel.entity.User;
import lk.ijse.hostel.util.FactoryConfiguration;
import lk.ijse.hostel.util.SetNavigation;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class LoginFormController {

    public AnchorPane context;
    public TextField txtUserName;
    public PasswordField txtPassword;
    public Label lblError;

    //Dependancy Injection-property injection
    private final UserBO userBO = (UserBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.USER);
    public FontAwesomeIconView passShow;
    public JFXTextField txtShowPassword;

    public void initialize(){

        txtShowPassword.setVisible(false);

    }

    public void LoginOnAction(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
        String uname=txtUserName.getText();
        String psw=txtPassword.getText();

        UserDTO user = userBO.getUser("U001");

        if (user.getUsername().equalsIgnoreCase(uname)&&user.getPassword().equalsIgnoreCase(psw)){
            SetNavigation.setUI("DashboardForm","DashboardForm",this.context);
        }else {
            lblError.setText("Enter Correct Login Details");
        }
    }

    public void showPassword(MouseEvent mouseEvent) {
        txtShowPassword.setText(txtPassword.getText());
        txtShowPassword.setVisible(true);
        txtPassword.setVisible(false);
    }

    public void hidePassword(MouseEvent mouseEvent) {
        txtShowPassword.setVisible(false);
        txtPassword.setVisible(true);
    }
}
