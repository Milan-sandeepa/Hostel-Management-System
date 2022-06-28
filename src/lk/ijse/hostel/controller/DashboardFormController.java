package lk.ijse.hostel.controller;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.hostel.util.SetNavigation;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;

public class DashboardFormController {
    public AnchorPane root;
    public Label lblMenu;
    public Label lblDescription;

    public void signOutPressed(MouseEvent mouseEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are You Sure LogOut?", ButtonType.YES,ButtonType.NO);

        Optional<ButtonType> buttonType = alert.showAndWait();

        if (buttonType.get().equals(ButtonType.YES)){
            SetNavigation.setUI("LoginForm","Login",this.root);
        }
    }

    public void playMouseExitAnimation(MouseEvent mouseEvent) {

    }

    public void playMouseEnterAnimation(MouseEvent mouseEvent) {

    }

    public void navigate(MouseEvent mouseEvent) {

    }
}
