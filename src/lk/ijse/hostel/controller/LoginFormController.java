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
import lk.ijse.hostel.util.SetNavigation;

import java.io.IOException;

public class LoginFormController {

    public AnchorPane context;
    public TextField txtUserName;
    public PasswordField txtPassword;
    public Label lblError;

    public void LoginOnAction(ActionEvent actionEvent) throws IOException {
        SetNavigation.setUI("test","dashboard",this.context);
    }
}
