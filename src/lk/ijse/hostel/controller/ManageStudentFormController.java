package lk.ijse.hostel.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
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
import lk.ijse.hostel.bo.custom.StudentBO;
import lk.ijse.hostel.dto.StudentDTO;
import lk.ijse.hostel.entity.Student;
import lk.ijse.hostel.util.FactoryConfiguration;
import lk.ijse.hostel.util.SetNavigation;
import lk.ijse.hostel.view.tm.StudentTM;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
    public JFXTextField txtStudentGender;
    public JFXDatePicker txtDob;
    public TableView<Student> tblStudent;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colContact;
    public TableColumn colDay;
    public TableColumn colGender;

    private final StudentBO studentBO = (StudentBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.STUDENT);


    public void initialize(){
        txtStudentId.setText(generateNewId());
        txtStudentId.setDisable(true);
        btnUpdate.setDisable(true);

        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
        colDay.setCellValueFactory(new PropertyValueFactory<>("dob"));
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));

        loadAllStudents();
        btnSave.setDisable(true);
        btnDelete.setDisable(true);
    }

    private void loadAllStudents() {

    }

    private String generateNewId() {
        return null;
    }

    private String getLastStudentId() {
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

    public void btnSaveOnAction(ActionEvent actionEvent) {
        try {
            String id=txtStudentId.getText();
            String name=txtStudentName.getText();
            String address=txtStudentAddress.getText();
            String contact=txtStudentContact.getText();
            String dob=String.valueOf(txtDob.getValue());
            String gender=txtStudentGender.getText();

            if (existStudent(id)) {
                new Alert(Alert.AlertType.ERROR, id + " already exists").show();
            }else{
                new Alert(Alert.AlertType.CONFIRMATION,  "Saved...!").show();
                clear();
                txtStudentId.setText(generateNewId());
                StudentDTO studentDTO = new StudentDTO(id, name, address, contact, dob, gender);
                studentBO.addStudent(studentDTO);
                btnSave.setDisable(true);
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to save the customer " + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        clear();
        txtStudentId.setText(generateNewId());
    }

    private boolean existStudent(String id) throws SQLException, ClassNotFoundException {
        return studentBO.ifStudentExist(id);
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {

    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {

    }

    public void textFields_Key_Released(KeyEvent keyEvent) {

    }
}
