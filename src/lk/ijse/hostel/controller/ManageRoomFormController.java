package lk.ijse.hostel.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hostel.bo.BOFactory;
import lk.ijse.hostel.bo.custom.RoomBO;
import lk.ijse.hostel.bo.custom.StudentBO;
import lk.ijse.hostel.dto.RoomDTO;
import lk.ijse.hostel.dto.StudentDTO;
import lk.ijse.hostel.util.SetNavigation;
import lk.ijse.hostel.view.tm.RoomTM;
import lk.ijse.hostel.view.tm.StudentTM;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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

    public void initialize(){
        txtRoomId.setText(generateNewId());
        txtRoomId.setDisable(true);
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

        loadAllRooms();
        btnSave.setDisable(false);
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

    private String generateNewId() {
        try {
            return roomBO.generateNewID();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate a new id " + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        if (tblRoom.getItems().isEmpty()) {
            return "R001";
        } else {
            String id = getLastRoomId();
            int newStudentId = Integer.parseInt(id.replace("R", "")) + 1;
            return String.format("R%03d", newStudentId);
        }
    }

    private String getLastRoomId() {
        List<RoomTM> tempRoomList = new ArrayList<>(tblRoom.getItems());
        Collections.sort(tempRoomList);
        return tempRoomList.get(tempRoomList.size() - 1).getRoom_Type_id();
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

    public void btnSaveOnAction(ActionEvent actionEvent) {
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
                txtRoomId.setText(generateNewId());
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
        txtRoomId.setText(generateNewId());
        tblRoom.refresh();
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

    }

    public void textFields_Key_Released(KeyEvent keyEvent) {

    }

    private boolean existRoom(String id) throws SQLException, ClassNotFoundException {
        return roomBO.ifRoomExist(id);
    }

    private void clear() {

    }
}
