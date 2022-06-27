package lk.ijse.hostel.controller;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hostel.util.SetNavigation;

import java.io.IOException;

public class DashboardFormController {
    public AnchorPane root;
    public Label lblMenu;
    public Label lblDescription;

    public void signOutPressed(MouseEvent mouseEvent) throws IOException {
        SetNavigation.setUI("LoginForm","Login",this.root);
    }

    public void playMouseExitAnimation(MouseEvent mouseEvent) {

    }

    public void playMouseEnterAnimation(MouseEvent mouseEvent) {

    }

    public void navigate(MouseEvent mouseEvent) {

    }
}
