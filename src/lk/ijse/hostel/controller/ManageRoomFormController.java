package lk.ijse.hostel.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hostel.bo.BOFactory;
import lk.ijse.hostel.bo.custom.RoomBO;
import lk.ijse.hostel.bo.custom.StudentBO;
import lk.ijse.hostel.dto.RoomDTO;
import lk.ijse.hostel.dto.StudentDTO;
import lk.ijse.hostel.util.SetNavigation;
import lk.ijse.hostel.util.ValidationUtil;
import lk.ijse.hostel.view.tm.RoomTM;
import lk.ijse.hostel.view.tm.StudentTM;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Pattern;

public class ManageRoomFormController {
    public AnchorPane root;
    public JFXButton btnSave;
    public JFXButton btnUpdate;
    public JFXButton btnDelete;
    public JFXTextField txtRoomId;
    public JFXTextField txtRoomKeyMoney;
    public JFXTextField txtRoomQty;
    public TableView<RoomTM> tblRoom;
    public TableColumn colId;
    public TableColumn colType;
    public TableColumn colKeyMoney;
    public TableColumn colQty;
    public JFXComboBox cmbRoomType;

    private final RoomBO roomBO = (RoomBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.ROOM);
    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap();

    public void initialize(){

        btnUpdate.setDisable(true);

        ObservableList type = FXCollections.observableArrayList("Non-AC","Non-AC / Food","AC","AC / Food");
        cmbRoomType.getItems().addAll(type);

        colId.setCellValueFactory(new PropertyValueFactory<>("room_Type_id"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colKeyMoney.setCellValueFactory(new PropertyValueFactory<>("key_money"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));

        tblRoom.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                txtRoomId.setText(newValue.getRoom_Type_id());
                cmbRoomType.setValue(String.valueOf(newValue.getType()));
                txtRoomKeyMoney.setText(newValue.getKey_money());
                txtRoomQty.setText(String.valueOf(newValue.getQty()));
                txtRoomId.setDisable(true);
                btnSave.setDisable(true);
                btnUpdate.setDisable(false);
                btnDelete.setDisable(false);
            }
        });

        Pattern idPattern = Pattern.compile("^(RM-)[0-9]{4}$");
        Pattern keyMoneyPattern = Pattern.compile("^[A-z ,-/0-9]+$");
        Pattern qty = Pattern.compile("^[0-9]+$");


        map.put(txtRoomId, idPattern);
        map.put(txtRoomKeyMoney, keyMoneyPattern);
        map.put(txtRoomQty, qty);

        loadAllRooms();
        btnSave.setDisable(true);
        btnDelete.setDisable(true);

    }

    private void loadAllRooms() {
        tblRoom.getItems().clear();
        try {
            ArrayList<RoomDTO> allRooms = roomBO.getAllRooms();
            for (RoomDTO room : allRooms) {
                tblRoom.getItems().add(new RoomTM(room.getRoom_Type_id(),room.getType(),room.getKey_money(),room.getQty()));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
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

    public void btnSaveOnAction(ActionEvent actionEvent) throws IOException {
        String id=txtRoomId.getText();
        String roomType=cmbRoomType.getValue().toString();
        String keyMoney=txtRoomKeyMoney.getText();
        int qty= Integer.parseInt(txtRoomQty.getText());

        try {
            if (existRoom(id)) {
                new Alert(Alert.AlertType.ERROR, id + " already exists").show();
            }else{
                new Alert(Alert.AlertType.CONFIRMATION,  "Saved...!").show();
                clear();
                RoomDTO roomDTO = new RoomDTO(id, roomType, keyMoney, qty);
                roomBO.addRoom(roomDTO);
                tblRoom.refresh();
                btnSave.setDisable(true);
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to save the Room " + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        clear();
        tblRoom.refresh();
        SetNavigation.setUI("ManageRoomForm","ManageRoom",this.root);
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String id=txtRoomId.getText();
        String roomType=cmbRoomType.getValue().toString();
        String keyMoney=txtRoomKeyMoney.getText();
        int qty= Integer.parseInt(txtRoomQty.getText());

        try {
            if (!existRoom(id)) {
                new Alert(Alert.AlertType.ERROR, "There is no such room associated with the id " + id).show();
            }else {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated...!").show();
                clear();

                RoomDTO roomDTO = new RoomDTO(id, roomType, keyMoney, qty);
                roomBO.updateRoom(roomDTO);
                clear();
                btnUpdate.setDisable(true);
                btnSave.setDisable(true);

                RoomTM selectedRoom = tblRoom.getSelectionModel().getSelectedItem();
                selectedRoom.setRoom_Type_id(id);
                selectedRoom.setType(roomType);
                selectedRoom.setKey_money(keyMoney);
                selectedRoom.setQty(qty);
                tblRoom.refresh();
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to update the room " + id + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String id = tblRoom.getSelectionModel().getSelectedItem().getRoom_Type_id();
        try {
            if (!existRoom(id)) {
                new Alert(Alert.AlertType.ERROR, "There is no such room associated with the id " + id).show();
            }else{
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure Delete Room ?",ButtonType.YES,ButtonType.NO);

                Optional<ButtonType> buttonType = alert.showAndWait();

                if (buttonType.get().equals(ButtonType.YES)){
                    roomBO.deleteRoom(id);
                    tblRoom.getItems().remove(tblRoom.getSelectionModel().getSelectedItem());
                    tblRoom.getSelectionModel().clearSelection();
                    clear();
                    btnSave.setDisable(true);
                    btnDelete.setDisable(true);
                    btnUpdate.setDisable(true);
                    new Alert(Alert.AlertType.CONFIRMATION, "Room Details Deleted...!").show();
                }

            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to delete the Room " + id).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void textFields_Key_Released(KeyEvent keyEvent) {
        Object response = ValidationUtil.validate(map,btnSave);
        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof TextField) {
                TextField errorText = (TextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {
                //new Alert(Alert.AlertType.INFORMATION, "Aded").showAndWait();
            }
        }
    }

    private boolean existRoom(String id) throws SQLException, ClassNotFoundException {
        return roomBO.ifRoomExist(id);
    }

    private void clear() {

    }
}
