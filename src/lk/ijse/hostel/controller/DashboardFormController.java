package lk.ijse.hostel.controller;

import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import lk.ijse.hostel.util.SetNavigation;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;

public class DashboardFormController {
    public AnchorPane root;
    public Label lblMenu;
    public Label lblDescription;
    public ImageView imgStudent;
    public ImageView imgRoom;
    public ImageView imgReserve;
    public ImageView imgMoney;

    public void signOutPressed(MouseEvent mouseEvent) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                "Are You Sure LogOut?", ButtonType.YES,ButtonType.NO);

        Optional<ButtonType> buttonType = alert.showAndWait();

        if (buttonType.get().equals(ButtonType.YES)){
            SetNavigation.setUI("LoginForm","Login",this.root);
        }
    }

    public void playMouseEnterAnimation(MouseEvent event) {
        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();

            switch (icon.getId()) {
                case "imgStudent":
                    lblMenu.setText("Manage Students");
                    lblDescription.setText("Click to add, edit, delete, search or view items");
                    break;
                case "imgRoom":
                    lblMenu.setText("Manage Rooms");
                    lblDescription.setText("Click here if you want to see a orders reports");
                    break;
                case "imgReserve":
                    lblMenu.setText("Reserve Room");
                    lblDescription.setText("Click here if you want to see a orders reports");
                    break;
                case "imgMoney":
                    lblMenu.setText("Pending KeyMoney");
                    lblDescription.setText("Click here if you want to see a orders reports");
                    break;
            }

            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1.2);
            scaleT.setToY(1.2);
            scaleT.play();

            DropShadow glow = new DropShadow();
            glow.setColor(Color.CORNFLOWERBLUE);
            glow.setWidth(20);
            glow.setHeight(20);
            glow.setRadius(20);
            icon.setEffect(glow);
        }
    }

    public void playMouseExitAnimation(MouseEvent event) {
        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();
            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1);
            scaleT.setToY(1);
            scaleT.play();

            icon.setEffect(null);
            lblMenu.setText("Welcome");
            lblDescription.setText("Please select one of above main operations to proceed");
        }
    }

    public void navigate(MouseEvent event) throws IOException {
        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();

            Parent root = null;

            switch (icon.getId()) {

                case "imgStudent":
                    root = FXMLLoader.load(this.getClass().getResource("../view/ManageItemForm.fxml"));
                    break;
                case "imgRoom":
                    root = FXMLLoader.load(this.getClass().getResource("../view/ManageReportForm.fxml"));
                    break;
                case "imgReserve":
                    root = FXMLLoader.load(this.getClass().getResource("../view/ManageItemForm.fxml"));
                    break;
                case "imgMoney":
                    root = FXMLLoader.load(this.getClass().getResource("../view/ManageReportForm.fxml"));
                    break;

            }

            if (root != null) {
                Scene subScene = new Scene(root);
                Stage primaryStage = (Stage) this.root.getScene().getWindow();
                primaryStage.setScene(subScene);
                primaryStage.centerOnScreen();

                TranslateTransition tt = new TranslateTransition(Duration.millis(350), subScene.getRoot());
                tt.setFromX(-subScene.getWidth());
                tt.setToX(0);
                tt.play();

            }
        }
    }

    public void navigateToUser(MouseEvent mouseEvent) {

    }

    public void navigateToHome(MouseEvent mouseEvent) {

    }
}
