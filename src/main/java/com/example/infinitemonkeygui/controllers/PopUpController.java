package com.example.infinitemonkeygui.controllers;


import javafx.scene.control.Alert;
import javafx.application.Platform;

public class PopUpController {

    private static final Alert stringMatched;
    private static final Alert wrongInput;

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

    public static void stringMatchFoundShowAlert() {
        Platform.runLater(stringMatched::showAndWait);
    }

    public static void wrongInputShowAlert(){
        Platform.runLater(wrongInput::showAndWait);
    }

}
