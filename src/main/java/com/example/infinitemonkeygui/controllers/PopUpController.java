package com.example.infinitemonkeygui.controllers;

import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.io.IOException;

public class PopUpController {

    private static Alert stringMatched;
    private static Alert wrongInput;
    private static final Stage popUpStage = new Stage();

    static{
        stringMatched = createAlert("String Found", "Your String has been found!");
        wrongInput = createAlert("Wrong Input Type", "Check the input field");
    }

    private static Alert createAlert(String alertTitle, String context) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(alertTitle);
        alert.setHeaderText(null);
        alert.setContentText(context);
        return alert;
    }

    public static void stringMatchShowAlert() throws IOException {
        stringMatched.showAndWait();
    }

    public static void wrongInputShowAlert(){
        wrongInput.showAndWait();
    }

}
