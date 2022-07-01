package lk.ijse.hostel.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import lk.ijse.hostel.bo.BOFactory;
import lk.ijse.hostel.bo.custom.impl.ReservationBOImpl;
import lk.ijse.hostel.dao.DAOFactory;
import lk.ijse.hostel.dao.custom.RoomDAO;
import lk.ijse.hostel.dto.ReservationDTO;
import lk.ijse.hostel.dto.RoomDTO;
import lk.ijse.hostel.dto.StudentDTO;
import lk.ijse.hostel.entity.Room;
import lk.ijse.hostel.entity.Student;
import lk.ijse.hostel.util.SetNavigation;
import lk.ijse.hostel.view.tm.ReservationDetailsTM;
import lk.ijse.hostel.view.tm.ReservationTM;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.time.LocalTime.now;

public class ReserveRoomFormController {
    public AnchorPane root;
    public JFXButton btnSave;
    public TableView<ReservationDetailsTM> tblReserveDetails;
    public TableColumn colRoomCode;
    public TableColumn colType;
    public TableColumn colKeyMoney;
    public TableColumn colQty;
    public TableColumn colTotal;
    public TableColumn colOption;
    public Label lblDate;
    public Label lblId;
    public JFXComboBox<String> cmbStudentId;
    public JFXTextField txtStudentName;
    public JFXComboBox<String> cmbRoomId;
    public JFXTextField txtKeyMoney;
    public JFXTextField txtRoomType;
    public JFXTextField txtQty;
    public Label lblTotal;
    public JFXButton btnReserveRoom;
    public TableColumn colStatus;
    public JFXComboBox<String> cmbStatus;

