package lk.ijse.hostel.util;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class ValidationUtil {
    public static Object validate(LinkedHashMap<TextField, Pattern> map, Button btn) {
        btn.setDisable(true);
        for (TextField key : map.keySet()) {
            Pattern pattern = map.get(key);
            if (!pattern.matcher(key.getText()).matches()){
                //if the input is not matching
                addError((TextField) key);
                return key;
            }
            removeError((TextField) key);
        }
        btn.setDisable(false);
        return true;
    }

    private static void removeError(TextField txtField) {
        txtField.setStyle("-fx-border-color: green");

    }

    private static void addError(TextField txtField) {
        if (txtField.getText().length() > 0) {
            txtField.setStyle("-fx-border-color: red");
        }

    }
}
