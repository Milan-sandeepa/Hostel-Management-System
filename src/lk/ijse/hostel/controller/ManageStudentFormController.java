package lk.ijse.hostel.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
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
import lk.ijse.hostel.bo.custom.StudentBO;
import lk.ijse.hostel.dto.StudentDTO;
import lk.ijse.hostel.entity.Student;
import lk.ijse.hostel.util.FactoryConfiguration;
import lk.ijse.hostel.util.SetNavigation;
import lk.ijse.hostel.util.ValidationUtil;
import lk.ijse.hostel.view.tm.StudentTM;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Pattern;

import static org.jboss.logging.NDC.clear;

public class ManageStudentFormController {
    public AnchorPane root;
    public JFXButton btnSave;
    public JFXButton btnUpdate;
    public JFXButton btnDelete;
    public JFXTextField txtStudentId;
    public JFXTextField txtStudentName;
    public JFXTextField txtStudentAddress;
    public JFXTextField txtStudentContact;
    public JFXDatePicker txtDob;
    public JFXComboBox cmbSex;

    public TableView<StudentTM> tblStudent;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colContact;
    public TableColumn colDay;
    public TableColumn colGender;

    private final StudentBO studentBO = (StudentBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.STUDENT);
    LinkedHashMap<TextField, Pattern> map = new LinkedHashMap();

    public void initialize(){
        btnUpdate.setDisable(true);

        ObservableList sex = FXCollections.observableArrayList("Male","Female");
        cmbSex.getItems().addAll(sex);

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colDay.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));

        tblStudent.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                txtStudentId.setText(newValue.getId());
                txtStudentName.setText(newValue.getName());
                txtStudentAddress.setText(newValue.getAddress());
                txtStudentContact.setText(newValue.getContact());
                txtDob.setValue(LocalDate.parse(newValue.getDob()));
                cmbSex.setValue(String.valueOf(newValue.getGender()));
                txtStudentId.setDisable(true);
                btnSave.setDisable(true);
                btnUpdate.setDisable(false);
                btnDelete.setDisable(false);
            }
        });

        Pattern idPattern = Pattern.compile("^[A-z 0-9-]+$");
        Pattern namePattern = Pattern.compile("^[A-z ]+$");
        Pattern addressPattern = Pattern.compile("^[A-z1-9 /,.-]+$");
        Pattern contactPattern = Pattern.compile("^[0-9]{10,11}$");


        map.put(txtStudentId, idPattern);
        map.put(txtStudentName, namePattern);
        map.put(txtStudentAddress, addressPattern);
        map.put(txtStudentContact, contactPattern);

        loadAllStudents();
        btnSave.setDisable(true);
        btnDelete.setDisable(true);
    }

    private void loadAllStudents() {
        tblStudent.getItems().clear();
        try {
            ArrayList<StudentDTO> allStudent = studentBO.getAllStudent();
            for (StudentDTO student : allStudent) {
                tblStudent.getItems().add(new StudentTM(student.getId(),student.getName(),student.getAddress(),student.getContactNo(),student.getDob(),student.getGender()));
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

            String id=txtStudentId.getText();
            String name=txtStudentName.getText();
            String address=txtStudentAddress.getText();
            String contact=txtStudentContact.getText();
            String dob=String.valueOf(txtDob.getValue());
            String gender=cmbSex.getValue().toString();

        try {
            if (existStudent(id)) {
                new Alert(Alert.AlertType.ERROR, id + " already exists").show();
            }else{
                new Alert(Alert.AlertType.CONFIRMATION,  "Saved...!").show();
                clear();
                StudentDTO studentDTO = new StudentDTO(id, name, address, contact, dob, gender);
                studentBO.addStudent(studentDTO);
                tblStudent.refresh();
                btnSave.setDisable(true);
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to save the customer " + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        clear();
        tblStudent.refresh();
        SetNavigation.setUI("ManageStudentForm","ManageStudent",this.root);
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String id=txtStudentId.getText();
        String name=txtStudentName.getText();
        String address=txtStudentAddress.getText();
        String contact=txtStudentContact.getText();
        String dob=String.valueOf(txtDob.getValue());
        String gender=cmbSex.getValue().toString();

        try {
            if (!existStudent(id)) {
                new Alert(Alert.AlertType.ERROR, "There is no such student associated with the id " + id).show();
            }else {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated...!").show();
                clear();

                StudentDTO studentDTO = new StudentDTO(id, name, address, contact, dob, gender);
                studentBO.updateStudent(studentDTO);
                clear();
                btnUpdate.setDisable(true);
                btnSave.setDisable(true);

                StudentTM selectedStudent = tblStudent.getSelectionModel().getSelectedItem();
                selectedStudent.setName(name);
                selectedStudent.setAddress(address);
                selectedStudent.setContact(contact);
                selectedStudent.setDob(dob);
                selectedStudent.setGender(gender);
                tblStudent.refresh();
            }

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to update the customer " + id + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {
        String id = tblStudent.getSelectionModel().getSelectedItem().getId();
        try {
            if (!existStudent(id)) {
                new Alert(Alert.AlertType.ERROR, "There is no such student associated with the id " + id).show();
            }else{
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are You Sure Delete Student ?",ButtonType.YES,ButtonType.NO);

                Optional<ButtonType> buttonType = alert.showAndWait();

                if (buttonType.get().equals(ButtonType.YES)){
                    studentBO.deleteStudent(id);
                    tblStudent.getItems().remove(tblStudent.getSelectionModel().getSelectedItem());
                    tblStudent.getSelectionModel().clearSelection();
                    clear();
                    btnSave.setDisable(true);
                    btnDelete.setDisable(true);
                    btnUpdate.setDisable(true);
                    new Alert(Alert.AlertType.CONFIRMATION, "Student Details Deleted...!").show();
                }

            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to delete the student " + id).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private boolean existStudent(String id) throws SQLException, ClassNotFoundException {
        return studentBO.ifStudentExist(id);
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

    private void clear() {
        txtStudentId.clear();
        txtStudentName.clear();
        txtStudentAddress.clear();
        txtStudentContact.clear();

    }
}