    private final ReservationBOImpl reservationBO= (ReservationBOImpl) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.RESERVE);
    public JFXTextField txtAvailableRooms;

    private String ReservationID;

    public void initialize(){
        ObservableList status = FXCollections.observableArrayList("paid","Not Paid");
        cmbStatus.getItems().addAll(status);
        ReservationID=generateNewOrderId();
        lblId.setText(ReservationID);
        lblDate.setText(LocalDate.now().toString());
        btnReserveRoom.setDisable(true);

        cmbStudentId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            enableOrDisableReservationButton();

            if (newValue != null) {
                try {
                    try {
                        if (!existStudent(newValue + "")) {
                            //"There is no such student associated with the id " + id
                            new Alert(Alert.AlertType.ERROR, "There is no such student associated with the id " + newValue + "").show();
                        }
                        /*Search student*/
                        StudentDTO studentDTO = reservationBO.searchStudent(newValue + "");
                        txtStudentName.setText(studentDTO.getName());

                    } catch (SQLException e) {
                        new Alert(Alert.AlertType.ERROR, "Failed to find the student " + newValue + "" + e).show();
                    }
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            } else {
                txtStudentName.clear();
            }
        });

        cmbRoomId.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newItemCode) -> {
            txtQty.setEditable(newItemCode != null);
            btnSave.setDisable(newItemCode == null);

            if (newItemCode != null) {
                try {
                    if (!existRoom(newItemCode + "")) {

                    }
                    /*Find Room*/
                    RoomDTO room = reservationBO.searchRoom(newItemCode + "");
                    txtRoomType.setText(room.getType());
                    txtKeyMoney.setText(String.valueOf(room.getKey_money()));
                    txtAvailableRooms.setText(String.valueOf(room.getQty()));


                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

            } else {
                txtRoomType.clear();
                txtQty.clear();

            }
        });

        colRoomCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        colType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colKeyMoney.setCellValueFactory(new PropertyValueFactory<>("keyMoney"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        TableColumn<ReservationDetailsTM, Button> lastCol = (TableColumn<ReservationDetailsTM, Button>) tblReserveDetails.getColumns().get(6);

        lastCol.setCellValueFactory(param -> {
            Button btnDelete = new Button("Delete");

            btnDelete.setOnAction(event -> {
                tblReserveDetails.getItems().remove(param.getValue());
                tblReserveDetails.getSelectionModel().clearSelection();
                calculateTotal();
                enableOrDisableReservationButton();
            });
            return new ReadOnlyObjectWrapper<>(btnDelete);
        });

        tblReserveDetails.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, selectedOrderDetail) -> {
            if (selectedOrderDetail != null) {
                cmbStudentId.setDisable(true);
                cmbRoomId.setDisable(true);
                txtStudentName.setDisable(true);
                txtRoomType.setDisable(true);
                txtKeyMoney.setDisable(true);
                txtQty.setDisable(false);
                cmbRoomId.setValue(selectedOrderDetail.getCode());
                btnSave.setText("Update");
                cmbStatus.setValue(selectedOrderDetail.getStatus());
                txtQty.setText(selectedOrderDetail.getQty() + "");
            } else {
                btnSave.setText("Add");
                cmbStudentId.setDisable(false);
                cmbRoomId.getSelectionModel().clearSelection();
                txtQty.clear();
            }
        });

        loadAllStudentIds();
        loadAllRoomIds();
        btnSave.setDisable(true);
    }

    private void calculateTotal() {
        double total=0 ;
        for (ReservationDetailsTM detail : tblReserveDetails.getItems()) {
            total = total+detail.getTotal();
        }
        lblTotal.setText(String.valueOf(total));
    }

    private void loadAllRoomIds() {
        try {
            ArrayList<RoomDTO> all = reservationBO.getAllRooms();
            for (RoomDTO dto : all) {
                cmbRoomId.getItems().add(dto.getRoom_Type_id());
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void loadAllStudentIds() {
        try {
            ArrayList<StudentDTO> all = reservationBO.getAllStudents();
            for (StudentDTO studentDTO : all) {
                cmbStudentId.getItems().add(studentDTO.getId());
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to load Student ids").show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void enableOrDisableReservationButton() {
        btnReserveRoom.setDisable(!(cmbStudentId.getSelectionModel().getSelectedItem() != null && !tblReserveDetails.getItems().isEmpty()));
    }

    boolean existStudent(String id) throws SQLException, ClassNotFoundException {
        return reservationBO.ifStudentExist(id);
    }

    private boolean existRoom(String s) throws SQLException, ClassNotFoundException {
        return reservationBO.ifRoomExist(s);
    }

    private String generateNewOrderId() {
        try {
            return reservationBO.generateNewReservationId();
        } catch (SQLException throwables) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate a new Reservation id").show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
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

    public void btnAdd_OnAction(ActionEvent actionEvent) {
        btnSave.setDisable(false);
        String roomID = cmbRoomId.getSelectionModel().getSelectedItem();
        String RoomType=txtRoomType.getText();
        String status = cmbStatus.getSelectionModel().getSelectedItem();
        int qtyWant=Integer.parseInt(txtQty.getText());
        double keyMoney = Double.parseDouble(txtKeyMoney.getText());
        double total = keyMoney * qtyWant;

        if (qtyWant<=Integer.parseInt(txtAvailableRooms.getText())){
            boolean exists = tblReserveDetails.getItems().stream().anyMatch(detail -> detail.getCode().equals(roomID));

            if (exists) {
                ReservationDetailsTM reservationDetailsTM = tblReserveDetails.getItems().stream().filter(detail -> detail.getCode().equals(roomID)).findFirst().get();

                if (btnSave.getText().equalsIgnoreCase("Update")){
                    cmbStatus.setDisable(false);
                    txtAvailableRooms.setDisable(false);
                    reservationDetailsTM.setQty(qtyWant);
                    reservationDetailsTM.setTotal(total);
                    reservationDetailsTM.setStatus(status);
                    tblReserveDetails.getSelectionModel().clearSelection();
                } else {
                    reservationDetailsTM.setQty(reservationDetailsTM.getQty() + qtyWant);
                    total = reservationDetailsTM.getQty()*(total);
                    reservationDetailsTM.setTotal(total);
                }
                tblReserveDetails.refresh();
            } else {
                tblReserveDetails.getItems().add(new ReservationDetailsTM(roomID,RoomType, keyMoney, qtyWant, total,status ));
            }
            cmbStudentId.requestFocus();
            calculateTotal();
            enableOrDisableReservationButton();
            cmbStudentId.setDisable(true);
        }else {
            new Alert(Alert.AlertType.WARNING, "All Rooms Booked...").show();
            txtQty.clear();
            txtRoomType.clear();

        }
    }

    public void btnReserveRoom_OnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        cmbStudentId.setDisable(false);
        txtStudentName.setDisable(false);
        txtAvailableRooms.setDisable(false);
        txtKeyMoney.setDisable(false);
        txtQty.setDisable(false);

        StudentDTO studentDTO = reservationBO.searchStudent(cmbStudentId.getValue());
        RoomDTO roomDTO = reservationBO.searchRoom(cmbRoomId.getValue());
        Student student = new Student(studentDTO.getId(), studentDTO.getName(), studentDTO.getAddress(), studentDTO.getContactNo(), studentDTO.getDob(), studentDTO.getGender());
        Room room = new Room(roomDTO.getRoom_Type_id(), roomDTO.getType(), roomDTO.getKey_money(), roomDTO.getQty() - 1);
        String stat=cmbStatus.getValue();
        boolean b = reserveRoom(ReservationID,
                LocalDate.now(),
                stat,student,room);

        if (b) {
            new Alert(Alert.AlertType.INFORMATION, "Room Has Reserved successfully").show();
            SetNavigation.setUI("ReserveRoomForm","ReserveRoom",this.root);
        } else {
            new Alert(Alert.AlertType.ERROR, "Room has not been Reserved successfully").show();
        }
    }

    public boolean reserveRoom(String res_id, LocalDate Date, String status, Student student, Room room) {
        try {
            ReservationDTO reservationDTO = new ReservationDTO(res_id, Date,status, student, room);
            return reservationBO.reservationRoom(reservationDTO);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void textFields_Key_Released(KeyEvent keyEvent) {

    }
}
